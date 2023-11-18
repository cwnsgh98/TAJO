<template>
    <div class="zzimmain">
        <div class="transe">
            <WheretajoTrans />
        </div>
        <div class="middle">
            <div class="zzim">
                <span>찜한코스</span>
                <div class="zzim-course">
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import WheretajoTrans from '../components/어디가조/WheretajoTrans.vue';
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