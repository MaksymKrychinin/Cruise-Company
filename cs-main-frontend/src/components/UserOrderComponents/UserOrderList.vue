<template>
  <div class="cruise-ship-list">
    <UserOrder v-for="order in userOrderList" v-bind:key="order.id" :user-order="order"/>
  </div>
</template>

<script>
import UserOrder from "@/components/UserOrderComponents/UserOrder.vue";
export default {
  name: "UserOrderList",
  components: {UserOrder},
  props: {
    userOrderList: Array
  }
}
</script>

<style scoped>
.cruise-ship-list{
  min-height: 680px;
}
</style>