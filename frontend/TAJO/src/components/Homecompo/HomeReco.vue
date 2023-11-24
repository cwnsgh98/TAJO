<template>
    <div>
        <div class="recobox">
            <div class="추천">타조에서 이런 기능을 만나보세요!</div>
            <div class="reco-list">
                <div class="코스" > <img src="@/assets/hot.png"> <span>열심히 달린 기록 조회하기 </span> </div>
                <div class="코스" > <img src="@/assets/best.png"> <span>내 주변 코스 후기보기 </span> </div>
                <div class="코스" > <img src="@/assets/heart.png"> <span>마음에 드는 코스는 함께하기 </span> </div>
            </div>
            <!-- <div class="추천">추천 라이딩코스를 만나보세요!</div>
            <div class="reco-list">
                <div class="코스"> <img src="@/assets/hot.png"> <span>가장 인기있는코스 : 유성온천</span> </div>
                <div class="코스"> <img src="@/assets/best.png"> <span>가장 리뷰많은코스 : 구암역 자전거 코스</span> </div>
                <div class="코스"> <img src="@/assets/heart.png"> <span>가장 찜이 많은 코스 : 삼성화재연수원</span> </div>
            </div> -->
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import { useRouter } from 'vue-router';
import { useCourseStore } from '../../stores/course';
import { useZzimStore } from '../../stores/zzim';
const router = useRouter();
const courseStore = useCourseStore();
const zzimStore = useZzimStore();
const mostViewed = async function() {
    courseStore.getAllCourse();
    
    await router.push('/Wherego/info');
    await new Promise((resolve) => nextTick(resolve));
    courseStore.courseList.sort((a,b)=>{
        if(a.viewCnt > b.viewCnt) return -1;
        if(a.viewCnt < b.viewCnt) return 1;
        return 0;
    })
}

const mostStared = async function() {
    courseStore.getAllCourse();
    courseStore.courseList.sort((a,b)=>{
        if(a.starAvg > b.starAvg) return -1;
        if(a.starAvg < b.starAvg) return 1;
        return 0;
    })
    

    router.push('/Wherego/info');

}

const mostLiked = async function() {
    zzimStore.getZzimList();
    await router.push('/Wherego');

}



</script>

<style scoped>
.추천{
    font-size: 26px;
    margin-bottom: 30px;
}
img{
    width: 30px;
    height: 30px;
    margin-right: 15px;
}
.코스스님{
    width: 60px;
    height: 60px;
    margin-right: 15px;
}
.코스{
display: flex;
align-items: center;
padding: 5px;
}
.recobox{
width: 393px;
height: 267px;
border : 2px solid #5C5C5C;
border-radius: 80px;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
background-color: #F5F5E9;
}
</style>