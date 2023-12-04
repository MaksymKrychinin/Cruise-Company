<template>
  <div class="user-order">
    <div class="cruise-ship">
      <h3>{{ userOrder.cruiseShip.routeFrom }} → {{ userOrder.cruiseShip.routeTo }}</h3>
      <p>Start date: {{ formattedStartDate }}</p>
      <p>End date: {{ formattedEndDate }}</p>
    </div>
    <div class="status">
      <p>Status: {{ userOrder.status }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserOrder",
  props: {
    userOrder: {
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
      status: {
        type: String,
        required: true,
      },
    },
  },
  computed: {
    formattedStartDate() {
      return new Date(this.userOrder.cruiseShip.startDate).toLocaleDateString();
    },
    formattedEndDate() {
      return new Date(this.userOrder.cruiseShip.endDate).toLocaleDateString();
    },
  },
};
</script>

<style scoped>

</style>