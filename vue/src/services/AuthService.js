import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
})

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  registerEmployee(employee) {
    return axios.post('/register/newEmployee', employee)
  },

  fetchUserList() {
    return http.get("/users");
  },

}
