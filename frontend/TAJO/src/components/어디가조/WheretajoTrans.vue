<template>
    <div>
        <div class="화면바꾸기">
            <RouterLink class="router-link" :to="{ name: 'Wherego' }">
                <button>지도보기</button>
            </RouterLink>
            <RouterLink class="router-link" :to="{ name: 'Wheregozzim' }">
                <button>찜한코스</button>
            </RouterLink>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.화면바꾸기{
    display: flex;
    background-color: beige;
    border: solid 1px #d2d2d2;
    height: 40px;
    align-items: center;
}
button{
    margin-left: 15px;
}
</style>