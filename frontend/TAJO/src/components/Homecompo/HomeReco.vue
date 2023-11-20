<template>
    <div>
        <div class="recobox">
            <div class="추천">타조에 관한 영상시청</div>
            <div class="reco-list">
                <div class="코스"> <img src="@/assets/hot.png"> <span>스님을 사랑하는 타조</span> </div>
                <div class="코스"> <img src="@/assets/best.png"> <span>대전 도로를 달리는 두마리의 타조</span> </div>
                <div class="코스"> <img src="@/assets/heart.png"> <span>사막에서 자전거 타는 사람들과 함께뛰는 타조</span> </div>
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