<template>
    <div class="day-info" v-if="showInfo">
      <!-- 각 날짜에 따른 정보를 여기에 추가하세요 -->
      <span>{{ day }}일</span>
      <span>추가 정보...</span>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      day: Number,
    },
    data() {
      return {
        showInfo: false,
      };
    },
    methods: {
      showInfoWindow() {
        this.showInfo = true;
      },
      hideInfoWindow() {
        this.showInfo = false;
      },
    },
    watch: {
      showInfo(newValue) {
        if (newValue) {
          console.log('Info window is shown.');
          // 정보 창이 표시될 때 수행할 작업을 여기에 추가하세요.
        } else {
          console.log('Info window is hidden.');
          // 정보 창이 숨겨질 때 수행할 작업을 여기에 추가하세요.
        }
      },
    },
  };
  </script>
  
  <style scoped>
    .day-info {
      position: absolute;
      background-color: #fff;
      border: 1px solid #ccc;
      padding: 10px;
      z-index: 999; /* 다른 요소 위에 표시되도록 설정합니다. */
    }
  </style>
  