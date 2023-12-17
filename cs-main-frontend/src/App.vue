<template>
  <div>
    <HeaderComponent v-bind:token="this.token"/>
    <main>
      <router-view/>
    </main>
    <FooterComponent/>
  </div>
</template>

<script>

import HeaderComponent from "@/components/layouts/HeaderComponent";
import FooterComponent from "@/components/layouts/FooterComponent";
import {jwtDecode} from "jwt-decode";

export default {
  name: 'App',
  components: {
    FooterComponent,
    HeaderComponent
  },
  data() {
    return {
      token: null
    }
  },
  watch: {
    $route() {
      this.token = localStorage.getItem('token');
      if (this.token) {
        const jwtPayload = jwtDecode(this.token);
        new Date().getTime() / 1000 < jwtPayload.exp
            ? console.log("Token is valid")
            : this.$router.push('/logout') && localStorage.setItem('error', 'You need to log in first');
      }
    }
  },
}
</script>

<style>

</style>
