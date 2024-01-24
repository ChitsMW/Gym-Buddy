<template>
    <!-- <Header>
        <div class="banner">
            <img src="/src/assets/Logo_4.png" alt="Logo" />
        </div>
        
    </Header> -->
    <!-- <div>
        <button @click="goToUserDashboard">Return to Dashboard</button>
    </div> -->
    <div id="dashboard-button">
            <button @click="goToUserDashboard">Return to Dashboard</button>
        </div>

    <!-- code for adding  reps and weight to your session -->

    <!-- <section class="workout-selector">
        <div>
            <label for="workout">Workout/Machine:</label>
            <select id="workout" name="workout" v-model="workoutName">
                <option value="chestPress">Chest Press</option>
                <option value="legExtension">Leg Extension</option>
                <option value="cableCurl">Cable Curl</option>
                <option value="seatedCrunch">Seated Crunch</option>
                <option value="chinUp">Assisted Underhand Chin Up</option>
                <option value="horizontalLegPress">Horizontal Leg Press</option>
                <option value="hipAbduction">Seated Hip Abduction/Adduction</option>
            </select>


            <div v-for="(section, index) in sections" :key="index">
                <label :for="'reps' + index">Number of Reps:</label>
                <input type="number" :id="'reps' + index" v-model="section.numReps">


                <label :for="'weight' + index">Weight:</label>
                <input type="number" :id="'weight' + index" v-model="section.weight">


                Button to remove the current section
                <button @click="removeSection(index)">Remove</button>
            </div>


            Button to add a new section
            <button @click="addSection">Add More Reps and Weight</button>


            <button @click="logEquipmentUsage">Log Usage</button>
        </div>


    </section> -->



    <body>
        <div>

            <h1>Explore the Equipment</h1>

            <div id="machine-list">
                <h3>Leg Extension Machine</h3>
                <router-link to="/machine1">
                    <img src="@/assets/machine1.jpg" alt="Machine Image 1" class="machine-image">
                </router-link>




                <h3>Abdominal Machine</h3>
                <router-link to="/machine2">
                    <img src="@/assets/machine2.jpg" alt="Machine Image 2" class="machine-image">
                </router-link>




                <h3>Leg Press Machine</h3>
                <router-link to="/machine3">
                    <img src="@/assets/machine3.jpg" alt="Machine Image 3" class="machine-image">
                </router-link>




                <h3>Chin-Dip Assist Machine</h3>
                <router-link to="/machine4">
                    <img src="@/assets/machine4.jpg" alt="Machine Image 4" class="machine-image">
                </router-link>




                <h3>Chest Press Machine</h3>
                <router-link to="/machine5">
                    <img src="@/assets/machine5.jpg" alt="Machine Image 5" class="machine-image">
                </router-link>




                <h3>Functional Trainer Machine</h3>
                <router-link to="/machine6 ">
                    <img src="@/assets/machine6.jpg" alt="Machine Image 6" class="machine-image">
                </router-link>




                <h3>Abductor- Adductor Machine</h3>
                <router-link to="/machine7">
                    <img src="@/assets/machine7.jpg" alt="Machine Image 7" class="machine-image">
                </router-link>
            </div>


            <!-- <div class="machine-grid">
                <h3>Leg Extension Machine</h3>
                <router-link to="/machine1">
                    <img src="@/assets/machine1.jpg" alt="Machine Image 1" class="machine-image">
                </router-link>

                <h3>Abdominal Machine</h3>
                <router-link to="/machine2">
                    <img src="@/assets/machine2.jpg" alt="Machine Image 2" class="machine-image">
                </router-link>

                <h3>Leg Press Machine</h3>
                <router-link to="/machine3">
                    <img src="@/assets/machine3.jpg" alt="Machine Image 3" class="machine-image">
                </router-link>

                <h3>Chin-Dip Assist Machine</h3>
                <router-link to="/machine4">
                    <img src="@/assets/machine4.jpg" alt="Machine Image 4" class="machine-image">
                </router-link>

                <h3>Chest Press Machine</h3>
                <router-link to="/machine5">
                    <img src="@/assets/machine5.jpg" alt="Machine Image 5" class="machine-image">
                </router-link>

                <h3>Functional Trainer Machine</h3>
                <router-link to="/machine6 ">
                    <img src="@/assets/machine6.jpg" alt="Machine Image 6" class="machine-image">
                </router-link>

                <h3>Abductor- Adductor Machine</h3>
                <router-link to="/machine7">
                    <img src="@/assets/machine7.jpg" alt="Machine Image 7" class="machine-image">
                </router-link>
            </div> -->
        </div>
    </body>
