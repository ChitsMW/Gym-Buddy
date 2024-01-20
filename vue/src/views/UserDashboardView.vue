<template>
    <header>
        <Sidebar />
        <div :style="{ 'margin-left': sidebarWidth }">
            <router-view />
        </div>
    </header>
    <div class="user-profile">
        <h1>Welcome {{ formattedUsername }}!</h1>
        <h2>Enjoy your workout!</h2>
        <div>Inspirational Quote</div>

        <!-- Buttons for different actions -->
        <button v-if="!isWorkoutInProgress" @click="startWorkout">Start Workout</button>
        <button v-else @click="endWorkout">End Workout</button>
        <button @click="goToEquipment">Log Equipment Used</button>
        <button @click="goToGymCalendar">Go to Gym Calendar</button>
        <router-link class="button" v-bind:to="{ name: 'my-profile', params: { userId: this.$store.state.user.id } }">View
            Profile</router-link>
        <button @click="viewPersonalMetrics">Previous Sessions</button>
        <button @click="logout">Logout</button>

        <div class="clock">
            <!-- <div v-if="isWorkoutInProgress"> -->
            <p>Workout Duration: {{ liveWorkoutDuration }}</p>
        </div>

    </div>
</template>


<script>
import Sidebar from '@/components/sidebar/Sidebar.vue'
import { sidebarWidth } from '@/components/sidebar/state.js'
import WorkoutService from '../services/WorkoutService';

export default {
    components: { Sidebar },
    computed: {
        formattedUsername() {
            const username = this.$store.state.user.username;
            return username.charAt(0).toUpperCase() + username.slice(1);
        },
    },
    data() {
        return {
            userProfile: null,
            liveWorkoutDuration: '0:00',
            updateInterval: null,
        };
    },
    methods: {
        startWorkout() {
            this.workoutStartTime = new Date();
            this.isWorkoutInProgress = true;
            this.updateInterval = setInterval(this.updateLiveDuration, 1000);
            console.log('Workout started at:', this.workoutStartTime);
        },
        async endWorkout() {
            clearInterval(this.updateInterval);
            const workoutEndTime = new Date();
            const workoutDuration = workoutEndTime - this.workoutStartTime;
            console.log('Workout ended at:', workoutEndTime);
            console.log('Workout duration:', workoutDuration);

            this.isWorkoutInProgress = false;
            this.workoutStartTime = null;

            const durationInMilliseconds = new Date() - this.workoutStartTime;

            // Format the duration in hours and minutes
            const hours = Math.floor(durationInMilliseconds / 3600000);
            const minutes = Math.floor((durationInMilliseconds % 3600000) / 60000);
            const seconds = Math.floor((durationInMilliseconds % 60000) / 1000);

            // Format the duration
            const formattedDuration = `${hours}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;

            try {
                const response = await WorkoutService.saveWorkout({
                    duration: formattedDuration,
                    date: new Date().toISOString(),
                });
                console.log('Workout saved successfully:', response.data);
            } catch (error) {
                console.error('Error saving workout:', error);
            }

            // Reset workout state
            this.isWorkoutInProgress = false;
            this.workoutStartTime = null;
        },
        updateLiveDuration() {
            // Update live duration while the workout is in progress
            const currentDuration = new Date() - this.workoutStartTime;
            const minutes = Math.floor((currentDuration % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((currentDuration % (1000 * 60)) / 1000);

            this.liveWorkoutDuration = `${minutes}:${seconds.toString().padStart(2, '0')}`;
        },
        goToEquipment() {
            this.$router.push('/equipmentlog');
        },

        goToGymCalendar() {
            this.$router.push('/calendar');
        },
        logout() {
            this.$store.commit('LOGOUT');
            this.$store.commit('SET_USER', { lastLoginDate: null });
            this.$router.push('/login');
        },
        // viewProfile() {
        //     // Implement logic to navigate to the profile update page
        //     // You can use Vue Router to navigate to the profile update page
        //     this.$router.push({ name: 'my-profile' });
        // },
        viewPersonalMetrics() {
            this.$router.push('/member-metrics');
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

.clock {
    border: 1px solid #ccc;
    padding: auto;
    margin: auto;
    font-size: 20px;
    text-align: center;
    background-color: whitesmoke;
}
</style>
