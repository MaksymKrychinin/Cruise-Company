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
        api: null,
      },
      frontPassportUrl: "",
      backPassportUrl: "",
    };
  },
  beforeMount() {
    this.errors.api = [];
  },
  methods: {
    async rentCruiseShipById() {
      const item = localStorage.getItem("token");
      try {
        const data = JSON.stringify({
          cruiseShip: {id: this.cruiseShipId},
          frontPassport: this.frontPassportUrl,
          backPassport: this.backPassportUrl,
        });
        const axiosResponse = await axios.post(`api/v1/user-orders/`, data, {
              headers: {
                'Authorization': `Bearer ${item}`,
              },
            },
        );
        if (axiosResponse.status === 200) {
          this.$router.push({name: 'user-order-list'});
        }
      } catch (e) {
        this.errors.api = e.response.data;
      }
    },
  },
}
</script>
<style scoped>

</style>