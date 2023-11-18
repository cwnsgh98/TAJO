<template>
    <div>
      <!-- 토글 버튼 -->
      <button @click="togglePanel">토글</button>
  
      <!-- 나타날/사라질 패널 -->
      <transition name="panel-fade">
        <div v-if="isPanelVisible" class="panel">
          <h2>반갑소</h2>
          <!-- 패널 내용 -->
          <div class="panel-content">
            <!-- 패널 닫기 버튼 -->
            <button @click="closePanel">닫기</button>
            <!-- 패널 내용 추가 -->
            <h2>이건모요</h2>
            <!-- ... -->
          </div>
        </div>
      </transition>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isPanelVisible: false,
      };
    },
    methods: {
      togglePanel() {
        this.isPanelVisible = !this.isPanelVisible;
      },
      closePanel() {
        this.isPanelVisible = false;
      },
    },
  };
  </script>
  
  <style scoped>
  .panel {
    position: fixed;
    top: 0;
    right: 0px;
    width: 300px;
    height: 100vh;
    background-color: #f0f0f0;
    overflow: hidden;
  }
  
  .panel-content {
    padding: 20px;
  }
  
  .panel-fade-enter-active, .panel-fade-leave-active {
    transition: opacity 3s;
  }
  .panel-fade-enter, .panel-fade-leave-to {
    opacity: 0;
  }
  </style>
  