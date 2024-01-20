<template>
    <div class="user-profile">
        <h1>Welcome {{ $store.state.user.username }}!</h1>
        <h2>Enjoy your workout!</h2>
        <div>Inspirational Quote</div>
        <!-- Buttons for different actions -->
        <button @click="startWorkout">Start Workout</button>
        <button @click="goToGymCalendar">Go to Gym Calendar</button>
        <button @click="logout">Logout</button>

        <router-link class="button" v-bind:to="{ name: 'my-profile', params: { userId: this.$store.state.user.id } }">View
            Profile</router-link>

    </div>
</template>
<script>
import ProfileService from '../services/ProfileService';
export default {
    methods: {
        startWorkout() {
            // Implement logic to start a workout
            // You can navigate to a specific workout page or perform any other action
        },
        goToGymCalendar() {
            // Implement logic to navigate to the gym calendar
            // You can use Vue Router to navigate to the calendar page
            this.$router.push('/calendar');
        },
        logout() {
            this.$store.commit("LOGOUT");
            this.$store.commit("SET_USER", { lastLoginDate: null });
            this.$router.push("/login");
        },
        viewProfile() {
            // Implement logic to navigate to the profile update page
            // You can use Vue Router to navigate to the profile update page
            this.$router.push({ name: 'my-profile' });
        },
        checkForProfile() {
            let profileExists = false;
            ProfileService.getProfileByUserId(this.$store.state.user.id)
                .then((response) => {
                    if (response.data.profile.profile_id !== null) {
                        profileExists = true;

                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
            return profileExists;
        },
    }
};
</script>
<style scoped>
/* Add styling for your user profile page here */
.user-profile {
    /* Example styles, customize as needed */
    max-width: 400px;
    margin: 0 auto;
    text-align: center;
}

button {
    margin: 0.5rem;
    padding: 0.5rem 1rem;
    font-size: 1rem;
}

.button{
    margin: 0.5rem;
    padding: 0.5rem 1rem;
    font-size: 1rem;
}

</style>