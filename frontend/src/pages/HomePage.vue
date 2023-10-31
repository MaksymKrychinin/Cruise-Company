<template>

  <div class="filter-left">
  </div>
  <div class="cruise-ships">
    <CruiseShipList v-if="token"/>
  </div>
  <div class="pagination">1...3...5</div>
</template>

<script>
import CruiseShipList from "@/components/CruiseShipComponents/CruiseShipList";
import {jwtDecode} from "jwt-decode";

export default {
  name: "HomePage",
  components: {CruiseShipList},
  token: String,
  beforeCreate() {
    this.token = localStorage.getItem('token');
    if (!this.token) {
      this.$router.push('/login');
      localStorage.setItem('error', 'You need to log in first');
    } else {
      const jwtPayload = jwtDecode(this.token);
      new Date().getTime() / 1000 < jwtPayload.exp
          ? console.log("Token is valid")
          : this.$router.push('/login') && localStorage.setItem('error', 'You need to log in first');
    }
  }
};
</script>

<style scoped>
.filter-left {
  width: 20%;
  height: calc(100vh - 50px);
  background-color: #e0c280;
  float: left;
}

.pagination {
  position: absolute;
  bottom: 30px;
  left: 50%;
  font-size: 20px;
}

</style>