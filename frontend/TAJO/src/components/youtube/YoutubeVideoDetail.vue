<template>
  <div v-if="store.selectedVideo">
    <h4>영상상세보기</h4>
    <div class="video-container">
      <iframe
        width="560"
        height="315"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen
      ></iframe>
    </div>
    <button @click="closeVideo">영상닫기</button>
  </div>
</template>

<style scoped>
.video-container {
  border: 2px solid rgb(0, 0, 0);
  margin-top: 12px;
  position: relative;
  padding-bottom: 56.25%; /* 16:9 비율의 동영상에 대한 높이 설정 */
  height: 0;
  overflow: hidden;
}

iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

button {
  margin-top: 12px; /* 버튼과 동영상 사이의 간격 조절 */
  padding: 3px;
  margin-bottom: 10px;
    background-color: aliceblue;
    font-family: 'cookierun';
}
</style>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { computed } from 'vue';

const store = useYoutubeStore();

const videoURL = computed(() => {
  const videoId = store.selectedVideo.id.videoId;
  return `https://www.youtube.com/embed/${videoId}`;
});

const closeVideo = () => {
  store.selectedVideo = false;
};
</script>
