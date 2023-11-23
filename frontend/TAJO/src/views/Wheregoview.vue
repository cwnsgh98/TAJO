<template>
    <div class="Where">
        <div class="wherehead">
            <WheretajoTrans />
        </div>
        <div class="wheremain">
            <div class="right">
                <WheretajoMap @help="goHelp" @marker-click="markerClick"/>
            </div>
            <div class="choose">
                <RouterView />
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, watch,onMounted } from 'vue';
import { RouterLink, RouterView, useRouter } from 'vue-router'
import WheretajoTrans from '../components/어디가조/WheretajoTrans.vue';
import WheretajoMap from '../components/어디가조/WheretajoMap.vue';
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
const markerClick = async function(id) {
    await router.push('/Wherego/info');
    await router.push(`/Wherego/${id}/Detail`);
}
</script>

<style scoped>
.right {
    min-width: 1200px;
}

.choose {
    margin-top: 10px;
    margin-left: 40px;
    min-width: 420px;
    height: 712px;
}

.wheremain {
    display: flex;
    max-height: 680px;
}

.Where {
    display: flex;
    flex-direction: column;
}
</style>