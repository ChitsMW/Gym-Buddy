<template>
    <div>
        <form v-on:submit.prevent="addNewExerciseLog">
            <label for="reps">Number of Reps:</label>
            <input type="number" id="reps" name="reps" v-model="exercise.reps">


            <label for="weight">Weight:</label>
            <input type="number" id="weight" name="weight" v-model="exercise.weight">
            <button type="submit">Log Exercise</button>
        </form>
    </div>
</template>






<script>
import ExerciseService from '../services/ExerciseService';
import EquipmentService from '../services/EquipmentService';


export default {
    props: {
        equipmentId: Number,
    },
    data() {
        return {
            exercise: {
                sessionId: this.$store.state.currentGymSessionId,
                equipmentId: this.equipmentId,
                reps: "",
                weight: "",
            },
            submitErrors: false,
            submitErrorMsg: 'There was a problem submitting the profile.',
            currentTotalReps: 0,
        };
    },
    methods: {
        fetchCurrentTotalReps() {
            EquipmentService.getRepsFromEquipment(this.equipmentId)
                .then((response) => {
                    this.currentTotalReps = response.data;
                })
                .catch((error) => {
                    console.log("Error fetching reps.")
                    const response = error.response;
                    this.submitErrors = true;
                    if (response.status === 400) {
                        this.submitErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
        },
        addNewExerciseLog() {
            if (this.$store.state.currentGymSessionId === null) {
                alert("Please start a workout before logging exercises.")
            } else {
                ExerciseService.addNewExerciseLog(this.exercise)
                    .then((response) => {
                        if (response.status === 200) {
                            this.exercise = response.data;
                            this.fetchCurrentTotalReps();
                            const newTotalReps = this.currentTotalReps + parseInt(this.exercise.reps, 10);
                            if (newTotalReps !== null) {
                                EquipmentService.addReps(this.equipmentId, newTotalReps)
                                    .then((response) => {
                                        alert("Your exercise has been logged successfully.");
                                        this.clearForm();
                                    })
                                    .catch((error) => {
                                        console.log("Error adding reps.")
                                        const response = error.response;
                                        this.registrationErrors = true;
                                        if (response.status === 400) {
                                            this.registrationErrorMsg = 'Bad Request: Validation Errors';
                                        }
                                    });
                            } else {
                                console.log("New Reps is null.")
                            }


                        }
                    })
                    .catch((error) => {
                        console.log("Error adding exercise.")
                        const response = error.response;
                        this.registrationErrors = true;
                        if (response.status === 400) {
                            this.registrationErrorMsg = 'Bad Request: Validation Errors';
                        }
                    });
            }
        },
        clearForm() {
            this.exercise = {
                sessionId: this.$store.state.currentGymSessionId,
                equipmentId: this.equipmentId,
                reps: "",
                weight: "",
            }
        }
    },
    created() {
        this.fetchCurrentTotalReps(); // Fetch the initial total reps value when the component is created
    }
}
</script>