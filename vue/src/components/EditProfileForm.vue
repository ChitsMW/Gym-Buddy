<template>
    <button v-on:click="toggleShowForm()" class="edit-profile-btn">Edit Profile</button>
    <form action="#" v-on:submit.prevent="" v-show="showForm">
        <h2>Modify Profile Details</h2>
        <div role="alert" v-if="submitErrors">
            {{ submitErrorMsg }}
        </div>
        <div class="form-group">
            <label for="name">Name: </label>
            <input type="text" id="name" v-model="profile.name">
        </div>
        <div class="form-group">
            <label for="email">Email: </label>
            <input type="text" id="email" v-model="profile.email">
        </div>
        <div class="form-group">
            <label for="age">Age: </label>
            <input type="text" id="age" v-model.number="profile.age">
        </div>
        <div class="form-group">
            <label for="height">Height (in inches): </label>
            <input type="text" id="height" v-model.number="profile.height">
        </div>
        <div class="form-group">
            <label for="weight">Weight (in pounds): </label>
            <input type="text" id="weight" v-model.number="profile.weight">
        </div>
        <div class="form-group">
            <label for="experience">Experience Level: </label>
            <input type="radio" id="beginner" value="Beginner" v-model="profile.experienceLevel">
            <label for="beginner">Beginner</label>

            <input type="radio" id="intermediate" value="Intermediate" v-model="profile.experienceLevel">
            <label for="intermediate">Intermediate</label>

            <input type="radio" id="advanced" value="Advanced" v-model="profile.experienceLevel">
            <label for="advanced">Advanced</label>
        </div>
        <div>
            <input type="submit" v-on:click="modifyProfile()" value="Submit" id="submit-button">
        </div>
    </form>
</template>

<script>
import ProfileService from '../services/ProfileService';

export default {
    props: {
        profileFrom: Object,
    },
    data() {
        return {
            showForm: false,
            profile: {
                profile_id: '',
                user_id: '',
                name: '',
                email: '',
                photo_url: '',
                age: '',
                height: '',
                weight: '',
                experienceLevel: '',
            },
            submitErrors: false,
            submitErrorMsg: 'There was a problem submitting the profile.',
            id: this.$route.params.userId,
            // current_profile_id: this.getProfileIdByUserId(),
        }
    },
    computed: {

    },
    methods: {
        toggleShowForm() {
            this.showForm = !this.showForm;
            this.profile = this.profileFrom;
        },
        clear() {
            this.profile = {

            };
        },
        getProfileId() {
            ProfileService.getProfileIdByUserId(this.id)
                .then((response) => {
                    return response.data;
                })
                .catch((error) => {
                    console.log(error.response.statusText);
                })
        },
        modifyProfile() {
            ProfileService.editUserProfile(this.profileFrom.profileId, this.profile)
                .then((response) => {
                    this.profile = response.data;
                    alert("Profile updated successfully. Returning to dashboard.")
                    const userRole = this.$store.state.user.authorities[0].name;
                    if (userRole === 'ROLE_ADMIN') {
                        this.$router.push('/admin-dashboard');
                    } else if (userRole === 'ROLE_EMPLOYEE') {
                        this.$router.push('/employee-dashboard');
                    } else if (userRole === 'ROLE_USER') {
                        this.$router.push('/userdashboard');
                    }
                })
                .catch((error) => {
                    if (error.response) {
                        this.errorMsg = "Error editing profile. Response received was '" + error.response.statusText + "'";
                    } else if (error.request) {
                        this.errorMsg = "Error editing profile. Server could not be reached.";
                    } else {
                        this.errorMsg = "Error editing profile. Request could not be reached.";
                    }
                });
        },
    }
}


</script>

<style>
.input-label {
    width: 100px;
    display: inline-block;
}

.form-group {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 10px;
}

input {
    margin: .2rem;
    border-radius: .5rem;
    height: 1.2rem;
}

#submit-button {
    margin: auto;
    padding: 0.5rem 1rem;
    font-size: 1rem;
    border-radius: 75px;
    /* Adjust the border-radius as needed for rounded corners */
    background-color: rgba(189, 236, 150, 0.9);
    /* Add your desired background color */
    color: #444343;
    /* Set text color to improve visibility */
    cursor: pointer;
    box-shadow: 0 0 10px rgba(20, 20, 20, 0.589);
    height: 2.5rem;
}
</style>


