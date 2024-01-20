import axios from 'axios';


const http = axios.create({
    baseURL: 'http://localhost:9000',
});


export default {
    logUsage(workoutName, dateTime, numReps, weight) {
        return http.post('/equipment-usage/log', {
            workoutName,
            dateTime,
            numReps,
            weight,
        });
    },
    // Add other methods as needed
};
