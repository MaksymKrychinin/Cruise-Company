<template>
  <div class="filter-left">
    <div class="filter">
      <h3>Filter</h3>

      <div v-for="filter in filters" :key="filter.fieldName">
        <div class="filter-item">
          <label>{{ TranslationPipe(filter.fieldName) || filter.fieldName }}</label>
          <input v-if="filter.fieldType === 'Integer' || filter.fieldType ==='int'"
                 type="number"
                 v-model="filter.value"/>
          <input v-if="filter.fieldType === 'String'"
                 type="text"
                 v-model="filter.value"/>
          <input v-if="filter.fieldType === 'LocalDate'"
                 type="date"
                 v-model="filter.value"/>
        </div>
      </div>

      <button @click="$emit('filterUserOrders', filters)">Filter</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {TranslationPipe} from "../../TranslationPipe";

export default {
  name: "UserOrdersFilter",
  methods: {TranslationPipe},
  props: {
    userOrderList: Array
  },
  data() {
    return {
      filters: [{
        fieldName: '',
        fieldType: '',
        value: '',
      }],
    }
  },
  async beforeCreate() {
    const axiosResponse = await axios.get("api/v1/user-orders/filters", {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
    });
    console.log(axiosResponse.data)
    this.filters = axiosResponse.data;
  },
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

.filter > div {
  display: flex;
  flex-direction: column;
}

.filter-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin: 10px;
}

.filter-item > label {
  margin-left: 10px;
  font-size: 16px;
  font-weight: bold;
}

.filter-item > input {
  width: 90%;
  margin-left: 2.5%;
}

.filter > button {
  margin: 10px;
  width: 90%;
  height: 40px;
  background-color: #ab965b;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter > button:hover {
  background-color: #e7ab26;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter > button:active {
  background-color: #ffa900;
  border: 1px solid #e0c280;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.filter > h3 {
  margin: 10px;
  font-size: 30px;
  font-weight: bold;
  text-align: center;
}
</style>