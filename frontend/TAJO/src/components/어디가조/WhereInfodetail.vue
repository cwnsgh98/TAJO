<template>
    <div class="InfoMain">
        <div class="course-Info">
            <div class="이미지">
                <!--<img class="코스이미지" :src="`@/assets/${course.img}`">-->
                <RouterLink to="/Wherego/info"><button>뒤로가기</button></RouterLink>
            </div>
            <div class="map-name">
                <span>코스 이름 : {{course.name}}</span>
                <span>코스 별점 : {{course.starAvg}}</span>
                <button>찜</button>
                <hr>
            </div>
            <div class="course-Info2">
                <span>코스정보</span>
                <span>코스 주소 : {{ course.location }}</span>
                <span>출발지 : {{ course.from }}</span>
                <span>도착지 : {{ course.to }}</span>
                <span>코스거리 : {{course.distance}}</span>
                <span>예상 소요시간 : {{course.time}}</span>
                <hr>
            </div>
            <!-- <div class="course-explain">
                <span>코스설명</span>
                <p>코스에 대한 장대한 설명이 여기 들어갈것이오.</p>
            </div> -->
        </div>
        <div class="course-review">
            <div class="review-head">
                <span>리뷰</span>
                <RouterLink :to="`/Wherego/${Route.params.courseid}/Review`">
                    <button>리뷰작성</button>
                </RouterLink>
            </div>
            <div class="review-list">
                <span>라이더리뷰의 개수 : {{ course.reviewCnt }}</span>
                <span>리뷰리스트들이 들어갈 자리입니다. </span>
                <div v-if="reviewList.length==0">
                    등록된 리뷰가 없습니다.
                </div>
                <template v-else>
                <div class="리뷰" v-for="review in reviewList" :key="review.reviewid">
                    <span>별점 : {{review.star}}</span>
                    <span>내용 : {{ review.content }}</span>
                    <span>작성자 : {{ review.writer }}</span>
                    <button v-if="userNickname===review.writer" @click="removeReview(review.reviewid)">삭제</button>
                </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch, onBeforeUpdate} from 'vue'
import { useRoute } from 'vue-router';
import { useCourseStore } from '../../stores/course';
import { useReviewStore } from '../../stores/review';
const courseStore = useCourseStore();
const reviewStore = useReviewStore();
const Route = useRoute();
const course = ref({});
const reviewList = ref([]);
const userNickname = ref('익명');
onMounted(async() => {
    try {

        course.value = await courseStore.getCourse(Route.params.courseid);
        reviewList.value = await reviewStore.getReviewList(Route.params.courseid);
        userNickname.value = localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).nickname : '익명';

    } catch (error){
        console.log(error);
    }

    watch( () => [reviewStore.reviewList],async  ([newList]) => {
        reviewList.value = newList;
        course.value = await courseStore.getCourse(Route.params.courseid);
    });

});
const removeReview = async function(reviewid) {
    try {
        await reviewStore.deleteReview(reviewid);
        await reviewStore.getReviewList(Route.params.courseid);
        for(const course of courseStore.courseList) {
            if(course.courseid == Route.params.courseid) {
                course.reviewCnt--;
            }
        }
    } catch (error){
        console.log(error);
    }
    
} 
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