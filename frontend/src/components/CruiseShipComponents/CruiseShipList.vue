<template>
  <div>
    <CruiseShip v-for="ship in shipsList" v-bind:key="ship.id" :cruise-ship="ship"/>
  </div>
</template>

<script>
import CruiseShip from "@/components/CruiseShipComponents/CruiseShip";
import axios from "axios";

export default {
  name: "CruiseShipList",
  components: {CruiseShip},
  data() {
    return {
      shipsList: [CruiseShip]
    }
  },
  beforeMount() {
    this.getAllCruiseShips();
  },
  methods: {
    async getAllCruiseShips() {
      const item = localStorage.getItem("token");
      const axiosResponse = await axios.get("api/v1/cruise-ships", {
            headers: {
              'Authorization': `Bearer ${item}`
            },
            params: {
              page: 0,
              size: 5
            }
          }
      );
      console.log(axiosResponse.data)
      this.shipsList = axiosResponse.data.content;
    }
  }

}
</script>

<style scoped>

</style>