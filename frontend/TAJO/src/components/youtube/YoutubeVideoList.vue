<template>
    <div>
        <h4 class="검색결과">검색 결과</h4>
        <div class="pagination-container">
            <div v-for="video in displayedVideos" :key="video.id.videoId" @click="clickVideo(video)">
                <!-- 영상 아이템의 내용 -->
                <div class="영상">
                    <div class="썸네일">
                        <img class="썸네일2" :src="video.snippet.thumbnails.default.url">
                    </div>
                    <div class="텍스트">
                        <span class="영상제목">{{ decodeEntities(video.snippet.title) }}</span>
                    </div>
                </div>
            </div>
            <div class="버튼디브">
                <button class="버튼버튼" @click="prevPage" :disabled="currentPage <= 1">이전 페이지</button>
                <span class="가운데">{{ currentPage }} / {{ totalPage }}</span>
                <button class="버튼버튼 오른쪽" @click="nextPage" :disabled="currentPage >= totalPage">다음 페이지</button>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref, computed } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';

const store = useYoutubeStore();
const videosPerPage = 4;
let currentPage = ref(1);

const nextPage = () => {
    currentPage.value++;
};

const prevPage = () => {
    currentPage.value--;
    if (currentPage.value < 1) {
        currentPage.value = 1;
    }
};

const totalPage = computed(() => Math.ceil(store.videos.length / videosPerPage));

const displayedVideos = computed(() => {
    const startIndex = (currentPage.value - 1) * videosPerPage;
    const endIndex = startIndex + videosPerPage;
    return store.videos.slice(startIndex, endIndex);
});

const clickVideo = (video) => {
    store.clickVideo(video);
};

const decodeEntities = (text) => {
    const element = document.createElement('div');
    element.innerHTML = text;
    return element.textContent;
};
</script>
  
<style scoped>
/* 스타일링 내용 */
.오른쪽{
    margin-left: 12px;
}
.가운데{
    margin-left: 7px;
    margin-right: 7px;
}
.버튼디브{
    padding: 12px;
    display: flex;
    align-items: center;
}
.검색결과 {
    margin-bottom: 13px;
}

.영상 {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.영상제목 {
    font-size: 13px;
}

.버튼버튼 {
    background-color: aliceblue;
    font-family: 'cookierun';
    padding: 3px;
}

.텍스트 {
    padding: 10px;
    border: 1px solid rgb(255, 255, 255);
    min-width: 210px;
    max-width: 210px;
    min-height: 70px;
    max-height: 70px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
}

.썸네일 {
    border-radius: 10px;
    padding: 2px;
    display: flex;
    align-items: center;
}

.썸네일2 {
    border-radius: 20px;
    padding: 2px;
    border: 2px solid rgb(23, 232, 255);
}

img {
    min-height: 100px;
    max-height: 100px;
    min-width: 130px;
    max-width: 130px;
}

.pagination-container {
    padding: 12px;
    border: 2px solid #a7a7a7;
}

/* 그 외 스타일링 내용 추가 */</style>
  