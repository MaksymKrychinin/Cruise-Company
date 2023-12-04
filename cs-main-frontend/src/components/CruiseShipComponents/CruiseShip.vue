<template>
  <div class="cruise-ship">
    <h3>{{ cruiseShip.routeFrom }} → {{ cruiseShip.routeTo }}</h3>
    <p>Available seats: {{ availableSeats }}</p>
    <p>Start date: {{ formattedStartDate }}</p>
    <p>End date: {{ formattedEndDate }}</p>
    <router-link :to="{name: 'cruise-ship-detail', params: {id: this.cruiseShip.id}   }">
      <button>View Details</button>
    </router-link>
    <router-link :to="{name: 'user-order-rent-cruise-ship-by-id', params: {id: this.cruiseShip.id}   }">
      <button>Buy Ticket</button>
    </router-link>
  </div>
</template>

<script>

export default {
  name: "CruiseShip",
  props: {
    cruiseShip: {
      id: {
        type: Number,
        required: true,
      },
      routeFrom: {
        type: String,
        required: true,
      },
      routeTo: {
        type: String,
        required: true,
      },
      capacity: {
        type: Number,
        required: true,
      },
      numberOfVisitedPorts: {
        type: Number,
        required: true,
      },
      startDate: {
        type: Date,
        required: true,
      },
      endDate: {
        type: Date,
        required: true,
      },
      orderedSeats: {
        type: Number,
        required: true,
      },
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