<template>
<div>
  <HeaderComponent/>
  <main>
    <router-view />
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
  }
}
</script>

<style>

</style>
