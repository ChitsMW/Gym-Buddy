<template>
  <Header>
    <div class="banner">

      <div>
        <img class="header-logo" src="/src/assets/Logo_4.png" alt="Logo" />

      </div>



    </div>
  </Header>

  <!-- <div class="wrapper">
    <div class="container"> -->
      <!-- content here -->
      <div id="nav">
      <button v-if="loggedIn && !workoutStarted" @click="startWorkout" ><i class="fa-solid fa-dumbbell" style="margin-right: 3px;"></i>Start Workout</button>
      <button v-if="loggedIn && workoutStarted" @click="endWorkout"><i class="fa-solid fa-stopwatch fa-beat"></i>End Workout</button>
      <p v-if="loggedIn">Workout Duration: {{ liveWorkoutDuration }}</p>
    </div>
      <router-view />
    <!-- </div>
  </div> -->

  <div id="capstone-app">

    <footer>
      <p>
        © 2024 Gym Buddy Inc.. All rights reserved. Cookie policy, Privacy and Terms.
      </p>


    </footer>

  </div>
</template>
<script>
import WorkoutService from './services/WorkoutService';
export default {
  data() {
    return {
      isLoggedIn: false,
      workoutStarted: false,
      liveWorkoutDuration: 0,
      workoutStartTime: null,
      updateInterval: null,
    };
  },
  computed: {
    loggedIn() {
      const loggedIn = this.$store.state.loggedIn;
      console.log('Is user logged in?', loggedIn);
      return loggedIn;
    },
  },
  methods: {
    startWorkout() {
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
      console.log('Workout ended at:', workoutEndTime);
      console.log('Workout duration:', workoutDuration);
      this.workoutStarted = false;
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
      this.counterInstance.reset(); //Reset the counter when the workout ends
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
  }
};
</script>
<style>
/* body {
  margin: 0;
  padding: 0;
  min-height: 100vh;
  display: flex;
  flex-direction: column;

 
} */

#nav {
  text-align: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}


button {
  margin: auto;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-radius: 75px;
  /* Adjust the border-radius as needed for rounded corners */
  background-color: rgba(189, 236, 150, 0.9);
  /* Add your desired background color */
  color: #444343;
  /* Set text color to improve visibility */
  cursor: pointer;
  box-shadow: 0 0 10px rgba(20, 20, 20, 0.589);
}
i{
    margin: 12px;
    padding: 12px;
}

button:hover {
  background-color: #3d6e8f;
  /* Change background color on hover */
}

/* img {
  max-width: 200px;
  display: block;
  margin-bottom: 20px;
  border-radius: 25px;
} */
.header-logo {
  max-width: 200px;
  display: block;
  margin-bottom: 20px;
  padding-top: 25px;
}

header {
  display: flex;
  justify-content: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #ddeed6;
  /* Set your desired background color */
  width: 100%;
  color: #fff;
  /* Set text color for better visibility */
  text-align: center;
  /* Adjust text alignment if needed */
  padding: 20px;
  /* Add padding as needed */
  /* box-shadow: 0 0 10px rgba(20, 20, 20, 0.589); */

}

footer {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #333;
  /* Set your desired background color */
  width: 100%;
  color: #fff;
  /* Set text color for better visibility */
  padding: 10px;
  text-align: center;
  position: relative;
  bottom: 0;


}



#app {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: "nav"
    /* "view" */
    "footer";
  /* position: relative; */
  /* height: 100vh; */
  gap: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

h1 {
  font-size: 2rem;
}

/* .wrapper {
  padding: 20px;
} */

.header {
  display: flex;
  align-items: center;
  justify-content: center;

  height: 200px;
  background: url('~@/assets/banner.jpg') no-repeat center center;
  background-size: 100% 100%;

}

.banner {
  height: 200px;
  /* background-image: url('@/assets/banner.jpg'); */
  background-size: cover;
}




@media (max-width: 500px) {
  h1 {
    font-size: 1.5rem;
  }
}

@media (max-width: 768px) {
  .button-grid {
    grid-template-columns: 1fr;
  }
}

@media print {
  .noprint {
    display: none;
  }
}
</style>
