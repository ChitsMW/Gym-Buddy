<template>
    <div>
        <h1>Gym Visit Metrics</h1>

        <!-- Display visit metrics data -->
        <div v-if="visitMetrics.length > 0">
            <h2>Your Gym Visits</h2>
            <!-- Display visit data in a table or other visualization method -->
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Visit Duration</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="visit in visitMetrics" :key="visit.id">
                        <td>{{ formatDate(visit.date) }}</td>
                        <td>{{ formatDuration(visit.duration) }}</td>
                    </tr>
                </tbody>
            </table>

            <!-- Display average visit duration -->
            <p>Average Visit Duration: {{ formatDuration(averageVisitDuration) }}</p>
        </div>

        <div v-else>
            <p>No gym visit data available.</p>
        </div>
    </div>
</template>
 
<script>
export default {
    data() {
        return {
            visitMetrics: [], // Array to store visit metrics data
        };
    },
    computed: {
        averageVisitDuration() {
            // Calculate the average visit duration based on visitMetrics
            if (this.visitMetrics.length === 0) {
                return 0; // Default value when no data is available
            }

            const totalDuration = this.visitMetrics.reduce((sum, visit) => sum + visit.duration, 0);
            return totalDuration / this.visitMetrics.length;
        },
    },
    methods: {
        // Fetch visit metrics data from the backend
        fetchVisitMetrics() {
            // Implement logic to fetch visit metrics data
            // Example: this.visitMetrics = fetchVisitMetrics();
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
    },
    mounted() {
        // Fetch visit metrics data when the component is mounted
        this.fetchVisitMetrics();
    },
};
</script>
 
<style>
/* Add styles as needed */
</style>