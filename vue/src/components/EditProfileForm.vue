<template>
    <button v-on:click="toggleShowForm()" class="edit-profile-btn">Edit Profile</button>
    <form action="#" v-on:submit.prevent="" v-show="showForm">
        <h1>Modify Profile Details</h1>
        <div role="alert" v-if="submitErrors">
            {{ submitErrorMsg }}
        </div>
        <div>
            <label for="name">Name: </label>
            <input type="text" id="name" v-model="profile.name">
        </div>
        <div>
            <label for="email">Email: </label>
            <input type="text" id="email" v-model="profile.email">
        </div>
        <div>
            <label for="photo-url">Photo url: </label>
            <input type="text" id="photo-url" v-model="profile.photo_url">
        </div>
        <div>
            <label for="age">Age: </label>
            <input type="text" id="age" v-model.number="profile.age">
        </div>
        <div>
            <label for="height">Height: </label>
            <input type="text" id="height" v-model.number="profile.height">
        </div>
        <div>
            <label for="weight">Weight: </label>
            <input type="text" id="weight" v-model.number="profile.weight">
        </div>
        <div>
            <label for="experience">Experience Level: </label>
            <input type="radio" id="beginner" value="Beginner" v-model="profile.experience_level">
            <label for="beginner">Beginner</label>

            <input type="radio" id="intermediate" value="Intermediate" v-model="profile.experience_level">
            <label for="intermediate">Intermediate</label>

            <input type="radio" id="advanced" value="Advanced" v-model="profile.experience_level">
            <label for="advanced">Advanced</label>
        </div>
        <div>
            <input type="submit" v-on:click="modifyProfile()" value="Submit">
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
                experience_level: '',
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