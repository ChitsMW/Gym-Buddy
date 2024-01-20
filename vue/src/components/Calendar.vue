<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>
    <div>
        <div ref="calendar" class="calendar"></div>
    </div>
</template>
 
<script>
import { Calendar } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';

export default {
    mounted() {
        const calendar = new Calendar(this.$refs.calendar, {
            plugins: [dayGridPlugin],
            // header: {
            //     left: 'prev, next today',
            //     center: 'title',
            //     right: 'dayGridMonth, timeGridWeek, timeGridDay',
            // },
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
            eventClick: this.handleEventClick,
            // Additional configuration options
        });
        calendar.render();
    },
    methods: {
        handleEventClick(info) {
            // 'info.event' contains the event object, including any additional properties you've added
            const registrationLink = info.event.extendedProps.registrationLink;
            if (registrationLink) {
                // Redirect to the registration link or perform any registration logic
                window.location.href = registrationLink;
            } else {
                // Handle the case where there's no registration link
                console.log('No registration link available for this event.');
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
    }
};
</script>


<style>
.calendar {
    background-color: whitesmoke;

}
</style>