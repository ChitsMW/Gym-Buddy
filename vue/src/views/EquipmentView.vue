<template>
    <Header>
        <div class="banner">
            <img src="/src/assets/Logo_4.png" alt="Logo" />
        </div>
        <div>
            <button @click="goToUserDashboard">Return to Dashboard</button>
        </div>
    </Header>

<!-- code for adding  reps and weight to your session -->


    <!-- <div>
        <label for="workout">Workout/Machine:</label>
        <select id="workout" name="workout" v-model="workoutName">
            <option value="option1">Option 1</option>
            <option value="option2">Option 2</option>
            <option value="option3">Option 3</option>
            <option value="option4">Option 4</option>
            <option value="option5">Option 5</option>
            <option value="option6">Option 6</option>
            <option value="option7">Option 7</option>
            <option value="option8">Option 8</option>
            <option value="option9">Option 9</option>
            <option value="option10">Option 10</option>
        </select>

        <div v-for="(section, index) in sections" :key="index">
            <label :for="'reps' + index">Number of Reps:</label>
            <input type="number" :id="'reps' + index" v-model="section.numReps">

            <label :for="'weight' + index">Weight:</label>
            <input type="number" :id="'weight' + index" v-model="section.weight"> 

             Button to remove the current section -->
            <!-- <button @click="removeSection(index)">Remove</button>
        </div> -->

        <!-- Button to add a new section -->
        <!-- <button @click="addSection">Add More Reps and Weight</button>

        <button @click="logEquipmentUsage">Log Usage</button>

    </div> --> 

    <body>
        <div>

            <h1>Explore the Equipment</h1>

            <div>
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
            this.$router.push('/userdashboard');
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
</style>
