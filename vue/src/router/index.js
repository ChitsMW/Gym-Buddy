import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
// import UserProfileView from '../views/UserProfileView.vue';
import AdminDashboardView from '../views/AdminDashboardView.vue';
import AdminTasksView from '../views/AdminTasksView.vue';
import UserDashboardView from '../views/UserDashboardView.vue';
import ProfileView from '../views/ProfileView.vue';
import EquipmentView from '../views/EquipmentView.vue';
import Machine1View from '../views/Machine1View.vue';
import Machine2View from '../views/Machine2View.vue';
import Machine3View from '../views/Machine3View.vue';
import Machine4View from '../views/Machine4View.vue';
import Machine5View from '../views/Machine5View.vue';
import Machine6View from '../views/Machine6View.vue';
import Machine7View from '../views/Machine7View.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/admin-dashboard',
    name: 'admin-dashboard',
    component: AdminDashboardView,
    meta: {
      requiresAuth: true,
      isAdmin: true, // Additional check for admin role
  }},
  {
    path: '/admin-tasks',
    name: 'admin-tasks',
    component: AdminTasksView,
    meta: {
      requiresAuth: true,
      isAdmin: true, // Additional check for admin role
  }},
  {
    path: "/userdashboard",
    name: "userdashboard",
    component: UserDashboardView,
   },
   {
    path: "/my-profile/:userId",
    name: "my-profile",
    component: ProfileView,
   },
   {
    path: '/equipmentlog',
    name: 'equipmentlog',
    component: EquipmentView,
  },
  {
    path: '/machine1',
    name: 'machine1',
    component: Machine1View,
  },
  {
    path: '/machine2',
    name: 'machine2',
    component: Machine2View,
  },
  {
    path: '/machine3',
    name: 'machine3',
    component: Machine3View,
  },
  {
    path: '/machine4',
    name: 'machine4',
    component: Machine4View,
  },
  {
    path: '/machine5',
    name: 'machine5',
    component: Machine5View,
  },
  {
    path: '/machine6',
    name: 'machine6',
    component: Machine6View,
  },
  {
    path: '/machine7',
    name: 'machine7',
    component: Machine7View,
  },

  // {
  //   path: "/profile",
  //   name: "profile",
  //   component: UserProfileView,
  // },
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
