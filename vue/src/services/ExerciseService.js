import axios from 'axios';


const http = axios.create({
    baseURL: 'http://localhost:9000',
});


export default {

    getExerciseLogById(logId) {
        return http.get(`/log/{${logId}}`)
    },
    addNewExerciseLog(exerciseLog) {
        return http.post(`/log/newExercise`, exerciseLog)
    },
    getExerciseLogBySessionId(sessionId){
        return http.get(`/log/gym/${sessionId}`)
    },
    getExerciseLogByUserId(userId){
        return http.get(`/log/list/${userId}`)
    }
};






























// import axios from 'axios';


// const http = axios.create({
//     baseURL: 'http://localhost:9000',
// });


// export default {


//     getExerciseLogById(logId) {
//         return http.get(`/log/{${logId}}`)
//     },
//     addNewExerciseLog(exerciseLog) {
//         return http.post(`/log/newExercise`, exerciseLog)
//     }
// };
