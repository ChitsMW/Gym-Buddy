<template>
    <div id="dashboard-button">
        <button @click="goToUserDashboard">Return to Dashboard</button>
        <!--Add the button for update the schedule-->
        <button v-if="isEmployeeOrAdmin" @click="toggleFormVisibility">Update Schedule</button>
    </div>




    <div>
        <div ref="calendar" class="calendar"></div>
    </div>






    <body>
        <img src="@/assets/class.gif">
    </body>


    <!--Add form for adding a class-->
    <div v-if="isFormVisible" class="modal">
        <div class="modal-content">
            <span class="close" @click="hideForm">&times;</span>
            <h2>Add Class to Schedule</h2>
            <form @submit.prevent="submitForm">
                <!--Form fields go here-->


                <label for="className">Class Name:</label>
                <input type="text" id="className" v-model="newClass.name" required />


                <label for="classTime">Class Time:</label>
                <input type="datetime-local" id="classTime" v-model="newClass.time" required />


                <button type="submit">Add Class</button>
            </form>
        </div>
    </div>


    <!--Popup form for registering for a class-->
    <div v-if="showRegistrationForm" class="modal">
        <div class="modal-content">
            <span class="close" @click="hideRegistrationForm">&times;</span>
            <h2>Register for Class</h2>
            <!--Registration form fields go here-->
            <form @submit.prevent="submitRegistrationForm">
                <label for="userName">Your Name:</label>
                <input type="text" id="userName" v-model="userName" required />
                <button type="submit">Register</button>
            </form>
        </div>
    </div>
</template>
 


<script>
import { Calendar } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';


export default {
    data() {
        return {
            isFormVisible: false,
            showRegistrationForm: false,
            newClass: {
                name: '',
                time: '',
            },
            userName: '',
            events: [
                {
                    title: 'Beginner Pilates',
                    start: '2024-01-18T18:30:00',


                },
                {
                    title: 'Beginner Pilates',
                    start: '2024-01-25T18:30:00',


                },
                {
                    title: 'Group Weights',
                    start: '2024-01-31T10:00:00',


                },
                {
                    title: 'Intro to Stretching',
                    start: '2024-02-15T08:00:00',


                },
                {
                    title: 'Intermediate Yoga',
                    start: '2024-02-10T14:00:00',


                },
                // Add more events as needed
            ],
        };
    },
    mounted() {
        const calendar = new Calendar(this.$refs.calendar, {
            plugins: [dayGridPlugin],
            events: this.events,
            eventClick: this.handleEventClick,
            // Additional configuration options
        });
        // calendar.render();
        this.renderCalendar(calendar);
    },


    methods: {
        handleEventClick(info) {
        this.showRegistrationForm = true;
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
        submitForm() {
            //Implement logic for adding the class to the schedule
            console.log('Class added:', this.newClass);
            this.events.push({
                title: this.newClass.name,
                start: this.newClass.time,
            });
            this.renderCalendar();
            //Reset form fields
            this.newClass.name = '';
            this.newClass.time = '';
            //Hide the form
            this.hideForm();
        },
        updateSchedule() {
            console.log('Schedule updated!');
        },
        renderCalendar() {
            const calendar = new Calendar(this.$refs.calendar, {
                plugins: [dayGridPlugin],
                events: this.events,
                eventClick: this.handleEventClick,
            });
            calendar.render();
        },
        toggleFormVisibility() {
            this.isFormVisible = !this.isFormVisible;
        },
        hideForm() {
            this.isFormVisible = false;
        },
        hideRegistrationForm() {
            this.showRegistrationForm = false;
        },
        submitRegistrationForm() {
            alert('Hooray, you Rockstar! Your Registration Was Successful!  See You In Class!')
            console.log('Registered for class:', this.userName);
            this.hideRegistrationForm();
        },
    },
    computed: {
        isEmployeeOrAdmin() {
            const userRole = this.$store.state.user.authorities[0].name;
            return userRole === 'ROLE_EMPLOYEE' || userRole === 'ROLE_ADMIN';
        },
    },
};
</script>


<style>
.modal {
    display: block;
    position: fixed;
    z-index: 1;
    left: 0;
    right: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}


.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}


.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
}


.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
}


.calendar {
    background-color: whitesmoke;
    width: 100%;
    margin: 0 auto;


    /* display: flex; */
    /* align-items: center; */
    /* justify-content: center; */
    /* margin-bottom: 20px; */




}


/* body { */
/* box-shadow: 0 4px 10px rgba(5, 5, 5, 0.589); */
/* background: url('@/assets/iStock-871070868.jpg') no-repeat center center fixed; */
/* background-size: 100%;   */
/* background-color: rgba(236, 234, 234, 0.9);
  background-size: 50%;
  background-color: rgb(255, 254, 254);
   */
/* border-radius: 25px; */
/* } */
#dashboard-button {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 20px;




}
</style>