<template>
  <form @submit.prevent="rentCruiseShipById">
    <div class="form-group">
      <input type="url" v-model="frontPassportUrl">
      <input type="url" v-model="backPassportUrl">
      <button type="submit" class="btn btn-primary">Rent</button>
    </div>
  </form>
  <small id="apiErrors"
         class="error"
         v-if="errors.api.length>0">{{ errors.api.reduce((prev, curr) => prev + ", " + curr) }}</small>
</template>
<script>

import axios from "axios";

export default {
  name: "UserOrderRentCruiseShipByIdComponent",
  props: {
    cruiseShipId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      errors: {
        api: [],
      },
      frontPassportUrl: "",
      backPassportUrl: "",
    };
  },
  methods: {
    async rentCruiseShipById() {
      this.errors.api = [];
      const item = localStorage.getItem("token");
      const userOrder = {
        cruiseShip:{id: this.cruiseShipId},
        frontPassport: this.frontPassportUrl,
        backPassport: this.backPassportUrl,
      };
      let data = JSON.stringify(userOrder);
      try {
        const axiosResponse = await axios.post(`/api/v1/user-orders/`,data,{
              headers: {
                'Authorization': `Bearer ${item}`,
                'Content-Type': 'application/json'
              },
            },
        );
        if (axiosResponse.status === 200) {
          this.$router.push({name: 'user-order-list'});
        }
      } catch (e) {
        console.log(e);
        this.errors.api.push(e.response);
      }
    },
  },
}
</script>
<style scoped>

</style>