<template>
    <div class="zzimmain">
        <div class="transe">
            <WheretajoTrans />
        </div>
        <div class="middle">
            <div class="zzim">
                <span>찜한코스</span>
                <div class="zzim-course" v-for="course in zzimList" :key="course.courseid">
                    <div class="이미지">
                        <!--<img class="코스이미지" :src="`@/assets/${course.img}`">-->                      
                    </div>
                    <div class="map-name">
                        <span>코스 이름 : {{ course.name }}</span>
                        <span>코스 별점 : {{ course.starAvg }}</span>
                        <hr>
                    </div>
                    <div class="course-Info2">
                        <span>코스정보</span>
                        <span>코스 주소 : {{ course.location }}</span>
                        <span>출발지 : {{ course.from }}</span>
                        <span>도착지 : {{ course.to }}</span>
                        <span>코스거리 : {{ course.distance }}</span>
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
.middle {
    display: flex;
    justify-content: center;
}

.zzimmain {
    display: flex;
    flex-direction: column;
}

span {
    border: solid 1px #4e4e4e;
}

.zzim {
    border: solid 2px #4e4e4e;
    width: 400px;
    height: 730px;
    display: flex;
    flex-direction: column;
}
</style>