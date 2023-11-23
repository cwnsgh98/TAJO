<template>
    <div class="zzimmain">
        <div class="transe">
            <WheretajoTrans />
        </div>
        <div class="top">
        <span>찜한코스</span>
    </div>
        <div class="middle">
            <div class="zzim">
                <div class="zzim-course" v-for="course in zzimList" :key="course.courseid">
                    <div class="이미지">
                        <img class="코스이미지" :src="`/src/assets/${course.img}`">                      
                    </div>
                    <div class="map-name">
                        <span> {{ course.name }}</span>
                        <hr>
                    </div>
                    <div class="course-Info2">
                        <!-- <span>코스 주소 : {{ course.location }}</span> -->
                        <span>출발지 : {{ course.from }}</span>
                        <span>도착지 : {{ course.to }}</span>
                        <span>코스거리 : {{ course.distance }}km</span>
                        <span>예상 소요시간 : {{ course.time }}</span>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import WheretajoTrans from '../components/어디가조/WheretajoTrans.vue';
import { ref, onMounted, watch, onBeforeUpdate } from 'vue'
import { useZzimStore } from '@/stores/zzim'
const zzimStore = useZzimStore();
const zzimList = ref([]);
onMounted(async () => {
    try {

        await zzimStore.getZzimList();
        zzimList.value = zzimStore.zzimList;


    } catch (error) {
        console.log(error);
    }
    watch(() => [zzimStore.zzimList], ([newzzimList]) => {
        zzimList.value = newzzimList;
    });


});
</script>

<style scoped>
.top{
    display: flex;
    justify-content: center;
    align-items: center;
}
.course-Info2{
    display: flex;
    flex-direction: column;
}
.map-name{
    display: flex;
    flex-direction: column;
}
img{
    height: 200px;
    width :200px;
    border: 2px solid black;
}
.middle {
    display: flex;
    justify-content: center;
}
.zzim-course{
    margin: 20px;
    display: flex;
    flex-direction: column;
    min-width: 204px;
    max-width: 204px;
    min-height: 321px;
    max-height: 321px;
    border: solid 1px black;
}
.zzimmain {
    display: flex;
    flex-direction: column;
}


.zzim {
    border: solid 1px #4e4e4e;
    min-width: 900px;
    max-width: 900px;
    min-height: 690px;
    max-height: 690px;
    display: flex;
    overflow: auto;
}
</style>