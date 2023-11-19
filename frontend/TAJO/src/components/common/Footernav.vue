<template>
    <div>
      <div class="footernav">
        <RouterLink to="/" class="router-link">
          <img class="홈로고" :src="getLogoPath('logohome.png')" />
        </RouterLink>
        <RouterLink to="/Together" class="router-link">
          <img class="같이타조로고" :src="getLogoPath('logotogether.png')" />
        </RouterLink>
        <RouterLink to="/Wherego" class="router-link">
          <img class="어디가조로고" :src="getLogoPath('logowhere.png')" />
        </RouterLink>
        <RouterLink to="/Mytajo" class="router-link">
          <img class="마이타조로고" :src="getLogoPath('logomytajo.png')" />
        </RouterLink>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onBeforeMount } from 'vue';
  import { useRouter, useRoute } from 'vue-router';
  
  const router = useRouter();
  const route = useRoute();
  
  const getLogoPath = (imageName) => {
    const currentPath = route.path;
    const isCurrentPath = (path) => currentPath === path;
  
    return isCurrentPath('/') && imageName.includes('logohome')
      ? `src/assets/${imageName.replace('.png', 'change.png')}`
      : isCurrentPath('/Together') && imageName.includes('logotogether')
      ? `src/assets/${imageName.replace('.png', 'change.png')}`
      : isCurrentPath('/Wherego') && imageName.includes('logowhere')
      ? `src/assets/${imageName.replace('.png', 'change.png')}`
      : isCurrentPath('/Mytajo') && imageName.includes('logomytajo')
      ? `src/assets/${imageName.replace('.png', 'change.png')}`
      : `src/assets/${imageName}`;
  };
  </script>

<style scoped>
.router-link {
  transition: transform 0.3s ease-in-out;
}
.같이타조로고:hover {
  transform: scale(1.1);
}
.어디가조로고:hover {
  transform: scale(1.1);
}
.마이타조로고:hover {
  transform: scale(1.1);
}
.홈로고:hover {
  transform: scale(1.1);
}
.footernav {
    background-color:#FFFFF3;
    display: flex;
    justify-content: space-around;
    box-shadow: 0px 2px 4px 5px rgba(0, 0, 0, 0.25);
}

img {
    padding: 9px;
}

.홈로고 {
    width: 44px;
    height: 83px;
}

.같이타조로고,
.마이타조로고 {
    width: 73px;
    height: 83px;
}

.어디가조로고 {
    width: 67px;
    height: 83px;
}
</style>




