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
    getMonthlyUsage(year,month){
        const formattedMonth = month.padStart(2, '0');
        return http.get(`/equipment/usage/${year}/${formattedMonth}`)
    }
   


    // Add other methods as needed
};

