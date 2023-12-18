<template>
  <div class="filter-left">
    <div class="filter">
      <h3>Filter</h3>

      <div v-for="filter in filters" :key="filter.fieldName">
        <div class="filter-item">
          <label>{{ translate(filter.fieldName) || filter.fieldName }}</label>
          <input v-if="filter.fieldType === 'Integer' || filter.fieldType ==='int'" type="number"
                 v-model="filter.fieldData"/>
          <input v-if="filter.fieldType === 'String'" type="text" v-model="filter.fieldData"/>
          <input v-if="filter.fieldType === 'LocalDate'" type="date" v-model="filter.fieldData"/>
        </div>
      </div>

      <button @click="filterCruiseShips(this.filters)">Filter</button>
      <button @click="$emit('clearCruiseShipFilters')">Clear</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {translate} from "@/TranslationPipe";

export default {
  name: "CruiseShipFilter",
  methods: {
    translate,
    filterCruiseShips(filters) {
      this.$emit('filterCruiseShips',
          filters.filter(filter => filter.fieldData?.trim().length > 0 && filter.fieldData !== undefined)
              .map(filter => {
                return {
                  fieldName: filter.fieldName,
                  fieldData: filter.fieldData,
                }
              }));
    },
  },
  data() {
    return {
      filters: [{
        fieldName: '',
        fieldType: '',
        fieldData: '',
      }],
    }
  },
  async beforeCreate() {
    const axiosResponse = await axios.get("api/v1/cruise-ships/filters", {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      },
    });
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
  width: 45%;
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