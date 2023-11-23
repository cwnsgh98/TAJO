<template>
    <div>
      <!-- Header -->
      <div class="header">
        <div class="header-fifty">
          <div class="fifty-first">
            <RouterLink class="router-link" :to="{ name: 'home' }">
              <img class="tajo" :src="getLogoPath('누끼딴로고.png')" />
            </RouterLink>
          </div>
          <div class="fifty-second">
            <div class="header-fifty2 " v-if="getUser"> 
            <span class="이름 stroked-text">{{ nickname }} </span>
            <span class="반갑소"> 님 반갑습니다!</span>
          </div>
            <a href="#" class="router-link hi" v-if="getUser" @click="logout">
              <!-- <img class="logout" :src="getLogoPath('logout.png')" /> -->
              <span class="로그아웃 stroked-text">로그아웃</span>
            </a>

            <RouterLink class="router-link" v-else :to="{ name: 'login' }">
              <img class="login" :src="getLogoPath('login.png')" />
            </RouterLink>
            <RouterLink class="router-link" v-if="!getUser" :to="{ name: 'regist' }">
              <img class="regist" :src="getLogoPath('regist.png')" />
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
  </template>
  
<script setup>
import { useRouter, useRoute } from 'vue-router';
import {ref,computed, onMounted,onUpdated, watch} from 'vue'
const user = ref(null);
const nickname = ref("");
onMounted(() => {
    
    const savedUser = localStorage.getItem("loginUser");
    
    if (savedUser) {
        user.value = JSON.parse(savedUser);
        nickname.value = user.value.nickname;
    }
});

onUpdated(() => {
    
    const savedUser = localStorage.getItem("loginUser");
    
    if (savedUser) {
        user.value = JSON.parse(savedUser);
        nickname.value = user.value.nickname;
    }
});

const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const getUser = computed(() => !!props.user);

const logout = () => {
    emits("logout");
};
const router = useRouter();
const route = useRoute();

const getLogoPath = (imageName) => {
    const currentPath = route.name;
    const isCurrentPath = (path) => currentPath === path;

    return isCurrentPath('home') && imageName.includes('누끼딴로고')
        ? `/src/assets/${imageName}`
        : isCurrentPath('login') && imageName.includes('login')
            ? `/src/assets/${imageName.replace('.png', 'change.png')}`
            : isCurrentPath('regist') && imageName.includes('regist')
                ? `/src/assets/${imageName.replace('.png', 'change.png')}`
                : `/src/assets/${imageName}`;
};
</script>
 
<style scoped>
.router-link {
  margin-top: 3px;
  transition: transform 0.3s ease-in-out;
}
.이름{
  margin-right: 10px;
  font-size: 18px;
  color: #FC9797;
}
.반갑소{
  color: black;
}
a{
  text-decoration: none;
}
.hi{
  text-align: center;
}
.stroked-text {
    -webkit-text-stroke: 0.5px #ffa8a8;
    /* Safari 및 Chrome 브라우저에서 사용 가능 */
    color: #F96C85;
    /* 텍스트 색상 지정 */
    font-size: 24px;
    font-family: 'cookierun';
}
.router-link:hover img {
  transform: scale(1.1);
}
.router-link:hover {
  transform: scale(1.1);
}

.header-fifty2{
  display: flex;
  align-items: center;
  margin-right: 50px;
}

.header,
.footernav {
  display: flex;
  justify-content: space-around;
}

.header-fifty {
  display: flex;
  width: 53%;
  justify-content: space-between;
  align-items: center;
}

.fifty-second {
  display: flex;
  justify-content: space-around;
  margin-right: 50px;
}

.fifty-first {
  margin-right: 20px;
}

.logout,
.logout:hover {
  width: 63px;
  height: 26px;
}

.logo,
.tajo,
.login,
.regist {
  padding: 9px;
  width: 140px;
  height: 49px;
  flex-shrink: 0;
}

.login {
  margin-right: 40px;
  width: 50px;
  height: 25px;
}

.regist {
  width: 66.6px;
  height: 25px;
}

.header {
  width: 100%;
  height: 82px;
  flex-shrink: 0;
  border-bottom: 2px solid #A4A4A4;
  background: #FFFFF3;
  display: flex;
  justify-content: flex-end;
}

.logout:hover {
  content: url('@/assets/logouthover.png');
}
</style>