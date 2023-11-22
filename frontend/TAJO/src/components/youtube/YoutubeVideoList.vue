<template>
    <div>
        <h4>검색 결과</h4>
        <div >
            <YoutubeVideoListItem class="영상목록" v-for="video in store.videos" :key="video.id.videoId" :video="video" />
        </div>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube'
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style  scoped>
.영상목록{
    display: flex;
    /* flex-direction: row; */
    min-height: 120px;
    min-width: 400px;
    justify-content: center;
    align-items: center;

    border: solid 2px #000000;
    border-radius: 30px;
    margin-bottom: 4px;
}

</style>