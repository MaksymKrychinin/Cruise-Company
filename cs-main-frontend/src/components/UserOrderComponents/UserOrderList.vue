<template>
  <div class="user-order-list">
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
.user-order-list{
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  min-height: 680px;
}
</style>