<template>
    <!-- <Header>
        <div class="banner">

        </div>
        <img class="header-logo" src="/src/assets/Logo_4.png" alt="Logo" />
        <div>
            <button @click="goToEquipmentLog">Return to Equipment Log</button>
        </div>

    </Header> -->
    <div>
        <button @click="goToEquipmentLog">Return to Equipment Log</button>

    </div>

    <body>
        <div>
            <div class="center-content">
                <h1>Leg Extension Machine</h1>

                <img src="@/assets/machine1.gif">

                <h2>
                    Machine | Beginner | Legs

                </h2>
                <h3>Tips & Tricks</h3>
                <p>
                    * Keep your core engaged and your back and butt firmly against the pads.
                </p>
                <p>
                    *Make sure to align your knees with the pivot point of the machine.
                </p>
                <p>
                    *The pad should rest just below your shins.
                </p>

                <!-- <div>
                    <button @click="goToEquipmentLog">Ready to try it?</button>
                </div> -->

                <div class="workout-selector">
                    <add-exercise-log :equipmentId="equipmentId"/>
                </div>

            </div>

        </div>
    </body>
</template>
 

<script>
import AddExerciseLog from '../components/AddExerciseLog.vue';
export default {

    components: {AddExerciseLog},

    data() {
        return {
            equipmentId: 3,
        };

    },


    methods: {
        goToEquipmentLog() {


            this.$router.push('/equipmentlog');
        },
        logEquipmentUsage() {
            // Implement logic to send this data to the backend
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
.center-content {
    display: flex;
    flex-direction: column;
    align-items: center;
}


body {
    background-color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;


}


img {
    max-width: 500px;
}


.header-logo {
    width: 200px;
}

.header {
    display: flex;
    align-items: center;
    justify-content: center;


    height: 200px;
    background: url('~@/assets/banner.jpg') no-repeat center center;
    background-size: cover;
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
</style>
