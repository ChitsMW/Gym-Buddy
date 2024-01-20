<template>
    <div class="profile-detail">
        <div v-if="profile">
            <h2 id="profile-header">My Profile</h2>
            <div class="details">
                <div id="edit-button">
                    <edit-profile-form v-bind:profile-from="profile" />
                </div>
                <div>
                    Name: {{ profile.name }}
                </div>
                <div>
                    Email: {{ profile.email }}
                </div>
                <div>
                    Photo: {{ profile.photo_url }}
                </div>
                <div>
                    Age: {{ profile.age }}
                </div>
                <div>
                    Height: {{ profile.height }}
                </div>
                <div>
                    Weight: {{ profile.weight }}
                </div>
                <div>
                    Experience: {{ profile.experience_level }}
                </div>
            </div>

        </div>

        <div id="create-button" v-if="!profile">
            <create-profile-form />
        </div>
    </div>
</template>

<script>
import ProfileService from '../services/ProfileService';
import EditProfileForm from './EditProfileForm.vue';
import CreateProfileForm from './CreateProfileForm.vue';

export default {
    name: 'profile-detail',
    components: {
        EditProfileForm,
        CreateProfileForm
    },
    data() {
        return {
            showForm: false,
            profile: {
                name: '',
                email: '',
                photo_url: '',
                age: '',
                height: '',
                weight: '',
                experience_level: '',
            },
            profile_id: '',
            submitErrors: false,
            submitErrorMsg: 'There was a problem submitting the profile.',
        }
    },
    methods: {
        toggleShowForm() {
            this.showForm = !this.showForm;
        },
        saveProfile() {
            ProfileService.createUserProfile(this.$store.state.user.id, this.profile)
                .then((response) => {
                    this.profile_id = response.data.profileId;
                    this.$router.push({ name: 'userdashboard' })
                })
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
        },
    },
    created() {
        ProfileService.getProfileByUserId(this.$route.params.userId).then((response) => {
            this.profile = response.data;
        })
    }
}



</script>