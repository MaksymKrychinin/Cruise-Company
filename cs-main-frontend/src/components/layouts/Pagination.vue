<template>
  <ul class="pagination">
    <li class="pagination-item">
      <button class="pagination-btn" type="button" @click="onClickPage(1)" :disabled="isInFirstPage"
              :class="{ active: isPageActive(1) }">
        {{ "<-" }}
      </button>
    </li>
    <li>
      ...
    </li>
    <li v-for="page in pages" class="pagination-item" :key="page.name">
      <button class="pagination-btn" type="button" @click="onClickPage(page.name)"
              :class="{ active: isPageActive(page.name) }">
        {{ page.name }}
      </button>
    </li>
    <li>
      ...
    </li>
    <li class="pagination-item">
      <button class="pagination-btn" type="button" @click="onClickPage(totalPages)" :disabled="isInLastPage"
              :class="{ active: isPageActive(totalPages) }">
        {{ "->" }}
      </button>
    </li>
    <li class="pagination-item">
      <select v-model="resultsPerPage" @change="changeResultsPerPage()">
        <option>6</option>
        <option>9</option>
        <option>12</option>
      </select>
    </li>
  </ul>
</template>

<script>
export default {
  name: "PaginationComponent",
  data() {
    return {
      resultsPerPage: 9
    };
  },
  props: {
    maxVisibleButtons: {
      type: Number,
      required: false,
      default: 3
    },
    totalPages: {
      type: Number,
      required: true,
      default: 1
    },
    currentPage: {
      type: Number,
      required: true,
      default: 1
    }
  },
  computed: {
    isInFirstPage() {
      return this.currentPage === 1;
    },
    isInLastPage() {
      return this.currentPage === this.totalPages;
    },
    startPage() {
      if (this.currentPage === 1) {
        return 1;
      }
      if (this.currentPage === this.totalPages) {
        return this.totalPages - this.maxVisibleButtons;
      }
      return this.currentPage - 1;
    },

    pages() {
      const range = [];
      for (
          let i = this.startPage;
          i <= Math.min(this.startPage + this.maxVisibleButtons - 1, this.totalPages);
          i++
      ) {
        range.push({
          name: i,
          isDisabled: i === this.currentPage
        });
      }
      return range;
    },
  },

  methods: {
    onClickPage(page) {
      this.$emit('pagination_page_changed', page);
    },
    changeResultsPerPage() {
      this.$emit('pagination_page_changed', this.currentPage, this.resultsPerPage);
    },
    isPageActive(page) {
      return this.currentPage === page;
    }
  }
}
</script>

<style scoped>
.pagination {
  height: 60px;
  position: sticky;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  list-style-type: none;
  color: #ffffff;
  background-color: rgba(136, 77, 77, 0.9);
}

.pagination-item {
  display: inline-block;
}

.pagination-item .pagination-btn {
  color: #ffffff;
  font-size: 14px;
  font-weight: 400;
  background-color: transparent;
}

.pagination-item .pagination-btn:hover {
  color: #ffffff;
  font-size: 14px;
  font-weight: 400;
  background-color: rgba(197, 27, 62, 0.9);
}

.active.pagination-btn {
  font-size: 18px;
  font-weight: 700;
  color: #ffffff;
  background-color: rgba(197, 63, 91, 0.9);
  pointer-events: none;
}
</style>