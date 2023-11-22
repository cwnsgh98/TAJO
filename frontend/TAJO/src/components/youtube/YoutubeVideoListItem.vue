<template>
  <div class="영상" @click="clickVideo">
      <div class="썸네일">
        <img class="썸네일2" :src="video.snippet.thumbnails.default.url">
      </div>
      <div class="텍스트">
        <span class="영상제목">{{ decodeEntities(video.snippet.title) }}</span>
      </div>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
});

const decodeEntities = function (text) {
  const element = document.createElement('div');
  element.innerHTML = text;
  return element.textContent;
};

const clickVideo = function () {
  store.clickVideo(props.video);
};
</script>

<style scoped>
.영상제목 {
  font-size: 14px;
}

.텍스트 {
  padding: 10px;
  border: 1px solid #969696;
  min-width: 210px;
  max-width: 210px;
  min-height: 40px;
  max-height: 40px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.썸네일{
  border-radius: 10px;
  padding: 2px;
  display: flex;
  align-items: center;
}
.썸네일2{
  border-radius: 20px;
  padding: 2px;
}
img {
  min-height: 120px;
  max-height: 120px;
  min-width: 150px;
  max-width: 150px;
}

.영상 {
  display: flex;
  flex-direction: row;
}
</style>
