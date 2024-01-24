<template>
  <header>
    <div class="banner">

      <div>
        <img class="header-logo" src="/src/assets/Logo_4.png" alt="Logo" />

      </div>


    </div>
  </header>

  <div id="nav">
    <div v-if="loggedIn">
      <workout-timer />
    </div>
  </div>
  <router-view />

  <div id="capstone-app">

    <footer>
      <p>
        © 2024 Gym Buddy Inc.. All rights reserved. Cookie policy, Privacy and Terms.
      </p>


    </footer>

  </div>
</template>

<script>
// import WorkoutService from './services/WorkoutService';
// import GymSessionService from './services/GymSessionService';
import WorkoutTimer from './components/WorkoutTimer.vue';


export default {
  components: {
    WorkoutTimer
  },
  data() {
    return {
      isLoggedIn: false,
    }
  },
  computed: {
    loggedIn() {
      const loggedIn = this.$store.state.loggedIn;
      console.log('Is user logged in?', loggedIn);
      return loggedIn;
    },
  },
}
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

i {
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
  /* background: url('~@/assets/banner.jpg') no-repeat center center; */
  background-size: 100% 100%;

}

/* .banner {
  height: 200px;
  background-image: url('@/assets/banner.jpg');
  background-size: cover;
} */


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
