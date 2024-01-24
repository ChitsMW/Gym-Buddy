<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <h1>Gym Visit Metrics</h1>
        <h2>Your Gym Visits</h2>
        <div>
            <h2>Fetch User Metrics</h2>
            <form @submit.prevent="fetchUserVisitMetrics">
                <label for="userId">Enter User ID:</label>
                <input type="text" id="userId" v-model="inputUserId" />
                <button type="submit">Fetch Visit Metrics</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Visit Duration in minutes</th>
                    <th>Equipment</th>
                    <th>Reps</th>
                    <th>Weight</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="log in logMetrics" :key="log.sessionId">
                    <td>{{ log.date }}</td>
                    <td>{{ log.duration }}</td>
                    <td>{{ log.equipmentName }}</td>
                    <td>{{ log.reps }}</td>
                    <td>{{ log.weight }}</td>
                </tr>
            </tbody>
        </table>
        <p>Average Duration in minutes:{{ averageDuration }}</p>
        <div v-if="isEmployeeOrAdmin">
            <h2>All User Metrics</h2>
            <table>
                <!--Table content for 'ROLE_EMPLOYEE' and 'ROLE_ADMIN'-->
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Visit Duration in minutes</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="session in userMetrics" :key="session.sessionId">
                        <td>{{ session.date }}</td>
                        <td>{{ session.duration }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
import WorkoutService from '../services/WorkoutService';
import ExerciseService from '../services/ExerciseService';
export default {
    data() {
        return {
            logMetrics: [],
            userMetrics: [], // Array to store USER metrics data
            inputUserId: '', // Store the user input
            averageDuration: 0,
        };
    },
    computed: {
        isEmployeeOrAdmin() {
            const userRole = this.$store.state.user.authorities[0].name;
            return userRole === 'ROLE_EMPLOYEE' || userRole === 'ROLE_ADMIN';
        },
    },
    mounted() {
        // Fetch visit metrics data when the component is mounted
        this.fetchUserVisitMetrics();
        //Check user role before calling
        const userRole = this.$store.state.user.authorities[0].name;
        if (userRole === 'ROLE_EMPLOYEE' || userRole === 'ROLE_ADMIN')
            this.fetchAllUserMetrics();
    },
    methods: {
        async fetchUserVisitMetrics() {
            if (!this.inputUserId) {
                return;
            }
            try {
                this.loading = true;
                // console.log('Fetching visit metrics for userId:', this.inputUserId);
                // const response = await WorkoutService.fetchVisitMetrics(this.inputUserId);
                // console.log('Full Response:', response);
                // this.visitMetrics = response.data;
                // console.log('Visit:', this.visitMetrics);
                console.log('Fetching exercise log for userId:', this.inputUserId);
                const logMetricsResponse = await ExerciseService.getExerciseLogByUserId(this.inputUserId);
                console.log('Log Metrics Response:', logMetricsResponse);
                this.logMetrics = logMetricsResponse.data;
                console.log('Log Metrics:', this.logMetrics);
            } catch (error) {
                console.error('Error fetching visit metrics:', error);
            } finally {
                this.loading = false;
            }
        },


        async fetchAllUserMetrics() {
            try {
                this.loading = true;
                const response = await WorkoutService
                    .fetchAllUserMetrics();
                console.log('Full Response:', response);
                this.userMetrics = response.data;
                console.log('Metrics:', this.userMetrics);
            } catch (error) {
                console.error('Error fetching metrics:', error);
            } finally {
                this.loading = false;
            }
        },
        // Helper function to format date
        formatDate(date) {
            // Implement date formatting logic (e.g., using a date formatting library)
            return date;
        },
        // Helper function to format duration (convert milliseconds to HH:MM:SS)
        formatDuration(duration) {
            const hours = Math.floor(duration / 3600000);
            const minutes = Math.floor((duration % 3600000) / 60000);
            const seconds = Math.floor((duration % 60000) / 1000);
            return `${hours}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
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
        calculateAverageDuration() {
            if (this.logMetrics.length === 0) {
                this.averageDuration = null;
                return;
            }


            //Create a map to group durations by date
            const durationsByDate = new Map();
            this.logMetrics.forEach((log) => {
                // Format the date to consider only the date part without time
                const formattedDate = log.date.split('T')[0];


                // Check if the date already exists in the map
                if (durationsByDate.has(formattedDate)) {
                    // If the date exists, add the duration to the existing array
                    durationsByDate.get(formattedDate).push(log.duration);
                } else {
                    // If the date doesn't exist, create a new array with the duration
                    durationsByDate.set(formattedDate, [log.duration]);
                }
            });
            // Calculate the average duration for each date
            const averageDurations = [];
            durationsByDate.forEach((durations, date) => {
                const totalDuration = durations.reduce((total, duration) => total + duration, 0);
                const averageDuration = totalDuration / durations.length;
                averageDurations.push({ date, averageDuration });
            });
            // Calculate the overall average duration
            const totalAverageDuration =
                averageDurations.reduce((total, entry) => total + entry.averageDuration, 0) /
                averageDurations.length;


            this.averageDuration = totalAverageDuration.toFixed(2);
        },
    },
    watch: {
        logMetrics: {
            handler: 'calculateAverageDuration',
            immediate: true,
        },
    },
};
</script>
<style>
td {
    text-align: center;
}
</style>























<!-- <template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <h1>Gym Visit Metrics</h1>
        <h2>Your Gym Visits</h2>
        <div>
            <h2>Fetch User Metrics</h2>
            <form @submit.prevent="fetchUserVisitMetrics">
                <label for="userId">Enter User ID:</label>
                <input type="text" id="userId" v-model="inputUserId" />
                <button type="submit">Fetch Visit Metrics</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Visit Duration in minutes</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="session in visitMetrics" :key="session.userId">
                    <td>{{ session.date }}</td>
                    <td>{{ session.duration }}</td>
                </tr>
            </tbody>
        </table>
        <div v-if="isEmployeeOrAdmin">
            <h2>All User Metrics</h2>
            <table>
                Table content for 'ROLE_EMPLOYEE' and 'ROLE_ADMIN'
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Visit Duration in minutes</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="session in userMetrics" :key="session.sessionId">
                        <td>{{ session.date }}</td>
                        <td>{{ session.duration }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
 
<script>
import WorkoutService from '../services/WorkoutService';
export default {
    data() {
        return {
            visitMetrics: [], // Array to store VISIT metrics data
            userMetrics: [], // Array to store USER metrics data
            inputUserId: '', // Store the user input
        };


    },
    computed: {
        isEmployeeOrAdmin() {
            const userRole = this.$store.state.user.authorities[0].name;
            return userRole === 'ROLE_EMPLOYEE' || userRole === 'ROLE_ADMIN';
        },
    },
    mounted() {
        // Fetch visit metrics data when the component is mounted
        this.fetchUserVisitMetrics();


        //Check user role before calling
        const userRole = this.$store.state.user.authorities[0].name;
        if (userRole === 'ROLE_EMPLOYEE' || userRole === 'ROLE_ADMIN')


            this.fetchAllUserMetrics();
    },
    methods: {
        async fetchUserVisitMetrics() {
            if (!this.inputUserId) {
                alert('Please enter a user ID.');
                return;
            }
            try {
                this.loading = true;
                console.log('Fetching visit metrics for userId:', this.inputUserId);
                const response = await WorkoutService.fetchVisitMetrics(this.inputUserId);
                console.log('Full Response:', response);
                this.visitMetrics = response.data;
                console.log('Visit:', this.visitMetrics);
            } catch (error) {
                console.error('Error fetching visit metrics:', error);
            } finally {
                this.loading = false;
            }
        },
        async fetchAllUserMetrics() {
            try {
                this.loading = true;
                const response = await WorkoutService
                    .fetchAllUserMetrics();
                console.log('Full Response:', response);
                this.userMetrics = response.data;
                console.log('Metrics:', this.userMetrics);
            } catch (error) {
                console.error('Error fetching metrics:', error);
            } finally {
                this.loading = false;
            }
        },
        // Helper function to format date
        formatDate(date) {
            // Implement date formatting logic (e.g., using a date formatting library)
            return date;
        },
        // Helper function to format duration (convert milliseconds to HH:MM:SS)
        formatDuration(duration) {
            const hours = Math.floor(duration / 3600000);
            const minutes = Math.floor((duration % 3600000) / 60000);
            const seconds = Math.floor((duration % 60000) / 1000);
            return `${hours}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
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
td {
    text-align: center;
}
</style> -->