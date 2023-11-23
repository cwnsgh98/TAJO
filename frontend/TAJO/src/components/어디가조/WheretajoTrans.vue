<template>
    <div>
      <div class="화면바꾸기">
        <RouterLink class="router-link" to="/Wherego/info">
          <button>지도보기</button>
        </RouterLink>
        <button @click="togglezzim">찜한코스</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, inject } from 'vue';
  import { RouterLink } from 'vue-router';
  
  const zzimgo = inject('zzimgo');
  const togglezzim = () => {
    zzimgo.value = !zzimgo.value;
  };



</script>

<style scoped>
.화면바꾸기{
    display: flex;
    background-color: beige;
    border: solid 1px #d2d2d2;
    height: 40px;
    align-items: center;
}
button{
    margin-left: 15px;
    background-color: aliceblue;
    border: 2px solid rgb(93, 93, 93);
    font-family: 'cookierun';
    padding: 2px;
    padding-left: 3px;
    padding-right: 3px;
    border-radius: 20px;
}
button:hover{
    background-color: rgb(255, 187, 179);
}
</style>