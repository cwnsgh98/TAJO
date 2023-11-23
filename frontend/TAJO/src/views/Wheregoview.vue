<template>
    <div class="Where">
        <div class="wherehead">
            <WheretajoTrans />
        </div>
        <div class="wheremain">
            <div class="right">
                <WheretajoMap @help="goHelp" @marker-click="markerClick" />
            </div>
            <div class="choose">
                <RouterView />
            </div>
        </div>
    </div>
    <div v-if="zzimgo" :class="{ '큰결심': zzimgo }">
        <div class="zzim-course2" v-for="course in zzimList" :key="course.courseid">
            <div class="이미지">
                <img class="코스이미지" :src="`/src/assets/${course.img}`">
            </div>
            <div class="map-name">
                <span class="코스이름"> {{ course.name }}</span>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, watch, onMounted, provide } from 'vue';
import { RouterLink, RouterView, useRouter } from 'vue-router'
import WheretajoTrans from '../components/어디가조/WheretajoTrans.vue';
import WheretajoMap from '../components/어디가조/WheretajoMap.vue';
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

const zzimgo = ref(false);
const togglezzim = () => {
    zzimgo.value = !zzimgo.value;
};
provide('zzimgo', zzimgo);
const isHelp = ref(false);
const goHelp = (() => {
    isHelp.value = !(isHelp.value);
})
watch(() => [isHelp.value], ([isHelp]) => {
    if (isHelp) {
        router.push('/Wherego/Help');
    } else {
        router.push('/Wherego/info');
    }
});
onMounted(() => {
    router.push('/Wherego/info');

})
const router = useRouter();
const markerClick = async function (id) {
    await router.push('/Wherego/info');
    await router.push(`/Wherego/${id}/Detail`);
}
</script>

<style scoped>
.right {
    border-right: 4px solid #a0a0a0;
    min-width: 1200px;
    min-height: 714px;
    max-height: 714px;
}

.choose {
    margin-top: 10px;
    margin-left: 40px;
    min-width: 420px;
    height: 712px;
}

.큰결심 {
    position: fixed;
    top: 140px;
    left: 110px;
    z-index: 4;
    min-width: 190px;
    max-width: 600px;
    max-height: 1000px;
    border: 2px solid rgb(52, 55, 143);
    background-color: aliceblue;
}

.코스이름 {
    font-size: 15px;
}

.코스이미지 {
    min-width: 60px;
    max-width: 60px;
    min-height: 40px;
    max-height: 40px;
    border: solid 1px black;
}
.이미지{
    display: flex;
    justify-content: center;
    align-items: center;
}
.wherehead {
    position: relative;
}
.zzim-course2{
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 12px;
}
.wheremain {
    display: flex;
    min-height: 680px;
    max-height: 680px;

    background-color: rgba(249, 251, 255, 0.635);
    /* background:  linear-gradient(121deg, #FFFFF3 0%, #FFFFED 100%); */
}
.map-name{
    margin-left: 10px;
}
.Where {
    display: flex;
    flex-direction: column;
}
</style>