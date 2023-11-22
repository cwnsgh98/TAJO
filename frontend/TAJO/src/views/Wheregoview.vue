<template>
    <div class="Where">
        <div class="wherehead">
            <WheretajoTrans/>
        </div>
        <div class="wheremain">
            <div class="right">
                <WheretajoMap @toggleResult="toggleResult"/>
            </div>
            <div class="choose">
                <WheretajoInfo v-if="showResult"/>
                <WheretajoHelp v-if="!showResult"/>
            </div>
        </div>
    </div>
</template>

<script setup>
import WheretajoTrans from '../components/어디가조/WheretajoTrans.vue';
    import WheretajoHelp from '../components/어디가조/WheretajoHelp.vue';
    import WheretajoInfo from '../components/어디가조/WheretajoInfo.vue';
    import WheretajoMap from '../components/어디가조/WheretajoMap.vue';
    import { provide, ref } from 'vue';

  
const showResult = ref(true);

provide('showResult', showResult);

</script>

<style scoped>
.right{
    min-width: 1200px;
}
.choose{
    margin-top: 10px;
    margin-left: 40px;
    min-width: 420px;
    height: 712px;
}
.wheremain{
    display: flex;
}
.Where{
    display: flex;
    flex-direction: column;
}
</style>