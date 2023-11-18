<template>
    <div class="InfoMain">
        <div class="course-list">
            <span>코스목록 10개</span>
        </div>
        <div class="course-Info">
            <div class="Info-map">
                맵이 들어갈 자리입니다
            </div>
            <div class="map-name">
                <span>맵 이름입니다</span>
                <span>리뷰의 평점과</span>
                <span>라이더리뷰의 개수</span>
                <hr>
            </div>
            <div class="course-Info">
                <span>코스정보</span>
                <span>코스거리 : 추후업데이트 예정</span>
                <span>예상 소요시간 : 8시간 50분</span>
                <hr>
            </div>
            <div class="course-explain">
                <span>코스설명</span>
                <p>코스에 대한 장대한 설명이 여기 들어갈것이오.</p>
            </div>
        </div>
        <div class="course-review">
            <div class="review-head">
                <span>리뷰</span>
                <button>리뷰작성</button>
            </div>
            <div class="review-list">
                리뷰리스트들이 들어갈 자리입니다.
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
div{
    border: solid 1px #a7a7a7;
}
span{
    padding-top: 3px;
    padding-bottom: 3px;
}
.map-name{
    display: flex;
    flex-direction: column;
}
.review-head{
    display: flex;
    justify-content: space-between;
}
.course-list{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 40px;
}
.course-Info {
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    border: solid 1px #a7a7a7;
    overflow-y: auto; /* 세로 스크롤 가능하도록 설정 */
    min-height: 550px;
    max-height: 300px; /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
  }
  .course-review{
    flex-grow: 1;
    border: solid 1px #a7a7a7;
    overflow-y: auto; /* 세로 스크롤 가능하도록 설정 */
    max-height: 150px; /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}
.review-list{
    min-height: 250px;
    
  }

</style>