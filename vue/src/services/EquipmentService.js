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
    fetchEquipmentList() {
        return http.get('/equipment/list');
    },
    getRepsFromEquipment(equipmentId) {
        return http.get(`/equipment/totalReps/${equipmentId}`)
    },
    async addReps(equipmentId, reps) {
        try {
            return await http.put(`/equipment/addReps/${equipmentId}`, reps, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
        } catch (error) {
            console.error('Error adding reps:', error);
            throw error;
        }
    },
    getMonthlyUsage(month,year){
        return http.get(`/equipment/usage/${year}/${month}`)
    },
   


    // Add other methods as needed
};































// import axios from 'axios';


// const http = axios.create({
//     baseURL: 'http://localhost:9000',
// });


// export default {
//     logUsage(workoutName, dateTime, numReps, weight) {
//         return http.post('/equipment-usage/log', {
//             workoutName,
//             dateTime,
//             numReps,
//             weight,
//         });
//     },
//     fetchEquipmentList() {
//         return http.get('/equipment/list');
//     },
//     getRepsFromEquipment(equipmentId) {
//         return http.get(`/equipment/totalReps/${equipmentId}`)
//     },
//     async addReps(equipmentId, reps) {
//         try {
//             return await http.put(`/equipment/addReps/${equipmentId}`, reps, {
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//             });
//         } catch (error) {
//             console.error('Error adding reps:', error);
//             throw error;
//         }
//     },
   


//     // Add other methods as needed
// };
