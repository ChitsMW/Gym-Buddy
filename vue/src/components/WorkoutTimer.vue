<template>
    <div id="nav">
        <button v-if="!workoutStarted" @click="startWorkout"><i class="fa-solid fa-dumbbell" style="margin-right: 3px;"></i>Start Workout</button>
        <button v-if="workoutStarted" @click="endWorkout"><i class="fa-solid fa-stopwatch fa-beat" style="margin-right: 5px;"></i>End Workout</button>
        <p>Workout Duration: {{ liveWorkoutDuration }}</p>
    </div>
</template>


<script>
import WorkoutService from '../services/WorkoutService';
import GymSessionService from '../services/GymSessionService';


export default {
    name: 'workout-timer',
    data() {
        return {
            isLoggedIn: false,
            workoutStarted: false,
            liveWorkoutDuration: 0,
            workoutStartTime: null,
            updateInterval: null,
            currentDate: new Date(),
            gymSession: {
                userId: this.$store.state.user.id,
                date: ''
            },
            currentGymSessionId: '',
        };
    },
    computed: {
        loggedIn() {
            const loggedIn = this.$store.state.loggedIn;
            console.log('Is user logged in?', loggedIn);
            return loggedIn;
        },
        formatDate() {
            const utcYear = this.currentDate.getUTCFullYear();
            const utcMonth = (this.currentDate.getUTCMonth() + 1).toString().padStart(2, '0');
            const utcDay = this.currentDate.getUTCDate().toString().padStart(2, '0');
            return `${utcYear}-${utcMonth}-${utcDay}`;
        }
    },
    methods: {
        initializeGymSession() {
            this.gymSession.date = this.formatDate;
        },
        startWorkout() {
            console.log("Started workout")
            GymSessionService.addNewGymSession(this.gymSession)
                .then((response) => {
                    this.gymSession = response.data;
                    this.currentGymSessionId = response.data.sessionId;
                    this.$store.commit("setCurrentGymSessionId", this.currentGymSessionId);
                    console.log(this.currentGymSessionId);
                    console.log(this.$store.state.currentGymSessionId)
                })
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    console.log("An error occurred.")
                });


            //  *** 1/21 THIS WORKS MOVING ON TO endWorkout() ***
            console.log('startWorkout method called');
            this.workoutStartTime = new Date();
            this.workoutStarted = true;
            this.updateInterval = setInterval(this.updateLiveDuration, 1000);
            console.log('Workout started at:', this.workoutStartTime);
        },
        async endWorkout() {
            // *** 1/21 THIS WORKS, BUT RETURNS AN ERROR: 'Error Saving Workout: Error: Network Error' ***
            // *** 1/21 MOVING ON TO updateLiveDuration() ***
            console.log('endWorkout method called');
            if (this.updateInterval) {
                clearInterval(this.updateInterval);
            }
            const workoutEndTime = new Date();
            const workoutDuration = workoutEndTime - this.workoutStartTime;
            const workoutDurationInMinutes = workoutDuration / (1000 * 60);
            const roundedDurationInMinutes = Math.round(workoutDurationInMinutes);
            console.log('Workout ended at:', workoutEndTime);
            console.log('Workout duration:', roundedDurationInMinutes);
            GymSessionService.addDuration(this.$store.state.currentGymSessionId, roundedDurationInMinutes)
                .then((response) => response.data)
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    console.log("An error occurred.")
                });
            this.$store.commit("setCurrentGymSessionId", null);
            this.workoutStarted = false;
            this.workoutStartTime = null;
            this.liveWorkoutDuration = '0:00';
            this.isWorkoutInProgress = false;
        },
        updateLiveDuration() {
            // *** 1/21 THIS WORKS GOING TO COMMENT OUT CLOCK INFORMATION IN DASHBOARDS ***
            console.log('updateLiveDuration method called');
            //Update live duration while the workout is in progress
            const currentDuration = new Date() - this.workoutStartTime;
            const minutes = Math.floor((currentDuration % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((currentDuration % (1000 * 60)) / 1000);
            this.liveWorkoutDuration = `${minutes}:${seconds.toString().padStart(2, '0')}`;
        }
    },
    created() {
        this.initializeGymSession();
        console.log('user id:' + this.$store.state.user.id)
    }
};
</script>


<style>
#nav {
    text-align: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}


#workout-duration {
    text-align: right;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    /* padding-right: 40px; */
}


button {
    margin: 0.5rem;
    padding: 0.5rem 1rem;
    font-size: 1rem;
    border-radius: 75px;
    /* Adjust the border-radius as needed for rounded corners */
    background-color: rgba(189, 236, 150, 0.603);
    /* Add your desired background color */
    color: #444343;
    /* Set text color to improve visibility */
    cursor: pointer;
}


button:hover {
    background-color: #50a345;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    /* Change background color on hover */
}


.header-logo {
    max-width: 200px;
    display: block;
    margin-bottom: 20px;
    padding-top: 25px;
}


header {
    display: flex;
    justify-content: center;
}


h1 {
    font-size: 2rem;
}


.wrapper {
    padding: 20px;
}


.header {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 200px;
    background: url('~@/assets/banner.jpg') no-repeat center center;
    background-size: cover;
}


.banner {
    height: 200px;
    background-image: url('@/assets/banner.jpg');
    background-size: cover;
}


@media (max-width: 500px) {
    h1 {
        font-size: 1.5rem;
    }
}
</style>
