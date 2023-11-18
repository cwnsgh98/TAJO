<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Theheader from "./components/common/Theheader.vue"
import Footernav from "./components/common/Footernav.vue"
</script>

<template>
  <div class="body">
  <header>
    <Theheader/>
  </header>
  
  <main>
    <RouterView />
  </main>

  <footer>
   <Footernav/>
  </footer>
</div>
</template>

<style scoped>
@font-face {
  font-family: 'cookierun';
  src: url('@/font/CookieRunBold.otf') format('opentype');
  font-weight: normal;
  font-style: normal;
}

body, html {
  overflow: hidden; /* 전체 페이지 스크롤 막기 */
}
footer{
  z-index: 3;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'cookierun';
}

body, html {
  height: 100%;
}

.body {
  display: flex;
  flex-direction: column;
  min-height: 75vh;
}

main {
  flex: 1;
  width: 100%;
  margin-bottom: 120px;
}
footer{
  width: 100%;
  position: fixed;
  bottom: 0;

}


</style>
