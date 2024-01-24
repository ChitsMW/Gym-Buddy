import axios from 'axios';


const http = axios.create({
    baseURL: 'http://localhost:9000',
});


export default {
    getGymSessionBySessionId(sessionId) {
        return http.get(`/session/${sessionId}`)
    },
    getGymSessionsByUserId(userId) {
        return http.get(`/session/${userId}`)
    },
    getAllGymSessions() {
        return http.get('/session/list')
    },
    addNewGymSession(gymSession) {
        console.log('Request Payload:', gymSession);
        return http.post('/session/add', gymSession, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },
    addDuration(sessionId, duration) {
        return http.put(`/session/endSession/${sessionId}`, duration, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }
};
