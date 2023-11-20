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
  import { ref} from 'vue';
  
  const props = defineProps(['showResult']); // 부모로부터 전달받은 showResult prop
  
  // 상태 변수 정의
  const isDriving = ref(false);
  const isPaused = ref(false); // 일시정지 상태
  const timer = ref(0); // 초기값을 0으로 설정
  const selectedSpeed = ref(''); // 새로운 상태 변수 (속도 선택)
  let intervalId;
  const animatedText = ref("주행 시작 !");
  
  // emit 함수 정의
  const emit = defineEmits();
  
  // 주행 시작 함수
  const startDriving = () => {
    isDriving.value = true;
    // 선택한 속도 표시
    console.log("Selected Speed:", selectedSpeed.value);
  
    // 1초마다 타이머 갱신
    intervalId = setInterval(() => {
      timer.value += 1;
  
      // 텍스트 변경 애니메이션
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
  
  // 주행 종료 함수
  const endDriving = () => {
    isDriving.value = false;
    isPaused.value = false;
    clearInterval(intervalId); // 타이머 중지
    animatedText.value = "주행시작 ! "; // 초기 텍스트로 리셋
  };
  
  // 타이머 초기화 함수
  const resetTimer = () => {
    timer.value = 0;
    animatedText.value = "주행시작 ! "; // 초기 텍스트로 리셋
  };
  
  // 타이머 일시정지 및 재개 함수
  const pauseResumeTimer = () => {
    if (isDriving.value) {
      if (isPaused.value) {
        // 주행 중이면서 일시정지 중인 경우
        intervalId = setInterval(() => {
          timer.value += 1;
        }, 1000);
      } else {
        // 주행 중이면서 일시정지 중이 아닌 경우
        clearInterval(intervalId); // 타이머 중지
      }
  
      isPaused.value = !isPaused.value; // 일시정지 상태 반전
    }
  };
  
  const saveAndShowResult = () => {
    emit('save-result'); // 이벤트 발생
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
  