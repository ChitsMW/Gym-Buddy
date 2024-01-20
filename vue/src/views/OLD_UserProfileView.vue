<!-- <template>
    <div>
        <form v-on:submit.prevent="saveProfile" >
            <h1>Create Profile</h1>
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
                <input type="text" id="experience" v-model="profile.experience_level">
            </div>

            <div>
                <button type="submit">Create Account</button>
            </div>

        </form>
    </div>
</template>


<script>
import ProfileService from '../services/ProfileService';

export default {
    data() {
        return {
            profile: {
                name: "",
                email: "",
                photo_url: "",
                age: "",
                height: "",
                weight: "",
                experience_level: "",
            },
            profile_id: "",
            submitErrors: false,
            submitErrorMsg: 'There was a problem submitting the profile.',
        };
    },
    methods: {
        saveProfile() {
            ProfileService.createUserProfile(this.$store.state.user.id, this.profile)
                .then((response) => {
                    this.profile_id = response.data.profileId;
                    this.$router.push({ name: 'home' })
                })
                .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
        },
        modifyProfile() {
            ProfileService.editUserProfile(this.$store.state.user.id, this.profile)
            .then((response) => {
                this.$router.push({name: 'home'})
            })
            .catch((error) => {
                    const response = error.response;
                    this.registrationErrors = true;
                    if (response.status === 400) {
                        this.registrationErrorMsg = 'Bad Request: Validation Errors';
                    }
                });
        }
    }
}
</script> -->