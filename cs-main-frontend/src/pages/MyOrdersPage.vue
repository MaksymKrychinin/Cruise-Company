<template>
  <UserOrderFilter/>

  <div class="user-orders">
    <UserOrderList :cruise-ship-list="shipsList"/>
    <PaginationComponent
        @pagination_page_changed="(page, resultPerPage)=>getAllUserOrders(page, resultPerPage)"
        :current-page="currentPage"
        :total-pages="totalPages"
        :max-visible-buttons="3"/>
  </div>
</template>

<script>
import {jwtDecode} from "jwt-decode";
import PaginationComponent from "@/components/layouts/Pagination";
import axios from "axios";
import UserOrderList from "@/components/UserOrderComponents/UserOrderList.vue";
import UserOrderFilter from "@/components/UserOrderComponents/UserOrderFilter.vue";

export default {
  name: "MyOrdersPage",
  components: {UserOrderFilter, UserOrderList, PaginationComponent},
  data() {
    return {
      shipsList: [],
      currentPage: 1,
      totalPages: 1,
      resultsPerPage: 8,
      token: ''
    };
  },
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
  },
  beforeMount() {
    this.getAllUserOrders();
  },
  methods: {
    async getAllUserOrders(pageNumber, resultPerPage) {
      const item = localStorage.getItem("token");
      if (pageNumber) {
        this.currentPage = pageNumber;
      }
      if (resultPerPage) {
        this.resultsPerPage = resultPerPage;
      }
      const axiosResponse = await axios.get("api/v1/user-orders", {
            headers: {
              'Authorization': `Bearer ${item}`
            },
            params: {
              page: this.currentPage - 1,
              size: this.resultsPerPage
            }
          }
      );
      this.currentPage = axiosResponse.data.pageable.pageNumber + 1;
      this.totalPages = axiosResponse.data.totalPages;
      this.shipsList = axiosResponse.data.content;
    }
  }
};
</script>

<style scoped>
div.user-orders {
  display: flex;
  flex-direction: column;
}
</style>