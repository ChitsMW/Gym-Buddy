<template>
    <div class="sidebar" :style="{ width: sidebarWidth }">

        <h1>
            <span v-if="collapsed">

            </span>
            <span v-else> </span>
        </h1>

        <span class="collapse-icon" :class="{ 'rotate-180': collapsed }" @click="toggleSidebar">
            <i class="fas fa-angle-double-left" />
        </span>
        <SidebarLink to="/profile" icon="fa-regular fa-user">Profile</SidebarLink>
        <SidebarLink to="/login" icon="fa-solid fa-arrow-up-from-bracket">Log Out</SidebarLink>


    </div>
</template>


<script>
import SidebarLink from './SidebarLink.vue'
import { collapsed, toggleSidebar, sidebarWidth } from './state'
export default {
    props: {},
    components: { SidebarLink },
    setup() {
        return { collapsed, toggleSidebar, sidebarWidth }
    }
}
</script>


<style>
:root {
    --sidebar-bg-color: #2f855a;
    --sidebar-item-hover: #38a169;
    --sidebar-item-active: #276749;
}
</style>


<style scoped>
.sidebar {
    color: beige;
    background-color: var(--sidebar-bg-color);
    float: left;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    bottom: 0;
    padding: 0.5em;
    transition: 0.5 em;
    display: flex;
    flex-direction: column;
}


.collapse-icon {
    position: top;
    bottom: 0;
    padding: .05em;
    color: whitesmoke;
}


.rotate-180 {
    transform: rotate(180deg);
    transition: 0.2s linear;
}
</style>
