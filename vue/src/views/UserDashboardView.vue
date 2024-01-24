<template>
    <header>
        <!-- <Sidebar />
        <div :style="{ 'margin-left': sidebarWidth }">
            <router-view />
        </div> -->
    </header>


    <div class="user-profile">
        <h1>Welcome {{ formattedUsername }}!</h1>
        <h2>Enjoy your workout!</h2>
        <img src="https://i.pinimg.com/originals/ec/76/0a/ec760a2fe48b77493d39811a0a617ca2.jpg" alt="Inspiration">

        <!-- Buttons for different actions -->

    </div>

    <body>

        <div class="button-grid">


            <!-- <button v-if="!isWorkoutInProgress" @click="startWorkout">
                <i class="fa-solid fa-dumbbell" style="margin-right: 3px;"></i> Start Workout
            </button>
            <button v-else @click="endWorkout">End Workout</button> -->
            <button @click="goToEquipment"><i class="fa-solid fa-list" style="margin-right: 9px;"></i>Equipment Log</button>
            <button @click="goToGymCalendar"> <i class="fa-regular fa-calendar-days" style="margin-right: 9px;"></i>Gym
                Calendar</button>
            <button class="button" @click="goToMyProfile"><i class="fa-solid fa-user" style="margin-right: 9px;"></i>View
                Profile</button>
            <!-- <router-link class="button"
                v-bind:to="{ name: 'my-profile', params: { userId: this.$store.state.user.id } }">View
                Profile</router-link> -->
            <button @click="viewPersonalMetrics"><i class="fa-solid fa-clock-rotate-left"
                    style="margin-right: 9px;"></i>Past Sessions</button>
            <button @click="logout"><i class="fa-solid fa-right-from-bracket" style="margin-right: 9px;"></i>Logout</button>

            <!-- <div class="clock"> -->
            <!-- <div v-if="isWorkoutInProgress"> -->
            <!-- <p>Workout Duration: {{ liveWorkoutDuration }}</p>
        </div> -->



        </div>




    </body>

    <section>
        <div>
            <h2>Featured this Week</h2>
        </div>
    </section>

    <section>

        <div>
            <img src="https://d1csarkz8obe9u.cloudfront.net/posterpreviews/zumba-class-ad-instagram-template-design-e6e0ee134a017d0f3011c7b2479e84b0_screen.jpg?ts=1636853345"
                alt="Zumba Class">
        </div>

    </section>
    <section>

        <div>
            <img src="https://static.vecteezy.com/system/resources/previews/005/464/329/original/yoga-class-illustration-isolated-concept-flat-cartoon-woman-asana-position-fitness-stretching-meditation-poster-banner-business-card-announcement-sport-lifestyle-mandala-vector.jpg"
                alt="Yoga Class">
        </div>

    </section>
</template>


<script>
import Sidebar from '@/components/sidebar/Sidebar.vue'
import { sidebarWidth } from '@/components/sidebar/state.js'
import WorkoutService from '../services/WorkoutService';


export default {
    // components: { Sidebar },
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
            if (this.$store.state.currentGymSessionId !== null) {
                alert("Please end your current gym session before logging out.")
            } else {
                this.$store.commit('LOGOUT');
                this.$store.commit('SET_USER', { lastLoginDate: null });
                this.$router.push('/login');
            }
        },
        viewPersonalMetrics() {
            this.$router.push('/member-metrics');
        },
        goToMyProfile() {
            const userId = this.$store.state.user.id;
            // Assuming 'my-profile' is the name of your route
            this.$router.push({ name: 'my-profile', params: { userId } });
        },




    }
};
</script>


<style scoped>
/* Add styling for your user profile page here */
body {
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 10px;
    background-color: rgb(236, 234, 234);
}
section{
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 10px;
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
body {
  box-shadow: 0 4px 10px rgba(5, 5, 5, 0.589);
  background: url('@/assets/weights.jpg') no-repeat center center fixed;
  background-size: 125%;
  /* background-color: rgba(236, 234, 234, 0.9);
  background-size: cover;
  background-color: rgb(236, 234, 234); */
  border-radius: 25px;
}
img {
    border-radius: 12px;
    max-width: 350px;
    height: auto;
}
.user-profile {
    /* Example styles, customize as needed */
    max-width: 400px;
    margin: 0 auto;
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
     justify-content: center;
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
h1,
h3 {
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.button-grid {
    display: grid;
    grid-template-columns: 1fr ;
    gap: 10px;
    max-width: auto;
    margin: 5px auto;
    align-items: center;
    justify-content: center;
}
/* .button-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-gap: 5px;
  margin: 70px 0;
  justify-content: center;
} */
/* .button-grid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-gap: 5px;
} */
button {
    width: 200px;
    height: 100px;
}
button {
    border-radius: 20px;
    padding: 10px;
    /* width: 200px auto;
    height: 100px; */
}
/* button {
    border-radius: 20px;
    font-weight: bold;
    font-size: 1.15rem;
} */
/* .button-grid {
    margin: 10px 0;
} */
/* button {
    padding: 10px;
} */
/* .view-profile-button {
    margin: 0.5rem;
  padding: 0.5rem 1rem;
  font-size: 1.15rem;
  font-weight: bold;
  border-radius: 20px;
  background-color: rgba(189, 236, 150, 0.603);
  color: #444343;
  cursor: pointer;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 170px;
    height: 75px;
} */
@media screen and (min-width: 600px) {
    body {
        font-size: 18px; /* Adjust font size for tablets */
    }
    .button-grid {
    grid-template-columns: repeat(2, 1fr);
}
}
/* Desktop styles */
@media screen and (min-width: 1200px) {
    body {
        font-size: 20px; /* Adjust font size for desktops */
    }
    .button-grid {
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: repeat(3, 1fr);
        /* Three equal-width columns for screens wider than 1200px */
        justify-self: stretch;
    }
  }
</style>