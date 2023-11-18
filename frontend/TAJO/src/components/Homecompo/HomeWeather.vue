<template>
    <div class="weather">
        <div class="main">
            <div class="main-left">
                <div class="오늘의날씨">
                    <span>오늘의 날씨는, 라이딩하기 좋은 날씨 에요!</span>
                </div>
                <div class="날씨">
                    <div>
                        지도api사용 칸입니다
                    </div>
                </div>
            </div>
            <div class="main-right">
                <div class="옷추천">
                    <span>가벼운 외투를 챙기면 좋아요.</span>
                </div>
                <div class="옷이미지">
                    <img src="@/assets/가벼운외투.png">
                </div>                    
            </div>
                
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.weather{
    width: 900px;
    height: 300px;
}
.main{
    margin-top: 5%;
    display: flex;
    justify-content: space-between;
    border: 2px solid #5C5C5C;
}
.main-left, .main-right{
    display: flex;
    flex-direction: column;
}
img{
    width: 160px;
    height: 160px;
}
</style>