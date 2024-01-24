<template>
  <div class="home">


    <!-- <h1>Welcome to Gym Buddy!</h1>
    <img  src="/src/assets/iStock-1189093701.jpg" alt="Logo" />
     <img  src="/src/assets/group.jpg" alt="Logo" />
    <p>You must be authenticated to see this</p>
    <router-link to="/login">Go to Login</router-link>
  </div>
   -->

  



  <body>
  <header>
    
    <h1>Are you ready to get started?</h1>
    
    <button @click="$router.push('/login')"><i class="fa-solid fa-right-from-bracket"
                                style="margin-right: 9px;"></i>Login or Sign Up</button>
  </header>
  <div id="nav">
    <div v-if="loggedIn">
      <workout-timer />
    </div>
  </div>
  <router-view />


  
</body>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
  <body class="w3-content" style="max-width:1200px">

<div class="w3-panel">
  <i class="w3-xlarge fa fa-bars"></i> 
  </div> 
  <!-- First Grid: Logo & About -->

  <div>
  <img class="header-logo" src="/src/assets/Logo_4.png" alt="Logo" />

</div>
  <div class="w3-row">
    <div class="w3-half w3-container">
      <h1 class="w3-xxlarge w3-text-light-grey">Hello</h1>
      <h1 class="w3-xxlarge w3-text-grey">Welcome to</h1>
      <h1 class="w3-jumbo" style="background-color: rgba(241, 205, 4, 0.712);">Gym Buddy!</h1>
      <h2 class="w3-xxlarge w3-text-grey" style="text-decoration-color: rgba(241, 205, 4, 0.712);">Your Fitness Companion
      </h2>
    </div>
    <div class="w3-half w3-container w3-xlarge w3-text-grey">
      <p class="">EMBRACE A #NOJUDGMENTS PHILOSOPHY</p>
      <p>We are nurturers: we seek only to encourage, entertain, and empower.</p>



    </div>
  </div>

  <!-- Second Grid: Resent -->
  <div class="w3-panel w3-text-grey">
    <h4>A PLACE TO BELONG:</h4>
  </div>
  <div class="w3-row">
    <div class="w3-half w3-container">
      <img src="@/assets/group.jpg" style="width:100%">
      <br>
      <p>Here we keep open minds. There is no one type or way in our diverse community. Come as you are!</p>
      <br>
    </div>
    <br>
    <div class="w3-half w3-container">
      <br>
      <img src="@/assets/banner.jpg" style="width:100%">
      <br>
      <p>We know serious fitness is hard, but that doesn’t mean it can’t be an edge-of-your-seat, can’t-get-enough,
        look-forward-to-your-workouts party.</p>
      <p class="w3-xlarge w3-text-grey">
        COME AS YOU ARE AND CRUSH YOUR GOALS!</p>
    </div>

    <div>
      <!-- <h1>Are you ready to get started?</h1> -->

      <button @click="$router.push('/login')"><i class="fa-solid fa-right-from-bracket"
          style="margin-right: 9px;"></i>Login or Sign Up</button>
    </div>
  </div>

  <!-- Footer -->
  <div class="w3-row w3-section">
    <div class="w3-third w3-container w3-black w3-large" style="height:250px">
      <h2>Contact Info</h2>
      <p><i class="fa fa-map-marker" style="width:30px"></i> Philadelphia, US</p>
      <p><i class="fa fa-phone" style="width:30px"></i> Phone: +00 151515</p>
      <p><i class="fa fa-envelope" style="width:30px"> </i> Email: mail@mail.com</p>
    </div>
    <div class="w3-third w3-center w3-large w3-dark-grey w3-text-white" style="height:250px">
      <h2>Contact Us</h2>
      <p>If you have an idea.</p>
      <p>What are you waiting for?</p>
    </div>
    <div class="w3-third w3-center w3-large w3-grey w3-text-white" style="height:250px">
      <h2>Like Us</h2>
      <i class="fa-brands fa-facebook"></i><br>
      <i class="fa-brands fa-pinterest"></i><br>
      <i class="fa-brands fa-x-twitter"></i><br>
      <i class="fa-brands fa-flickr"></i><br>
      <i class="fa-brands fa-linkedin"></i>
    </div>
  </div>
  <div class="w3-container w3-text-grey">
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">gym buddy</a></p>
  </div>

</body>
</div>
</template>

<script>
export default {

  goToLogin() {

    this.$router.push("/login");
  }
};
</script>

<style scoped>

.header-logo {
  max-width: 200px;
  display: block;
  margin-bottom: 20px;
  padding-top: 25px;
}

