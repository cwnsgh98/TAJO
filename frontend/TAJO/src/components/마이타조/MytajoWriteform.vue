<template>
    <div class="big">
      <div v-if="!showResult" class="writeform">
        <!-- 주행 시작 전에 속도 선택하는 부분 -->
        <div v-if="!isDriving && timer === 0">
          <label for="speed">속도 선택:</label>
          <select v-model="selectedSpeed">
            <option value="slow">천천히</option>
            <option value="normal">보통</option>
            <option value="fast">빠르게</option>
          </select>
        </div>
  
        <!-- 주행 시작 버튼이 보일 때 -->
        <button v-if="!isDriving && timer === 0" @click="startDriving">주행 시작</button>
  
        <!-- 주행 중일 때 -->
        <div v-if="isDriving">
          <p class="주행중">{{ animatedText }}</p>
          <p>주행 시간: {{ timer }}</p>
          <p>선택한 속도: {{ selectedSpeed }}</p>
          <div class="양옆">
            <button @click="pauseResumeTimer">
              {{ isPaused ? '이어서 주행하기' : '일시정지' }}
            </button>
            <button @click="endDriving">{{ isPaused ? '주행 종료' : '주행 종료' }}</button>
          </div>
        </div>
  
        <!-- 주행이 종료된 후에만 타이머와 초기화 버튼이 보이도록 설정 -->
        <div v-if="!isDriving && timer >= 1">
          <p>{{ isPaused ? '주행 일시정지' : '주행 종료' }}</p>
          <p>주행 시간: {{ timer }}</p>
          <div class="양옆">
            <button @click="resetTimer">타이머 초기화</button>
            <button @click="saveAndShowResult">
              {{ isPaused ? '이어서 주행하기' : '주행 저장하기' }}
            </button>
          </div>
        </div>
        <div class="조타">
          <div class="말풍선디브">
            <span class="말풍선">타조하니깐 기분이 조타</span>
          </div>
          <img src="@/assets/말풍선타조.png">
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useTodayStore } from '@/stores/today';
  import { useDistanceStore } from '@/stores/distance';
  import { useRecordStore } from '@/stores/record';
  import axios from 'axios';
  const store = useTodayStore();
  const distStore = useDistanceStore();
  const recordStore = useRecordStore();
  const props = defineProps(['showResult']);
  const isDriving = ref(false);
  const isPaused = ref(false);
  const timer = ref(0);
  const selectedSpeed = ref('');
  let intervalId;
  const animatedText = ref("주행 시작 !");
  const savedUser = localStorage.getItem("loginUser");
    
  const user = ref(null);
  user.value = JSON.parse(savedUser);
  const emit = defineEmits();
  
  const startDriving = () => {
    isDriving.value = true;
    console.log("Selected Speed:", selectedSpeed.value);
  
    intervalId = setInterval(() => {
      timer.value += 1;
  
      if (timer.value % 4 === 1) {
        animatedText.value = "즐거운 주행중 . ";
      } else if (timer.value % 4 === 2) {
        animatedText.value = "즐거운 주행중 . . ";
      } else if (timer.value % 4 === 3) {
        animatedText.value = "즐거운 주행중 . . . ";
      } else {
        animatedText.value = "즐거운 주행중 . . . . "
      }
    }, 1000);
  };
  
  const endDriving = () => {
    isDriving.value = false;
    isPaused.value = false;
    clearInterval(intervalId);
    animatedText.value = "주행시작 ! ";
  };
  
  const resetTimer = () => {
    timer.value = 0;
    animatedText.value = "주행시작 ! ";
  };
  
  const pauseResumeTimer = () => {
    if (isDriving.value) {
      if (isPaused.value) {
        intervalId = setInterval(() => {
          timer.value += 1;
        }, 1000);
      } else {
        clearInterval(intervalId);
      }
  
      isPaused.value = !isPaused.value;
    }
  };
  
  const saveAndShowResult = () => {
    let speed = 0;
    let calorie = 0;
    if(selectedSpeed.value == "slow") {
      speed = 15;
      calorie = 6;
    } else if (selectedSpeed.value == "normal") {
      speed = 20;
      calorie = 7;
    } else if (selectedSpeed.value == "fast") {
      speed = 30;
      calorie = 10;
    }
    let distance = timer.value * speed;
    calorie = calorie * timer.value;

    store.plusTodayDist(distance);
    store.plusTodayCal(calorie);
    store.plusTodayTime(timer.value);
    distStore.getDistRank(user.value.userid);
    distStore.setTotalDist(distStore.totalDist+distance);
    recordStore.getRecord(user.value.userid);
    const record = {
      userid : user.value.userid,
      distance : distance,
    }
    //입력한 데이터 저장
    const res = axios.post(`http://localhost:8080/api-user/record`,
      record);
    
    const gradeResponse = axios.get(`http://localhost:8080/api-user/grade`, {
        params: { userid: user.value.userid },
      });

  
      // 응답 처리
      user.value.grade = gradeResponse.data;
      // 로그인 성공 후 등급 업데이트
      localStorage.setItem("loginUser", JSON.stringify(user.value));
      
    resetTimer(); // 타이머 초기화
    emit('save-result');
  };
  
  </script>
  
  
  <style scoped>
  .주행중 {
    font-size: 25px;
    padding-bottom: 20px;
  }
  
  button {
    border: solid 1px #000000;
    border-radius: 20px;
    padding: 10px 20px;
    cursor: pointer;
    background-color: #F7CAC9;
    color: #000000;
    transition: background-color 0.3s;
    margin: 20px;
    font-family: 'cookierun';
  }
  
  button:hover {
    background-color: #DAA59A;
    /* 호버 시 배경색 변경 */
  }
  
  .big {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  
  .말풍선디브 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .말풍선 {
    width: 160px;
    padding: 40px;
    border-radius: 40px;
    border: 2px solid #000000;
  }
  
  .조타 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: url('@/assets/초원.png') rgb(255, 255, 243, 1) 50% / 100% 100% no-repeat;
  }
  
  img {
    width: 160px;
    height: 232px;
  }
  
  .양옆 {
    display: flex;
    justify-content: space-around;
  }
  
  .writeform {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 317px;
    height: 750px;
  }
  </style>
  