<template>
    <div>
      <button class="고치기버튼" @click="search('안장')">안장</button>
      <button class="고치기버튼" @click="search('핸들')">핸들</button>
      <button class="고치기버튼" @click="search('체인')">체인</button>
      <button class="고치기버튼" @click="search('브레이크')">브레이크</button>
      <button class="고치기버튼" @click="search('바퀴')">바퀴</button>
      <button class="고치기버튼" @click="search('타조')">타조</button>
      <div>
        <input type="text" v-model="keyword">
        <button @click="search">검색</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useYoutubeStore } from '@/stores/youtube';
  
  const keyword = ref('');
  const store = useYoutubeStore();
  
  const search = (additionalKeyword) => {
    // "타조"를 눌렀을 때는 "자전거"와 "수리" 추가하지 않음
    const combinedKeyword = additionalKeyword === '타조' ? '귀여운 타조' : '자전거' + additionalKeyword + ' 수리';
    keyword.value = combinedKeyword;
  
    // 검색 수행
    store.youtubeSearch(keyword.value);
  };
  </script>
  
  <style scoped>
  .고치기버튼{
    border: solid 1px #000000;
    border-radius: 50px;
    padding: 5px 12px;
    margin-right: 6px;
    cursor: pointer;
    background-color: #f9d6d6;
    color: #000000;
    transition: background-color 0.3s;
    margin-top: 12px;
    margin-bottom: 20px;
    font-family: 'cookierun';
  }
  </style>
  