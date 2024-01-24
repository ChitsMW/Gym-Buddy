<template>
 
  <div class="background-container">
  <!-- <img class="banner" src="src\assets\banner.jpg" alt="banner"/> -->


 
  <div id="login">
 
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
        <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
</div>

</template>


<script>
import authService from "../services/AuthService";




export default {
components: {},
data() {
  return {
    user: {
      username: "",
      password: ""
    },
    invalidCredentials: false
  };
},
methods: {
  login() {
    this.user.username = this.user.username.toLowerCase();
    this.user.password = this.user.password.toLowerCase();
    authService
      .login(this.user)
      .then(response => {
        if (response.status === 200) {
          const user = response.data.user;
          user.lastLoginDate = new Date().toLocaleString();




          // Log the user object to the console
          console.log('Logged-in user:', user);








          const role = user.authorities && user.authorities.length > 0
            ? user.authorities[0].name
            : null;








          // Set user information in the store
          this.$store.commit("SET_AUTH_TOKEN", response.data.token);
          this.$store.commit("SET_USER", user);
          this.$store.commit("setLoggedIn", true);






          // Determine the dashboard route based on the user's role
          let dashboardRoute = '/userdashboard';








          if (role === 'ROLE_EMPLOYEE') {
            dashboardRoute = '/employee-dashboard';
          } else if (role === 'ROLE_ADMIN') {
            dashboardRoute = '/admin-dashboard';
          }




          // Log the selected dashboard route
          console.log('Selected dashboard route:', dashboardRoute);








          // Redirect to the appropriate dashboard
          this.$router.push(dashboardRoute);
        }
      })
      .catch(error => {
        const response = error.response;




        if (response.status === 401) {
          this.invalidCredentials = true;
        }
      });


  }
}
};
</script>


<style scoped>
/* Add styling for your logo and form elements here */
body {
/* Set background image properties for the entire page */
/* Do not repeat the background image */
min-height: 100vh;
/* Ensure the background covers the full viewport height */
font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
box-sizing: border-box;
margin: 0;
/* Other styles specific to the #app element */
}
#login {
/* Other styles specific to the login box */
max-width: 400px;
margin: 0 auto;
padding: 20px;
box-shadow: 0 0 10px rgba(192, 191, 191, 0.884);
background-color: rgba(255, 255, 255, 0.5);;
border-radius: 75px;
align-items: center;
justify-content: center;
text-align: center;
/* color: rgb(12, 12, 12); */
}
.background-container  {
background-image: url('/src/assets/iStock-871070868.jpg');
background-size: cover;
/* Cover the entire page */
background-position: center;
/* Center the background image */
background-repeat: no-repeat;
}
.form-input-group {
margin-bottom: 1rem;
}
.register{color: white;}
h1 {
  color: white;
  padding-top: 5rem;
}
label {
margin-right: 0.5rem;
color: white;
/* color: rgb(10, 10, 10); */
font-weight: bold;
}
/* img {
  max-width: 200px;
  display: block;
  margin-bottom: 20px;
  } */
</style>
<!-- <style scoped>
/* Add styling for your logo and form elements here */
#login {
  /* Example styles, customize as needed */
  max-width: 400px;
  margin: 0 auto;
}
img {
  max-width: 100%;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style> -->