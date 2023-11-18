<template>
    <div class="teachermain">
        <div class="tajoteacher">
            <img src="@/assets/자전거탄타조.png">
        </div>
        <div>
            <span>타조가 알려주는 오늘의 날씨는?</span>
        </div>
    </div>
</template>

<script setup>

</script>

<style  scoped>
.teachermain{
    display: flex;
    flex-direction: column;
    justify-content:center;
    align-items: center;
}
img{
    width: 83px;
    height: 110px;
}
span{
    font-weight: 700;
    font-size: 26px;
}
.tajoteacher{
    padding: 30px;
}
</style>