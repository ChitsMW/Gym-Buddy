<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <h1>LIST OF MEMBERS</h1>
        <!-- <button @click="fetchUserList">Get List of Users</button> -->
        <ol>
            <li v-for="user in users" :key="user.id">
                <router-link :to="{ name: 'userdashboard', params: { id: user.id } }">
                    {{ user.username }}
                </router-link>
            </li>
        </ol>
    </div>
</template>


<script>
import AuthService from '../services/AuthService';
import ProfileService from '../services/ProfileService';

export default {
    data() {
        return {
            users: [],
        };
    },
    mounted() {
        this.fetchUserList();
    },
    methods: {
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
        async fetchUserList() {
            try {
                const response = await AuthService
                    .fetchUserList();
                console.log('Full Response:', response);
                this.users = response.data;
                console.log('Users:', this.users);
            } catch (error) {
                console.error('Error fetching user list:', error);
            }
        },

    },
}
</script>