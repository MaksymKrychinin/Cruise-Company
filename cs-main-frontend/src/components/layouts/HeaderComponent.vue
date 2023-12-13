<template>
  <header v-if="token!=null">
    <img src="@/assets/images/logo.png" width="60" height="70">
    <router-link to="/cruise-ships"><p>Cruise Ships</p></router-link>
    <router-link to="/user-orders"><p>My Orders</p></router-link>
    <router-link to="/admin-panel"
    v-if="role('admin')"><p>Admin Panel</p></router-link>
    <router-link to="/logout"><p>Logout</p></router-link>
  </header>
  <header v-else>
    <img src="@/assets/images/logo.png" width="50" height="50">
    <router-link to="/"><p>Home</p></router-link>
    <router-link to="/login"><p>Login</p></router-link>
    <router-link to="/register"><p>Register</p></router-link>
  </header>
</template>
<script>

import {role} from "@/main";

export default {
  name: "HeaderComponent",
  props: {
    token: {
      type: String,
      required: true
    },
  },
  methods: {
    role,
  },
}

</script>

<style scoped>
header {
  display: flex;
  width: 100%;
  height: 80px;
  color: black;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  background-color: #659B5E;
}

a {
  font-size: 20px;
  font-weight: bolder;
  text-decoration: none;
  color: black;
}
</style>