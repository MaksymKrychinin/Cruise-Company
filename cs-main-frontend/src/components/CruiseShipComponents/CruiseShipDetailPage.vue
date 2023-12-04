<template>
  <div class="cruise-ship">
    <h3>{{ cruiseShip.routeFrom }} → {{ cruiseShip.routeTo }}</h3>
    <p>Available seats: {{ availableSeats }}</p>
    <p>Start date: {{ formattedStartDate }}</p>
    <p>End date: {{ formattedEndDate }}</p>
  </div>
  <router-link :to="{name: 'user-order-rent-cruise-ship-by-id', params: {id: this.cruiseShip.id}}">
    <button>Buy ticket</button>
  </router-link>
</template>

<script>
import axios from "axios";

export default {
  name: "CruiseShipDetailPage",
  props: {
    cruiseShipId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      cruiseShip: {
        routeFrom: "",
        routeTo: "",
        capacity: 0,
        orderedSeats: 0,
        startDate: "",
        endDate: "",
      },
    };
  },
  beforeMount() {
    this.getCruiseShipsById();
  },
  methods:{
    async getCruiseShipsById() {
      const item = localStorage.getItem("token");
      const axiosResponse = await axios.get(`api/v1/cruise-ships/${this.cruiseShipId}`, {
            headers: {
              'Authorization': `Bearer ${item}`
            },
          }
      );
      this.cruiseShip = axiosResponse.data;
    },
  },
  computed: {
    availableSeats() {
      return this.cruiseShip.capacity - this.cruiseShip.orderedSeats;
    },
    formattedStartDate() {
      return new Date(this.cruiseShip.startDate).toLocaleDateString();
    },
    formattedEndDate() {
      return new Date(this.cruiseShip.endDate).toLocaleDateString();
    },
  },
};
</script>

<style scoped>

</style>