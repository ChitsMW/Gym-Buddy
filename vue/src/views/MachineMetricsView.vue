<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <h1>Equipment Usage Report</h1>
            <table>
                <thead>
                    <tr>
                        <th>Equipment Name</th>
                        <th>Total Reps</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="equipment in equipmentUsageData" :key="equipment.id">
                        <td>{{ equipment.equipmentName }}</td>
                        <td>{{ equipment.totalReps }}</td>
                    </tr>
                </tbody>
            </table>
        </div>


        <!-- <div v-else>
            <p>No equipment usage data available for the selected month.</p>
        </div> -->
    <!-- </div> -->
</template>
 


<script>
import EquipmentService from '../services/EquipmentService'
export default {
    data() {
        return {
            selectedMonth: '', // Use a date format suitable for month selection
            equipmentUsageData: [],
        };
    },
    mounted() {
        this.fetchEquipmentList();
    },
    methods: {
        async fetchEquipmentList() {
            try{
                this.loading = true;
                const response = await EquipmentService
                .fetchEquipmentList();
                console.log('Full Response:', response);
                this.equipmentUsageData = response.data;
                console.log('Equipment:', this.equipmentUsageData);
            } catch (error) {
                console.error('Error fetching equipment life:', error);
            } finally {
                this.loading = false;
            }
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
    },
};
</script>
 


<style>
/* Add styles as needed */
</style>