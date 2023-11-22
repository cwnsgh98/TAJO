<template>
  <div class="game-container">
    <span>목숨: <i>{{ life }}</i></span>
    <span style="margin-left: 10px;">점수: <i>{{ score }}</i></span>
    <h2 v-show="gameState === 0" style="margin-left: 120px; margin-top:30px;">스페이스를 눌러 게임을 시작</h2>
    <canvas ref="canvas"></canvas>
    <div v-show="gameOver" class="modal">
      <div class="modal-content">
        <h3>게임 오버!</h3>
        <p>당신의 점수: {{ score }}</p>
        <button @click="restartGame">게임 재시작</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

const dinoImg = new Image();
dinoImg.src = 'src/assets/ostrich5.png';
const canvas = ref(null);
const life = ref(1);
const score = ref(0);
const gameState = ref(0);
const jumpState = ref(0);
const jumpTimer = ref(0);
const cactusArr = ref([]);
let timer = 0;
let animation;
let baseSpeed = 3 ; // 기본 속도
let speedIncreaseInterval = 1000; // 속도를 증가시킬 간격
let speedIncreaseFactor = 0.15; // 속도를 증가시키는 비율
let minSpeedIncreaseInterval = 800; // 최소 간격
let maxSpeedIncreaseInterval = 1600; // 최대 간격
const dino = {
  x: 10,
  y: 200,
  width: 48,
  height: 48,
  draw() {
    const ctx = canvas.value.getContext('2d');
    ctx.drawImage(dinoImg, this.x, this.y, this.width, this.height);
  },
};

class Cactus {
  constructor(baseSpeed) {
    this.image = new Image();
    const randomImageNumber = getRandomInt(1, 26);
    this.image.src = `src/assets/3ban${randomImageNumber}.png`; // 랜덤 이미지 파일 경로 설정
    this.width = 47 + getRandomInt(-3, 4) ; // 너비를 늘리려면 10을 더해주세요
    this.height = 45 + getRandomInt(-3, 4);
    this.x = 500;
    this.isTop = Math.random() < 0.5;
    if (this.isTop) {
      this.y = 130 - this.height;
    } else {
      this.y = 250 - this.height;
    }
    this.speed = baseSpeed;
  }

  draw() {
    const ctx = canvas.value.getContext('2d');
    ctx.drawImage(this.image, this.x, this.y, this.width, this.height);
  }
}
function getRandomSpeedIncreaseInterval() {
  return getRandomInt(minSpeedIncreaseInterval, maxSpeedIncreaseInterval);
}
function frameAction() {
  animation = requestAnimationFrame(frameAction);
  const ctx = canvas.value.getContext('2d');
  ctx.clearRect(0, 0, canvas.value.width, canvas.value.height);
  timer += 1;

  // 일정 시간마다 속도 증가
  if (timer % speedIncreaseInterval === 0) {
    baseSpeed += baseSpeed * speedIncreaseFactor;
  }

  if (timer % getRandomInt(118.3, 120) === 0) {
    const cactus = new Cactus(baseSpeed);
    cactusArr.value.push(cactus);
  }
  // 일정 시간마다 속도 증가
  if (timer % speedIncreaseInterval === 0) {
    baseSpeed += baseSpeed * speedIncreaseFactor;
    speedIncreaseInterval = getRandomSpeedIncreaseInterval(); // 랜덤 간격 설정
  }
  cactusArr.value.forEach((a, i, o) => {
    if (a.x < 0) {
      o.splice(i, 1);
      score.value += 10;
    } else if (collisionDetection(dino, a) < 0) {
      o.splice(i, 1);
    }

    a.x -= a.speed;
    a.draw();
  });

  if (jumpState.value === 0) {
    if (dino.y < 200) {
      dino.y += 3;
    }
  }

  if (jumpState.value === 1) {
    jumpTimer.value+=1.5;
    dino.y -= 3;

    if (jumpTimer.value > 50) {
      jumpState.value = 2;
    }
  }

  if (jumpState.value === 2) {
    dino.y += 3;
    if (dino.y >= 200) {
      dino.y = 200;
      jumpState.value = 0;
      jumpTimer.value = 0;
    }
  }

  drawLine();
  dino.draw();
}

function collisionDetection(dino, cactus) {
  let xValue, yValue;

  if (cactus.isTop) {
    // 상단 장애물인 경우, y값 조건 추가
    xValue = cactus.x - (dino.x + dino.width);
    yValue = dino.y - cactus.y - 60;
    
  } else {
    xValue = cactus.x - (dino.x + dino.width);
    yValue = cactus.y - (dino.y + dino.height);
  }

  if (xValue < 0 && yValue < 0) {
    life.value--;

    if (life.value === 0) {
      showGameOverModal();
      gameState.value = 0;
      cancelAnimationFrame(animation);
      // location.reload();
    }
    return -1;
  } else {
    return 1;
  }
}

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min)) + min;
}

function drawLine() {
  const ctx = canvas.value.getContext('2d');
  ctx.beginPath();
  ctx.moveTo(0, 250);
  ctx.lineTo(600, 250);
  ctx.stroke();
}


onMounted(() => {
  canvas.value.width = window.innerWidth - 100;
  canvas.value.height = window.innerHeight - 100;

  dinoImg.onload = () => {
    dino.draw();
    drawLine();
  };
  document.body.style.overflow = 'hidden';
});

document.addEventListener('keydown', (e) => {
  if (e.code === 'Space' && jumpState.value === 0) {
    e.preventDefault();
    if (gameState.value === 0) {
      gameState.value = 1;
      frameAction();
      document.querySelector('h2').style.display = 'none';
    } else if (gameState.value === 1) {
      jumpState.value = 1;
    }
  }
});

onUnmounted(() => {
  document.body.style.overflow = ''; 
});

const gameOver = ref(false);

function showGameOverModal() {
  gameOver.value = true;
}

function restartGame() {
  gameOver.value = false;
  // 게임 재시작 로직 추가
  location.reload(); // 예제로서 간단하게 페이지 새로고침을 사용
}

</script>

<style scoped>
body,
html {
  overflow: hidden;
  /* 페이지 전체 스크롤 제거 */
}
.game-container {
  position: absolute;
  top: 50%;
  left:45%;
  transform: translate(-50%, -50%);
  transform:scale(1.7);
  overflow: hidden; /* 스크롤 없애기 */
}

canvas {
  width: 150%; /* 부모 요소에 꽉 차도록 설정 */
  height: 150%;
}

.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 56%;
  height: 40%;
  background: rgba(0, 0, 0, 0.5); /* 반투명한 배경 */
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
}

.modal-content h3 {
  color: red;
}

.modal-content button {
  padding: 10px;
  margin-top: 10px;
  cursor: pointer;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
}
</style>