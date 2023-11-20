<template>
  <div class="body">
    <header>
      <Theheader :user="user" @logout="logout" />
    </header>

    <main>
      <RouterView @login-user="loginUser" @create-user="createUser" />
    </main>

    <footer>
      <Footernav />
    </footer>
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Theheader from "./components/common/Theheader.vue"
import Footernav from "./components/common/Footernav.vue"
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
const user = ref(null);

onMounted(() => {
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }
});

const logout = () => {
  user.value = null;
  localStorage.removeItem("loginUser");
  alert("로그아웃 했습니다.");
};

const loginUser = (loginUser) => {
  // user 정보 요청 api 주소
  const API_URL = `http://localhost:8080/api-user`;
  const grade = ref(null);
  // axios 요청
  axios
    .get(API_URL)
    .then((res) => {
      let matchedUser = res.data.find(
        (u) => u.userid === loginUser.userid && u.password === loginUser.password
      );

      if (matchedUser) {
        user.value = matchedUser;
        localStorage.setItem("loginUser", JSON.stringify(user.value));
        alert("로그인 성공");
        router.push('/');
      } else {
        alert("로그인 실패");
      }
    })
    .catch((err) => {
      console.log(err);
      alert("로그인 실패: 서버 에러");
    });
};

const createUser = (user) => {

  const API_URL = `http://localhost:8080/api-user/signup`;
  axios({
    url: API_URL,
    method: "POST",
    params: {
      userid: user.userid,
      password: user.password,
      nickname: user.nickname,
      level: user.level,
    },
  })
    .then(() => {
      alert("회원가입 완료");
      router.push('/');
    })
    .catch((err) => {
      console.log(err);
    });
};

</script>
<style scoped>
@font-face {
  font-family: 'cookierun';
  src: url('@/font/CookieRunBold.otf') format('opentype');
  font-weight: normal;
  font-style: normal;
}

body,
html {
  overflow: hidden;
  /* 전체 페이지 스크롤 막기 */
}

footer {
  z-index: 3;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'cookierun';
}

body,
html {
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

footer {
  width: 100%;
  position: fixed;
  bottom: 0;

}</style>
