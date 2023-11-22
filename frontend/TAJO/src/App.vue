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
import { useDistanceStore } from '@/stores/distance'
import { useTodayStore } from '@/stores/today'
import { useRecordStore } from '@/stores/record'
const router = useRouter();
const user = ref(null);
const store = useDistanceStore();
const todayStore = useTodayStore();
const recordStore = useRecordStore();
onMounted(() => {
  
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }
});

const logout = () => {
  user.value = null;
  localStorage.clear();
  alert("로그아웃 했습니다.");
  // store.setTotalDist(0);
  todayStore.setDefault();
  router.push({name : 'home'});
};
const loginUser = async (loginUser) => {
  // user 정보 요청 api 주소
  const API_URL = `http://localhost:8080/api-user`;

  try {
    // 첫 번째 axios 요청
    const res = await axios.get(API_URL);

    // 응답 처리
    let matchedUser = res.data.find(
      (u) => u.userid === loginUser.userid && u.password === loginUser.password
    );

    if (matchedUser) {
      // 두 번째 axios 요청
      const gradeResponse = await axios.get(`${API_URL}/grade`, {
        params: { userid: matchedUser.userid },
      });

      // 세 번째 axios 요청
      const recordResponse = await axios.get(`${API_URL}/record`, {
        params: { userid: matchedUser.userid },
      });
      let totalDistance = 0;
      for(const record of recordResponse.data) {
        totalDistance+=record.distance;
      }

      // store 업데이트
      store.getDistRank(matchedUser.userid);
      recordStore.getRecord(matchedUser.userid);

      // 응답 처리
      matchedUser.grade = gradeResponse.data;
      recordStore.setGrade(gradeResponse.data);
      store.setTotalDist(totalDistance);
      // 성공적으로 요청이 완료된 후에 user.value 설정
      user.value = matchedUser;
      // 로그인 성공 후 로직 수행
      localStorage.setItem("loginUser", JSON.stringify(user.value));
      alert("로그인 성공");
      router.push('/');
    } else {
      alert("로그인 실패");
    }
  } catch (err) {
    console.error(err);
    alert("로그인 실패: 서버 에러");
  }
};
/*const loginUser = async (loginUser) => {
  // user 정보 요청 api 주소
  const API_URL = `http://localhost:8080/api-user`;
  // axios 요청
  await axios
    .get(API_URL)
    .then((res) => {
      let matchedUser = res.data.find(
        (u) => u.userid === loginUser.userid && u.password === loginUser.password
      );

      if (matchedUser) {
        await axios.get(`${API_URL}/grade`, { userid : matchedUser.userid})
        .then((res) => {
          matchedUser.grade = res.data;
        });
        user.value = matchedUser;
        console.log(user.value.grade);
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
};*/


const createUser = (user) => {

  const API_URL = `http://localhost:8080/api-user/signup`;
  axios({
    url: API_URL,
    method: "POST",
    params: {
      userid: user.userid,
      password: user.password,
      nickname: user.nickname,
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
