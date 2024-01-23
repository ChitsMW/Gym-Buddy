<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div id="register" class="text-center">
        <form v-on:submit.prevent="registerEmployee">
            <h1>Create Employee Account</h1>
            <div role="alert" v-if="registrationErrors">
                {{ registrationErrorMsg }}
            </div>
            <div class="form-input-group">
                <label for="username">Employee Username</label>
                <input type="text" id="username" v-model="employee.username" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="password">Password</label>
                <input type="password" id="password" v-model="employee.password" required />
            </div>
            <div class="form-input-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" id="confirmPassword" v-model="employee.confirmPassword" required />
            </div>
            <button type="submit">Create Employee Account</button>

        </form>
    </div>
</template>
  
<script>
import authService from "../services/AuthService";

export default {
    data() {
        return {
            employee: {
                username: '',
                password: '',
                confirmPassword: '',
                role: 'employee',
            },
            registrationErrors: false,
            registrationErrorMsg: 'There were problems registering this user.',
        };
    },
    methods: {
        registerEmployee() {
            if (this.employee.password != this.employee.confirmPassword) {
                this.registrationErrors = true;
                this.registrationErrorMsg = 'Password & Confirm Password do not match.';
            } else {
                authService
                    .registerEmployee(this.employee)
                    .then((response) => {
                        if (response.status === 201) {
                            this.$router.push({ name: 'admin-dashboard' })
                        }
                    })
                    .catch((error) => {
                        const response = error.response;
                        this.registrationErrors = true;
                        if (response.status === 400) {
                            this.registrationErrorMsg = 'Bad Request: Validation Errors';
                        }
                    });
            }
        },
        clearErrors() {
            this.registrationErrors = false;
            this.registrationErrorMsg = 'There were problems registering this user.';
        },
        goToUserDashboard() {
            const userRole = this.$store.state.user.authorities[0].name;
            if (userRole === 'ROLE_ADMIN') {
                this.$router.push('/admin-dashboard');
            } else if (userRole === 'ROLE_EMPLOYEE') {
                this.$router.push('/employee-dashboard');
            } else if (userRole === 'ROLE_USER') {
                this.$router.push('/userdashboard');
            }
        },
    },
};
</script>
  
<style scoped>
.form-input-group {
    margin-bottom: 1rem;
}

label {
    margin-right: 0.5rem;
}
</style>