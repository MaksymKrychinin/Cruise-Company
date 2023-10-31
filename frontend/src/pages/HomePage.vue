<template>

  <div class="filter-left">
  </div>
  <div class="cruise-ships">
    <CruiseShipList/>
  </div>
  <div class="pagination">1...3...5</div>
</template>

<script>
import CruiseShipList from "@/components/CruiseShipComponents/CruiseShipList";
import { useJwt } from '@vueuse/integrations/useJwt'
import {ref} from "vue";

export default {
  name: "HomePage",
  components: {CruiseShipList},
  beforeMount() {
    const item = localStorage.getItem('token');
    const encodedJwt = ref(item);
    const {header, payload} = useJwt(encodedJwt)
    console.log(payload)
    console.log(header)
    if (!item) {
      this.$router.push('/login');
    }
  },
  methods: {}
}
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