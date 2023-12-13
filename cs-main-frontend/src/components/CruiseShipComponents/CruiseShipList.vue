<template>
  <div class="cruise-ship-list">
    <CruiseShip v-for="ship in cruiseShipList" v-bind:key="ship.id" :cruise-ship="ship"/>
  </div>
</template>
<script>
import CruiseShip from "@/components/CruiseShipComponents/CruiseShip";

export default {
  name: "CruiseShipList",
  components: {CruiseShip},
  props: {
    cruiseShipList: Array
  }
}
</script>

<style scoped>
.cruise-ship-list{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  min-height: 680px;
}
</style>