html {
  box-sizing: border-box
}

*,
*:before,
*:after {
  box-sizing: inherit
}

/* Extract from normalize.css by Nicolas Gallagher and Jonathan Neal git.io/normalize */
html {
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%
}

body {
  margin: 0
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
main,
menu,
nav,
section {
  display: block
}

summary {
  display: list-item
}

audio,
canvas,
progress,
video {
  display: inline-block
}

progress {
  vertical-align: baseline
}

audio:not([controls]) {
  display: none;
  height: 0
}

[hidden],
template {
  display: none
}

a {
  background-color: transparent
}

a:active,
a:hover {
  outline-width: 0
}

abbr[title] {
  border-bottom: none;
  text-decoration: underline;
  text-decoration: underline dotted
}

b,
strong {
  font-weight: bolder
}

dfn {
  font-style: italic
}

mark {
  background: #ff0;
  color: #000
}

small {
  font-size: 80%
}

sub,
sup {
  font-size: 75%;
  line-height: 0;
  position: relative;
  vertical-align: baseline
}

sub {
  bottom: -0.25em
}

sup {
  top: -0.5em
}

figure {
  margin: 1em 40px
}

img {
  border-style: none
}

code,
kbd,
pre,
samp {
  font-family: monospace, monospace;
  font-size: 1em
}

hr {
  box-sizing: content-box;
  height: 0;
  overflow: visible
}

button,
input,
select,
textarea,
optgroup {
  font: inherit;
  margin: 0
}

optgroup {
  font-weight: bold
}

button,
input {
  overflow: visible
}

button,
select {
  text-transform: none
}

button,
[type=button],
[type=reset],
[type=submit] {
  -webkit-appearance: button
}

button::-moz-focus-inner,
[type=button]::-moz-focus-inner,
[type=reset]::-moz-focus-inner,
[type=submit]::-moz-focus-inner {
  border-style: none;
  padding: 0
}

button:-moz-focusring,
[type=button]:-moz-focusring,
[type=reset]:-moz-focusring,
[type=submit]:-moz-focusring {
  outline: 1px dotted ButtonText
}

fieldset {
  border: 1px solid #c0c0c0;
  margin: 0 2px;
  padding: .35em .625em .75em
}

legend {
  color: inherit;
  display: table;
  max-width: 100%;
  padding: 0;
  white-space: normal
}

textarea {
  overflow: auto
}

[type=checkbox],
[type=radio] {
  padding: 0
}

[type=number]::-webkit-inner-spin-button,
[type=number]::-webkit-outer-spin-button {
  height: auto
}

[type=search] {
  -webkit-appearance: textfield;
  outline-offset: -2px
}

[type=search]::-webkit-search-decoration {
  -webkit-appearance: none
}

::-webkit-file-upload-button {
  -webkit-appearance: button;
  font: inherit
}

/* End extract */

.header-logo {
  max-width: 200px;
  display: block;
  margin-bottom: 20px;
  padding-top: 25px;
}

html,
body {
  font-family: Verdana, sans-serif;
  font-size: 15px;
  line-height: 1.5
}

html {
  overflow-x: hidden
}

h1 {
  font-size: 36px
}

h2 {
  font-size: 30px
}

h3 {
  font-size: 24px
}

h4 {
  font-size: 20px
}

h5 {
  font-size: 18px
}

h6 {
  font-size: 16px
}

/*  */

.w3-bar {
  width: 100%;
  overflow: hidden
}

.w3-center .w3-bar {
  display: inline-block;
  width: auto
}



.w3-bar-block.w3-center .w3-bar-item {
  text-align: center
}



.w3-container:after,
.w3-container:before,
.w3-panel:after,
.w3-panel:before,
.w3-row:after,
.w3-row:before,
.w3-row-padding:after,
.w3-row-padding:before,
.w3-cell-row:before,
.w3-cell-row:after,
.w3-clear:after,
.w3-clear:before,
.w3-bar:before,
.w3-bar:after {
  content: "";
  display: table;
  clear: both
}


.w3-content {
  max-width: 980px
}



.w3-container,
.w3-panel {
  padding: 0.01em 16px
}

.w3-panel {
  margin-top: 16px;
  margin-bottom: 16px
}


.w3-large {
  font-size: 18px !important
}

.w3-xlarge {
  font-size: 24px !important
}

.w3-xxlarge {
  font-size: 36px !important
}


.w3-jumbo {
  font-size: 64px !important
}


.w3-center {
  text-align: center !important
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













