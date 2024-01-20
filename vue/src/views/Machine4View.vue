<template>
    <Header>
        <div class="banner">
        </div>
        <img class="header-logo" src="/src/assets/Logo_4.png" alt="Logo" />
        <div>
            <button @click="goToEquipmentLog">Return to Equipment Log</button>
        </div>
    </Header>

    <body>
        <div>
            <div class="center-content">
                <h1>Chin-Dip Assist Machine</h1>
                <img src="@/assets/machine4.gif">
                <h2>
                    Machine | Intermediate | Arms/Back
                </h2>
                <h3>Tips & Tricks</h3>
                <p>
                    * Keep your core engaged and your back and butt firmly against the pads.
                </p>
                <p>
                    *Make sure to align your knees with the pivot point of the machine.
                </p>
                <p>
                    *The pad should rest just below your shins.
                </p>
                <div>
                    <button @click="goToEquipmentLog">Log Usage</button>
                </div>
            </div>
        </div>
    </body>
</template>
 

<script>
export default {
    data() {
        return {};
    },
    methods: {
        goToEquipmentLog() {
            this.$router.push('/equipmentlog');
        },
        logEquipmentUsage() {
            // Implement logic to send this data to the backend
        },
    },
};
</script>


<style scoped>
.center-content {
    display: flex;
    flex-direction: column;
    align-items: center;
}


body {
    background-color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;


}


img {
    max-width: 500px;
}


.header-logo {
    width: 200px;
}

.header {
    display: flex;
    align-items: center;
    justify-content: center;


    height: 200px;
    background: url('~@/assets/banner.jpg') no-repeat center center;
    background-size: cover;
}
</style>
