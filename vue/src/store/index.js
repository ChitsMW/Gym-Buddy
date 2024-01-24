import { createStore as _createStore } from 'vuex';
import axios from 'axios';


export function createStore(currentToken, currentUser) {
  const storedToken = localStorage.getItem('token');
  const storedUser = JSON.parse(localStorage.getItem('user'));


  let store = _createStore({
    state: {
      token: currentToken || storedToken || '',
      user: currentUser || storedUser || {},
      currentGymSessionId: null,
      loggedIn: Boolean(storedToken),
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        state.loggedIn = false;
        state.currentGymSessionId = null;
        axios.defaults.headers.common = {};
      },
      setLoggedIn(state, loggedIn) {
        state.loggedIn = loggedIn;
      },
      setCurrentGymSessionId(state, gymSessionId) {
        state.currentGymSessionId = gymSessionId;
      }
    },
  });
  return store;
}
