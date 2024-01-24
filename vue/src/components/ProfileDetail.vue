<template>
    <button @click="goToUserDashboard">Return to Dashboard</button>

    <div class="profile-detail">
        <div v-if="profile">
            <h1 id="profile-header">My Profile</h1>
            <div class="details">
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            {{ profile.name }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Email:
                        </td>
                        <td>
                            {{ profile.email }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Age:
                        </td>
                        <td>
                            {{ profile.age }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Height (inches):
                        </td>
                        <td>
                            {{ profile.height }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Weight (lbs):
                        </td>
                        <td>
                            {{ profile.weight }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Experience Level:
                        </td>
                        <td>
                            {{ profile.experienceLevel }}
                        </td>
                    </tr>
                </table>
            </div>


        <div id="edit-button">
            <edit-profile-form v-bind:profile-from="profile" />
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
                profileId: '',
                name: '',
                email: '',
                photo_url: '',
                age: '',
                height: '',
                weight: '',
                experienceLevel: '',
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
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
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
    },
    created() {
        ProfileService.getProfileByUserId(this.$route.params.userId).then((response) => {
            this.profile = response.data;
        })
    }
}
</script>


<style>
.profile-detail{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column; /* Display children in a column */
    /* height: 100vh; */
    /* margin: 0; */
}

#edit-button{
    text-align: center;
    padding: 20px;
}

table{
    width: 80%;
    margin: auto;
}

</style>
