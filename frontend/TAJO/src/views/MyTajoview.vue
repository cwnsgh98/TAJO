<template>
  <div>
    <div class="MTmain" v-if="user">
      <div class="MT1">
        <MytajoProfile />
      </div>
      <div class="MT2">
        <MytajoMiddle />
      </div>
      <div class="MT3">
        <!-- MT3 영역의 컴포넌트 조건부 렌더링 -->
        <MytajoWriteform :showResult="showResult" @save-result="saveResult" />
        <MytajoResult v-if="showResult" @show-writeform="showWriteform" />
      </div>
    </div>
    <div class ="main" v-else>
      <MytajoGameVue/>
    </div>
  </div>
</template>

<script setup>
import MytajoProfile from '../components/마이타조/MytajoProfile.vue';
import MytajoMiddle from '../components/마이타조/MytajoMiddle.vue';
import MytajoWriteform from '../components/마이타조/MytajoWriteform.vue';
import MytajoResult from '../components/마이타조/MytajoResult.vue';
import MytajoGameVue from '../components/마이타조/MytajoGame.vue';
import { ref } from 'vue';

const showResult = ref(false);
const user = ref(localStorage.getItem("loginUser"))
const saveResult = () => {
  // 주행 저장 로직 등을 수행한 후
  showResult.value = true; // 결과 표시를 활성화
};

const showWriteform = () => {
  // MytajoResult에서 MytajoWriteform을 보여주기 위한 이벤트 핸들러
  showResult.value = false; // 결과를 감추고
};
</script>
  
<style scoped>
.MTmain {
  display: flex;
  overflow: hidden;
}

.MT1 {
  flex-grow: 0;
  border: solid 1px #a7a7a7;
  max-height: 755px;
  min-height: 755px;
  overflow: hidden;
  /* 스크롤 막기 */
}

.MT2 {
  flex-grow: 6;
  border: solid 1px #a7a7a7;
  max-height: 755px;
  min-height: 755px;
  overflow: hidden;
  /* 스크롤 막기 */
}

.MT3 {
  /* display: flex; */
  /* flex-direction: column; */
  /* justify-content: center; */
  /* align-items: center; */
  flex-grow: 1;
  border: solid 1px #a7a7a7;
  overflow-y: auto;
  /* 세로 스크롤 가능하도록 설정 */
  max-height: 755px;
  /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}
.main{
    width: 700px;
    height: 700px;
}
</style>
  