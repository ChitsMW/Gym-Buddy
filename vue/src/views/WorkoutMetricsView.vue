<template>
    <button @click="goToEmployeeDashboard">Return to Dashboard</button>
    <div>
        <h1>Workout Metrics</h1>

        <!-- Display workout metrics based on user type -->
        <div v-if="userRole === 'ROLE_USER'">
            <h2>Your Workout Metrics</h2>
            <p>Time spent at the gym: {{ userWorkoutMetrics.timeSpent }}</p>
            <h3>Equipment Used</h3>
            <ul>
                <li v-for="equipment in userWorkoutMetrics.equipmentUsed" :key="equipment.name">
                    {{ equipment.name }} - Reps: {{ equipment.reps }}, Weight: {{ equipment.weight }}
                </li>
            </ul>
        </div>

        <div v-else-if="userRole === 'ROLE_EMPLOYEE'">
            <h2>Member Workout Metrics</h2>
            <p>Select a gym member to view their workout metrics:</p>
            <!-- Display a list of gym members -->
            <ul>
                <li v-for="member in gymMembers" :key="member.id" @click="viewMemberWorkoutMetrics(member.id)">
                    {{ member.name }}
                </li>
            </ul>

            <!-- Display selected member's workout metrics -->
            <div v-if="selectedMemberWorkoutMetrics">
                <h3>Time spent at the gym: {{ selectedMemberWorkoutMetrics.timeSpent }}</h3>
                <h4>Equipment Used</h4>
                <ul>
                    <li v-for="equipment in selectedMemberWorkoutMetrics.equipmentUsed" :key="equipment.name">
                        {{ equipment.name }} - Reps: {{ equipment.reps }}, Weight: {{ equipment.weight }}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>
 
<script>
export default {
    data() {
        return {
            userRole: 'ROLE_USER', // Replace with actual user role from Vuex store
            userWorkoutMetrics: {
                timeSpent: '2 hours', // Replace with actual user workout metrics from Vuex store
                equipmentUsed: [
                    { name: 'Treadmill', reps: 0, weight: 0 },
                    // Add more equipment data as needed
                ],
            },
            gymMembers: [
                { id: 1, name: 'User1' },
                { id: 2, name: 'User2' },
                // Add more gym member data as needed
            ],
            selectedMemberWorkoutMetrics: null,
        };
    },
    methods: {
        viewMemberWorkoutMetrics(memberId) {
            // Fetch and set the workout metrics for the selected member
            // Replace this with an API call or Vuex store action
            // Example:
            // this.selectedMemberWorkoutMetrics = fetchMemberWorkoutMetrics(memberId);
        },
        goToEmployeeDashboard() {
            this.$router.push('/employee-dashboard');
        },
    },
};
</script>
 
<style>
/* Add styles as needed */
</style>