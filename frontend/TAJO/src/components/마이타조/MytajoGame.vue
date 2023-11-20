<template>
    <div>
      <span>목숨: <i>{{ life }}</i></span>
      <span style="margin-left: 10px;">점수: <i>{{ score }}</i></span>
      <h2 v-show="gameState === 0">스페이스를 눌러 게임을 시작</h2>
      <canvas ref="canvas"></canvas>
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
  let baseSpeed = 3; // 기본 속도
  let speedIncreaseInterval = 1000; // 속도를 증가시킬 간격
  let speedIncreaseFactor = 0.2; // 속도를 증가시키는 비율
  
  const dino = {
    x: 10,
    y: 200,
    width: 57,
    height: 57,
    draw() {
      const ctx = canvas.value.getContext('2d');
      ctx.drawImage(dinoImg, this.x, this.y, this.width, this.height);
    },
  };
  
  class Cactus {
    constructor(baseSpeed) {
      this.width = 20 + getRandomInt(-3, 4);
      this.height = 30 + getRandomInt(-3, 4);
      this.x = 500;
      this.y = 250 - this.height;
      this.speed = baseSpeed;
    }
  
    draw() {
      const ctx = canvas.value.getContext('2d');
      ctx.fillStyle = 'red';
      ctx.fillRect(this.x, this.y, this.width, this.height);
    }
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
  
    if (timer % 120 === 0) {
      const cactus = new Cactus(baseSpeed);
      cactusArr.value.push(cactus);
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
        dino.y += 2;
      }
    }
  
    if (jumpState.value === 1) {
      jumpTimer.value++;
      dino.y -= 2;
  
      if (jumpTimer.value > 50) {
        jumpState.value = 2;
      }
    }
  
    if (jumpState.value === 2) {
      dino.y += 2;
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
    const xValue = cactus.x - (dino.x + dino.width);
    const yValue = cactus.y - (dino.y + dino.height);
    if (xValue < 0 && yValue < 0) {
      life.value--;
      if (life.value === 0) {
        alert('게임오버');
        gameState.value = 0;
        cancelAnimationFrame(animation);
        location.reload();
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
      frameAction();
    };
  });
  
  onUnmounted(() => {
    cancelAnimationFrame(animation);
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
  </script>
  