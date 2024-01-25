<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <h1>Equipment Usage Report</h1>
        <form @submit.prevent="fetchEquipmentList">
            <label for="selectedYear">Select Year:</label>
            <input type="number" v-model="selectedYear" id="selectedYear" required />
            <br>
            <label for="selectedMonth">Select Month:</label>
            <select v-model="selectedMonth" id="selectedMonth" required>
                <option value="01">January</option>
                <option value="02">February</option>
                <option value="03">March</option>
                <option value="04">April</option>
                <option value="05">May</option>
                <option value="06">June</option>
                <option value="07">July</option>
                <option value="08">August</option>
                <option value="09">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select>
            <!-- <label for="selectedMonth">Select Month:</label>
            <input type="month" v-model="selectedMonth" id="selectedMonth" required /> -->
            <button type="submit">Fetch Data</button>
        </form>
        <div v-if="equipmentUsageData.length > 0">
            <table>
                <thead>
                    <tr>
                        <th>Equipment Name</th>
                        <th>Total Reps</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="equipment in equipmentUsageData" :key="equipment.date">
                        <td>{{ equipment.equipmentName }}</td>
                        <td>{{ equipment.totalReps }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-else>
            <p>No equipment usage data available for the selected month.</p>
        </div>
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
            selectedYear: '',
            selectedMonth: '', // Use a date format suitable for month selection
            equipmentUsageData: [],
            loading: false
        };
    },
    mounted() {
        this.fetchEquipmentList();
    },
    methods: {
        async fetchEquipmentList() {
            try {
                this.loading = true;
                const response = await EquipmentService.getMonthlyUsage(this.selectedYear, this.selectedMonth);
                console.log('API Response:', response.data);
                // Use a Set to store unique equipment names
                const uniqueEquipmentNamesSet = new Set();
                // Filter out duplicates based on equipment_name
                this.equipmentUsageData = response.data.filter(equipment => {
                    if (!uniqueEquipmentNamesSet.has(equipment.equipmentName)) {
                        uniqueEquipmentNamesSet.add(equipment.equipmentName);
                        return true;
                    }
                    return false;
                });
                console.log('Selected Year:', this.selectedYear);
                console.log('Selected Month:', this.selectedMonth);
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
