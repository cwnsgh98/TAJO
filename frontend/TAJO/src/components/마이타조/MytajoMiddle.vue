<template>
    <div class="mid">
        <div class="nextlevel">
            <span>다음 등급까지</span>
            <div>
                <span class="남은거리">172km</span>
                <span> 남았어요.</span>
            </div>
        </div>
        <img :class="{ movingImage: isMoving }" src="@/assets/ostrich8.png">
    <img :class="{ '작은타조': true, movingImage: isMoving }" src="@/assets/ostrich7.png">
    <img :class="{ '자전거탄타조': true, movingImage: isMoving }" src="@/assets/자전거리버스타조.png">
        <div class="주행기록">
            <span>오늘 아직 라이딩하지 않으셨네요 ㅠㅠ</span>
            <button @click="toggleMovement">
            주행 시작하기
            </button>
            <div>
    <button @click="toggleResult">Toggle Result</button>
    <button @click="toggleWriteForm">Toggle Write Form</button>
  </div>
        </div>

    </div>
</template>

<script setup>
import { ref ,inject } from 'vue';

const showResult = inject('showResult');
const showWriteForm = inject('showWriteForm');

const toggleResult = () => {
  showResult.value = !showResult.value;
  showWriteForm.value = false;
};

const toggleWriteForm = () => {
  showWriteForm.value = !showWriteForm.value;
  showResult.value = false;
};

const isMoving = ref(false);

const toggleMovement = () => {
  isMoving.value = !isMoving.value;

  // 이미지의 초기 위치로 되돌림
  if (!isMoving.value) {
    setTimeout(() => {
      isMoving.value = true;
    }, 600);
  }
};
</script>

<style scoped>
.movingImage {
  height: 80px;
  width: 70px;
  position: absolute;
  top: 70%;
  right: 40%;
  transition: transform 0.5s ease-in-out;
}

/* 애니메이션 활성화 시 이미지 이동 */
.movingImage {
  transform: translateX(50px); /* 이동할 거리 및 방향 설정 */
}

.자전거탄타조{
    height: 100px;
    width: 85px;
    position: absolute;
    top:60%;
    right: 26%;
}
.작은타조{
    height: 70px;
    width: 64px;
    position: absolute;
    top:60%;
    right: 37%;
}
img{
    height: 80px;
    width: 70px;
    position: absolute;
    top:70%;
    right: 40%;
}
.nextlevel {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    width: 140px;
    height: 60px;
    background-color: #F5F5E9;
    border: 2px solid #5C5C5C;
    border-radius: 20px;
    position: absolute;
    top:50%;
    right: 30%;
}
.남은거리{
    color: rgb(12, 161, 4);
}
.nextlevel span {
    font-weight: 700;
    font-size: 17px;
}

.주행기록 span {
    font-weight: 700;
    font-size: 23px;
}

.주행기록 a {
    font-weight: 700;
    font-size: 23px;
    color: rgb(250, 85, 85);
}

.주행기록 {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    width: 400px;
    height: 90px;
    background-color: #F5F5E9;
    border: 2px solid #5C5C5C;
    border-radius: 20px;
    position: absolute;
    right: 230px;
    bottom: 40px;
}

.mid {
    background: url('@/assets/나무길배경.png') rgb(255, 255, 236) 50% / 100% 100% no-repeat;
    
    height: 760px;
    position: relative;
    top: 0px;
}
</style>