<template>

  <CruiseShipFilter @filterCruiseShips="(filterArray)=> this.filters = filterArray"
                    @clearCruiseShipFilters="this.filters=[]"/>

  <div class="cruise-ships">
    <CruiseShipList :cruise-ship-list="shipsList"/>
    <PaginationComponent
        @pagination_page_changed="(page, resultPerPage)=>getAllCruiseShips(page, resultPerPage)"
        :current-page="currentPage"
        :total-pages="totalPages"
        :max-visible-buttons="3"/>
  </div>
</template>

<script>
import CruiseShipList from "@/components/CruiseShipComponents/CruiseShipList";
import PaginationComponent from "@/components/layouts/Pagination";
import axios from "axios";
import CruiseShipFilter from "@/components/CruiseShipComponents/CruiseShipFilter";

export default {
  name: "CruiseShipsPage",
  components: {CruiseShipFilter, PaginationComponent, CruiseShipList},
  data() {
    return {
      filters: [],
      shipsList: [],
      currentPage: 1,
      totalPages: 1,
      resultsPerPage: 9,
      token: ''
    };
  },
  beforeCreate() {
    this.token = localStorage.getItem('token');
    if (!this.token) {
      this.$router.push('/login');
      localStorage.setItem('error', 'You need to log in first');
    }
  },
  beforeMount() {
    this.getAllCruiseShips();
  },
  watch: {
    filters: {
      handler: function () {
        this.getAllCruiseShips(1, this.resultsPerPage);
      },
      deep: true
    }
  },
  methods: {
    async getAllCruiseShips(pageNumber, resultPerPage) {
      let axiosResponse = null;
      const item = localStorage.getItem("token");
      if (pageNumber) {
        this.currentPage = pageNumber;
      }
      if (resultPerPage) {
        this.resultsPerPage = resultPerPage;
      }
      const params = {
        page: this.currentPage - 1,
        size: this.resultsPerPage
      }
      if (this.filters.length > 0) {
        const data = {...params, ...this.filters};
        axiosResponse = await axios.post("api/v1/cruise-ships/filter/", {data}, {
              headers: {
                'Authorization': `Bearer ${item}`,
                'Content-Type': 'application/json'
              },
            }
        );
      } else {
        axiosResponse = await axios.get("api/v1/cruise-ships", {
              headers: {
                'Authorization': `Bearer ${item}`
              },
              params: params
            }
        );
      }
      console.log(axiosResponse)
      this.currentPage = axiosResponse.data.pageable.pageNumber + 1;
      this.totalPages = axiosResponse.data.totalPages;
      this.shipsList = axiosResponse.data.content;
    },
  },
};
</script>

<style scoped>
div.cruise-ships {
  display: flex;
  flex-direction: column;
}
</style>