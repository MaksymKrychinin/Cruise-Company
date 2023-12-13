<template>
  <div class="user-order"
       v-bind:class="statusColor()">
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
      user: {
        type: Object,
        required: false,
      },
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
      id: {
        type: Number,
        required: true,
      },
    },
  },
  methods: {
    statusColor() {
      if (this.userOrder.status.toUpperCase() === "APPROVED") {
        return "approved-border";
      } else if (this.userOrder.status.toUpperCase() === "CANCELED") {
        return "canceled-border";
      } else if (this.userOrder.status.toUpperCase() === "NOT APPROVED") {
        return "not-approved-border";
      } else if (this.userOrder.status.toUpperCase() === "RAINBOW") {
        return "rainbow";
      }
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
.user-order {
  position: relative;
  border-radius: 25px;
  padding: 20px;
  width: 200px;
  height: 150px;
  margin: 15px;
}

.cruise-ship > h3 {
  margin: 0;
}

.rainbow::before {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: linear-gradient(to bottom right, #b827fc 0%, #2c90fc 25%, #b8fd33 50%, #fec837 75%, #fd1892 100%);
  border-radius: 23px;
  z-index: -1;
}
.not-approved-border {
  border: 2px solid #db8645;
}
.canceled-border {
  border: 2px solid #db5145;
}
.approved-border {
  border: 2px solid #659B5E;
}

</style>