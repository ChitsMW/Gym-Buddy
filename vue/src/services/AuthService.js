import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  registerEmployee(employee) {
    return axios.post('/register/newEmployee', employee)
  }

}
