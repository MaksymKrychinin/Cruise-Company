<template>
  <header v-if="token!=null">
    <img src="@/assets/images/logo.png" width="50" height="50">
    <router-link to="/cruise-ships">Cruise Ships</router-link>
    <router-link to="/user-orders">My Orders</router-link>
    <router-link to="/logout">Logout</router-link>
  </header>
  <header v-else>
    <img src="@/assets/images/logo.png" width="50" height="50">
    <router-link to="/">Home</router-link>
    <router-link to="/login">Login</router-link>
    <router-link to="/register">Register</router-link>
  </header>
</template>
<script>
export default {
  name: "HeaderComponent",
  props: {
    token: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
header {
  display: flex;
  width: 100%;
  height: 50px;
  color: black;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  background-color: #ffb552;
}

</style>