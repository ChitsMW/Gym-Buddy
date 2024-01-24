<template>
    <div>
        <form v-on:submit.prevent="saveProfile">
            <h1>Enter Profile Details</h1>
            <div role="alert" v-if="submitErrors">
                {{ submitErrorMsg }}
            </div>
            <div class="form-group">
                <label for="name">Name: </label>
                <input type="text" id="name" v-model="profile.name">
            </div>
            <div class="form-group">
                <label for="email">Email: </label>
                <input type="text" id="email" v-model="profile.email">
            </div>
            <div class="form-group">
                <label for="age">Age: </label>
                <input type="text" id="age" v-model.number="profile.age">
            </div>
            <div class="form-group">
                <label for="height">Height (in inches): </label>
                <input type="text" id="height" v-model.number="profile.height">
            </div>
            <div class="form-group">
                <label for="weight">Weight (in pounds): </label>
                <input type="text" id="weight" v-model.number="profile.weight">
            </div>
            <div class="form-group">
                <label for="experience">Experience Level: </label>
                <input type="radio" id="beginner" value="Beginner" v-model="profile.experienceLevel">
                <label for="beginner">Beginner</label>

                <input type="radio" id="intermediate" value="Intermediate" v-model="profile.experienceLevel">
                <label for="intermediate">Intermediate</label>

                <input type="radio" id="advanced" value="Advanced" v-model="profile.experienceLevel">
                <label for="advanced">Advanced</label>
            </div>
            <div>
                <button type="submit" id="submit-button">Save</button>
            </div>
        </form>
    </div>
</template>
<script>
import ProfileService from '../services/ProfileService';
export default {
    data() {
        return {
            profile: {
                name: "",
                email: "",
                photo_url: "",
                age: "",
                height: "",
                weight: "",
                experienceLevel: "",
            },
            profile_id: "",
            submitErrors: false,
            submitErrorMsg: 'There was a problem submitting the profile.',
        };
    },
    methods: {
        saveProfile() {
            ProfileService.createUserProfile(this.$store.state.user.id, this.profile)
                .then((response) => {
                    this.profile = response.data;
                    this.profile_id = response.data.profileId;
                    alert("Profile created successfully. Returning to dashboard.")

                    const userRole = this.$store.state.user.authorities[0].name;
                    if (userRole === 'ROLE_ADMIN') {
                        this.$router.push('/admin-dashboard');
                    } else if (userRole === 'ROLE_EMPLOYEE') {
                        this.$router.push('/employee-dashboard');
                    } else if (userRole === 'ROLE_USER') {
                        this.$router.push('/userdashboard');
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
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
    }
}
</script>

<style>
.input-label {
    width: 100px;
    display: inline-block;
}

.form-group {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 10px;
}

input {
    margin: .2rem;
    border-radius: .5rem;
    height: 1.2rem;
}

#submit-button {
    text-align: center;
    margin: 1rem auto;
    display: block;
}
</style>