import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
})

export default {
    createUserProfile(userId, userProfile) {
        return http.post(`/profile/create/${userId}`, userProfile)
    },
    getProfileByProfileId(profileId) {
        return http.get(`/profile/${profileId}`)
    },
    getProfileByUserId(userId) {
        return http.get(`/profile/member/${userId}`)
    },
    editUserProfile(profileId, userProfile) {
        return http.put(`/profile/${profileId}`, userProfile)
    },
    getProfileIdByUserId(userId) {
        return http.get(`/profile/profileIdOfUser${userId}`)
    }
}