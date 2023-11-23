<template>
    <div class="modal" v-if="isVisible">
      <div class="modal-content">
        <p>{{ message }}</p>
        <button @click="closeModal">확인</button>
      </div>
    </div>
  </template>
  
  <script setup>
  const props = defineProps(["isVisible", "message"]);
  const emits = defineEmits(["close"]);
  
  const isVisible = ref(false);
const message = ref('');

const showModal = (msg) => {
  isVisible.value = true;
  message.value = msg;
};

const hideModal = () => {
  isVisible.value = false;
  message.value = '';
};
  const closeModal = () => {
    emits("close");
  };
  </script>
  
  <style scoped>
  .modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background: white;
    padding: 20px;
    border-radius: 8px;
    text-align: center;
  }
  
  button {
    margin-top: 10px;
    padding: 5px 10px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  </style>