</template>
   

<script>
export default {
    data() {
        return {
            workoutName: '',
            sections: [{ numReps: 0, weight: 0 }],
        };
    },
    methods: {
        logEquipmentUsage() {
            // Implement logic to send this data to the backend
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
        addSection() {
            this.sections.push({ numReps: 0, weight: 0 });
        },
        removeSection(index) {
            this.sections.splice(index, 1);
        },
        
        

    },
};
</script>

<style scoped>
.machine-grid {
    max-width: 600px auto;

  /* display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px; */
}

.machine-image {
    max-width: 350px;
}

.machine-image {
    flex: 0 0 50%;
}

img {
    max-width: 200px;
    /* width: 120px;
  height: 100px;  */
}

body {
    background-color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    box-shadow: 0 0 10px rgba(20, 20, 20, 0.884);
    padding: 20px;
    /* Add padding on all sides */
    margin: 20px;
    /* Add margin on all sides */
    border-radius: 25px;
    /* Adjust the border-radius as needed for rounded corners */
    background-color: rgba(248, 248, 248, 0.603);
    /* Add your desired background color */
    color: #444343;
    /* Set text color to improve visibility */
    align-items: center;

}

.workout-selector {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: rgb(192, 194, 192);
    box-shadow: 0 0 10px rgba(20, 20, 20, 0.884);
    padding: 20px;
    /* Add padding on all sides */
    margin: 20px;
    /* Add margin on all sides */
    border-radius: 25px;
    /* Adjust the border-radius as needed for rounded corners */
    background-color: rgba(241, 205, 4, 0.712);
    /* Add your desired background color */
    color: #444343;
    /* Set text color to improve visibility */
    align-items: center;
  justify-content: center;
  text-align: center;

}
#machine-list {
    display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  max-width: 100%; /* Adjust as needed */
  margin: 0 auto;
  padding: 2px;


}



.banner {
    height: 200px;
    background-image: url('@/assets/banner.jpg');
    background-size: cover;
}
h1 {
    text-align: center;
    padding-top: 19px;


}


.header {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 200px;
    background: url('~@/assets/banner.jpg') no-repeat center center;
    background-size: cover;
}

.logo {
    justify-self: center;
    margin-left: auto;
    /* Move the logo to the right */
    /* box-shadow: 0 0 10px rgba(245, 241, 241, 0.884); */
}
#dashboard-button {
    display: flex;
    align-items: center;
    justify-content: center;


}



/* 2 columns on screens above 768px */
@media (min-width: 768px) {
    .machine-grid {
        flex: 1 0 50%;
    }
}

/* 1 column on small screens */
@media (max-width: 480px) {
    .machine-image {
        flex: 0 0 100%;
    }
}
</style>

<!-- <style scoped>
.machine-grid {
    max-width: 600px;
    /* display: flex;
  flex-wrap: wrap;
  gap: 20px; */
}

.machine-image {
    max-width: 350px;
}

.machine-image {
    flex: 0 0 50%;
}

img {
    max-width: 200px;
    /* width: 120px;
  height: 100px;  */
}

body {
    background-color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.banner {
    height: 200px;
    background-image: url('@/assets/banner.jpg');
    background-size: cover;
}

.header {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 200px;
    background: url('~@/assets/banner.jpg') no-repeat center center;
    background-size: cover;
}

/* 2 columns on screens above 768px */
@media (min-width: 768px) {
    .machine-grid {
        flex: 1 0 50%;
    }
}

/* 1 column on small screens */
@media (max-width: 480px) {
    .machine-image {
        flex: 0 0 100%;
    }
}
</style> -->
