<template>
  <h3>Logging out...</h3>
</template>

<script>

export default {
  name: "LogoutComponent",
  created() {
    this.logout();
  },
  methods: {
    logout() {
      localStorage.removeItem('token');
      console.log(localStorage.getItem('token'))
      this.$router.push('/login');
    }
  },
}
</script>

<style scoped>

</style>