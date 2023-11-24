<template>
  <!--  <div class="cruise-ship">
      <img src="../../assets/images/logo.png" alt="Cruise image" class="logo">
      <p class="route">{{ cruiseShip.routeFrom }} - {{ cruiseShip.routeTo }}</p>
      <p><img class="icon"
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Person_icon_BLACK-01.svg/186px-Person_icon_BLACK-01.svg.png"
              alt="Capacity">
        {{ cruiseShip.capacity }}
        <img class="icon"
             src="https://cdn-icons-png.flaticon.com/512/32/32188.png"
             alt="NumberOfVisitedPorts">{{ cruiseShip.numberOfVisitedPorts }}
      </p>

      <p><img class="icon"
              src="https://static.vecteezy.com/system/resources/thumbnails/003/738/383/small/appointment-date-icon-free-vector.jpg"
              alt="Start date">{{ cruiseShip.startDate }}</p>

      <p><img src="https://cdn-icons-png.flaticon.com/512/4781/4781427.png"
              class="icon"
              alt="End date">{{ cruiseShip.endDate }}
        <img src="https://www.shutterstock.com/image-vector/chair-icon-illustration-isolated-vector-260nw-746198542.jpg"
             class="icon"
             alt="Ordered seats">{{ cruiseShip.orderedSeats }}</p>

    </div>-->
  <div class="cruise-ship">
    <h3>{{ cruiseShip.routeFrom }} → {{ cruiseShip.routeTo }}</h3>
    <p>Available seats: {{ availableSeats }}</p>
    <p>Start date: {{ formattedStartDate }}</p>
    <p>End date: {{ formattedEndDate }}</p>
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
.cruise-ship {
  width: 20%;
  height: 300px;
  background-color: #e0c280;
  float: left;
  margin: 10px;
  font-size: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}

.cruse-ship p {
  margin: 0;

}

.cruise-ship img.icon {
  width: 25px;
  height: 25px;
}

.cruise-ship img.logo {
  width: 230px;
  height: 230px;
  box-shadow: #080710 1px 1px 5px 5px;
}
</style>