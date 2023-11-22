<template>
    <div class="HelpMain">
        <div class="result-head">
            <span>다음 영상을 보고 수리해볼까요 ?</span>
        </div>
        <div class="helpselect">
            <!-- <span>고장부위를 선택하세요</span>
                <div class="selectpart">
                    <button>안장</button>
                    <button>체인</button>
                    <button>핸들</button>
                    <button>바퀴</button>
                    <button>브레이크</button>
                    <button style="color:red">타조를 사랑하는 나의 마음</button> -->
                    <!-- </div> -->
                    <YoutubeVideoSearch/>
                     <YoutubeVideoDetail/>
            </div>
            <div class="helpresult">
                <div class="result-show">
                    <!--v-for 로 영상들 받아서 돌려야함 일단은 기본세팅-->
                    <div class="video-list">
                    <YoutubeVideoList/>
                    
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '../../stores/youtube';
import YoutubeVideoDetail from '@/components/youtube/YoutubeVideoDetail.vue'
import YoutubeVideoList from '@/components/youtube/YoutubeVideoList.vue'
import YoutubeVideoSearch from '@/components/youtube/YoutubeVideoSearch.vue'
const store = useYoutubeStore()
</script>

<style scoped>
.HelpMain{
    height: 712px;
    display: flex;
    flex-direction: column;
    padding-top: 20px;
}
</style>