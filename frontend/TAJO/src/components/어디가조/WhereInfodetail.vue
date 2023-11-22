<template>
    <div class="InfoMain">
        <div class="course-Info">
            <div class="이미지">
                이미지를 넣을 공간이오
                <RouterLink to="/Wherego"><button>뒤로가기</button></RouterLink>
            </div>
            <div class="map-name">
                <span>맵 이름입니다</span>
                <span>리뷰의 평점과</span>
                <button>찜</button>
                <hr>
            </div>
            <div class="course-Info2">
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
                <RouterLink :to="`/Wherego/${Route.params.courseid}/Review`">
                    <button>리뷰작성</button>
                </RouterLink>
            </div>
            <div class="review-list">
                <span>라이더리뷰의 개수</span>
                <span>리뷰리스트들이 들어갈 자리입니다.</span>
                <div class="리뷰">
                    <span>리뷰입니당</span>
                    <span>후기남깁니다</span>
                    <button>삭제</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const Route = useRoute();
</script>

<style scoped>
div {
    border: solid 1px #a7a7a7;
}

span {
    padding-top: 3px;
    padding-bottom: 3px;
}
.course-Info2{
    display: flex;
    flex-direction: column;
}
.review-list {
    display: flex;
    flex-direction: column;
}

.map-name {
    display: flex;
    flex-direction: column;
}

.review-head {
    display: flex;
    justify-content: space-between;
}

.course-list {
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
    overflow-y: auto;
    /* 세로 스크롤 가능하도록 설정 */
    min-height: 400px;
    max-height: 300px;
    /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}

.course-review {
    flex-grow: 1;
    border: solid 1px #a7a7a7;
    overflow-y: auto;
    /* 세로 스크롤 가능하도록 설정 */
    min-height: 300px;
    max-height: 300px;
    /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}

.review-list {
    min-height: 250px;

}</style>