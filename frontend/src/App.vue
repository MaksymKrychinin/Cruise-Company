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
    }
  },
}
</script>

<style>

</style>
