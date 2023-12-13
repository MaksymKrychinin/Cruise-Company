<template>
  <div class="filter-left">
    <div class="filter">
      <h3>Filter</h3>
      <div class="filter-item">
        <label for="name">Status</label>
        <input type="text" id="orderStatus" name="orderStatus" placeholder="Order Status">
      </div>
      <button>Filter</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserOrderFilter"
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