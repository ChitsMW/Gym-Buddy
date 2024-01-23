import axios from 'axios';


const http = axios.create({
    baseURL: 'http://localhost:9000',
});


export default {
    // saveWorkout(workoutData) {
    //     return http.post('/workouts', workoutData);
    // },
    fetchAllUserMetrics(){
        return http.get('/session/list');
    },
    fetchVisitMetrics(userId) {
        return http.get(`/session/user/${userId}`);
    },


    // Add more methods for other workout-related API calls
};
