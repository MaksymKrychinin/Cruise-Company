<template>
  <div class="filter-left">
    <div class="filter">
      <h3>Filter</h3>
      <div class="filter-item">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Name">
      </div>
      <div class="filter-item">
        <label for="name">Destination</label>
        <input type="text" id="destination" name="destination" placeholder="Destination">
      </div>
      <div class="filter-item">
        <label for="name">Departure</label>
        <input type="text" id="departure" name="departure" placeholder="Departure">
      </div>
      <div class="filter-item">
        <label for="name">Arrival</label>
        <input type="text" id="arrival" name="arrival" placeholder="Arrival">
      </div>
      <div class="filter-item">
        <label for="name">Price</label>
        <input type="text" id="price" name="price" placeholder="Price">
      </div>
      <div class="filter-item">
        <label for="name">Duration</label>
        <input type="text" id="duration" name="duration" placeholder="Duration">
      </div>
      <div class="filter-item">
        <label for="name">Capacity</label>
        <input type="text" id="capacity" name="capacity" placeholder="Capacity">
      </div>
      <div class="filter-item">
        <label for="name">Rating</label>
        <input type="text" id="rating" name="rating" placeholder="Rating">
      </div>
      <div class="filter-item">
        <label for="name">Cruise Line</label>
        <input type="text" id="cruiseLine" name="cruiseLine" placeholder="Cruise Line">
      </div>
      <div class="filter-item">
        <label for="name">Ship Type</label>
        <input type="text" id="shipType"
               name="shipType" placeholder="Ship Type">
      </div>
      <div class="filter-item">
        <label for="name">Ship Size</label>
        <input type="text" id="shipSize" name="shipSize" placeholder="Ship Size">
      </div>
      <div class="filter-item">
        <label for="name">Ship Age</label>
        <input type="text" id="shipAge" name="shipAge" placeholder="Ship Age">
      </div>
      <button>Filter</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CruiseShipFilter"
}
</script>

<style scoped>
.filter-left {
  position: sticky;
  left: 0;
  top: 0;
  width: 20%;
  height: 100vh;
  background-color: #e0c280;
  float: left;
}

.filter>div{
  display: flex;
  flex-direction: column;
}

.filter-item{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 10px;
}

.filter-item>label{
  margin-right: 10px;
}

.filter-item>input{
  width: 100%;
}

.filter>button{
  margin: 10px;
  width: 90%;
  height: 40px;
  background-color: #9b8751;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter>button:hover{
  background-color: #e7ab26;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter>button:active{
  background-color: #ffa900;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter>h3{
  margin: 10px;
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}
</style>