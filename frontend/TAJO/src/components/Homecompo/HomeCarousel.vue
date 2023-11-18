<template>
  <div class="carousel-container">
    <div class="carousel">
      <div class="slides" :style="{ width: `${carouselItems.length * slideWidth}px` }">
        <div v-for="(item, index) in carouselItems" :key="index" class="slide"
          :style="{ opacity: currentIndex === index ? 1 : 0, zIndex: currentIndex === index ? 1 : 0 }">
          <img class="picture" :src="item.imageUrl" alt="Slide">
          <div class="caption"></div>
        </div>
      </div>
      <div class="indicators">
        <div v-for="(item, index) in carouselItems" :key="index" class="indicator"
          :class="{ 'active': currentIndex === index }" @click="goToSlide(index)"
          :style="{ zIndex: 2 }"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import ostrich1 from '@/assets/분홍타조.jpeg';
import ostrich2 from '@/assets/호수타조.jpeg';
import ostrich3 from '@/assets/타이거.jpeg';

const carouselItems = ref([
  {
    imageUrl: ostrich1,
    title: 'Slide 1',
    description: 'Description for Slide 1',
  },
  {
    imageUrl: ostrich2,
    title: 'Slide 2',
    description: 'Description for Slide 2',
  },
  {
    imageUrl: ostrich3,
    title: 'Slide 3',
    description: 'Description for Slide 3',
  },
]);

const currentIndex = ref(0);
const slideWidth = 1200; // 슬라이드의 고정된 폭

const prevSlide = () => {
  currentIndex.value = (currentIndex.value - 1 + carouselItems.value.length) % carouselItems.value.length;
};

const nextSlide = () => {
  currentIndex.value = (currentIndex.value + 1) % carouselItems.value.length;
};

const goToSlide = (index) => {
  currentIndex.value = index;
};

onMounted(() => {
  const intervalId = setInterval(nextSlide, 1500);

  onBeforeUnmount(() => {
    clearInterval(intervalId);
  });
});
</script>

<style scoped>
.carousel-container {
  width: 100%;
  height: 500px;
  overflow: hidden;
}

.picture {
  width: 100%;
  height: 450px;
  object-fit: cover;
  z-index: 1;
}

.carousel {
  display: flex;
  flex-direction: column;
  position: relative;
  overflow: hidden;
  height: 100%;
}

.slides {
  display: flex;
  transition: opacity 0.5s ease-in-out; /* 이미지 전환 시 부드러운 페이드 인/아웃 효과 추가 */
}

.slide {
  flex: 0 0 100%;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: opacity 0.5s ease-in-out; /* 이미지 전환 시 부드러운 페이드 인/아웃 효과 추가 */
}

.indicators {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.indicator {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: #ccc;
  margin: 0 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

.indicator.active {
  background-color: #333;
}

.caption {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
}
</style>
