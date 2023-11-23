<template>
    <div class="InfoMain">
        <div class="course-Info">
            <div class="map-name">
                <RouterLink to="/Wherego/info"><button class="뒤로">&lt;</button></RouterLink>
                <span class="코스이름"> {{ course.name }}</span>
            </div>
            <div class="이미지">
                <img class="코스이미지" :src="`/src/assets/${course.img}`">
            </div>
            <div class="별점찜">
                <div class="별">
                    <span class="코별">코스 별점 : </span>
                    <span v-for="index in 5" :key="index" class="별아이콘 stroked-text"
                        :class="{ '별채움': index <= course.starAvg }">&#9733;</span>
                </div>
                <button v-if="!alreadyZZIM&&userNickname !='익명'" @click="addZzim">찜</button>
                <button v-if="alreadyZZIM&&userNickname !='익명'" @click="removeZzim">찜 해제</button>
            </div>
            <div class="course-Info2">
                <span>코스 주소 : {{ course.location }}</span>
                <span>출발지 : {{ course.from }}</span>
                <span>도착지 : {{ course.to }}</span>
                <span>코스거리 : {{ course.distance }} km</span>
                <span class="마진바텀">예상 소요시간 : {{ course.time }}</span>
                <hr>
            </div>
        </div>
        <div class="review-head">
            <!-- <span class="리뷰포지션">리뷰</span> -->
            <RouterLink :to="`/Wherego/${Route.params.courseid}/Review`">
                <button class="스틱키">리뷰작성</button>
            </RouterLink>
        </div>


        <div class="review-container course-review">
            <div class="review" v-for="review in reviewList" :key="review.reviewid">
              
                <div class="userbox">
                    <img class="user-avatar" src="/src/assets/ostrich1.png" alt="User Avatar" />
                    <div class="user">
                        <div class="user-info">
                            <span class="user-name">{{ review.writer }}</span>
                        </div>
                        <div class="rating">
                            <span v-for="index in 5" :key="index" class="별아이콘 stroked-text"
                                :class="{ '별채움': index <= review.star }">&#9733;</span>
                        </div>
                    </div>
                </div>
                <div class="review-content">
                    <p class="comment">{{ review.content }}</p>
                </div>
                <div class="버삭">
                <button class="삭제" v-if="userNickname === review.writer" @click="removeReview(review.reviewid)">삭제</button>
                
            </div>
            </div>
        </div>
    </div>
</template>
<script setup>
import { ref, onMounted, watch, onBeforeUpdate} from 'vue'
import { useRoute } from 'vue-router';
import { useCourseStore } from '../../stores/course';
import { useReviewStore } from '../../stores/review';
import { useZzimStore } from '../../stores/zzim'
const courseStore = useCourseStore();
const reviewStore = useReviewStore();
const zzimStore = useZzimStore();
const Route = useRoute();
const course = ref({});
const reviewList = ref([]);
const userNickname = ref('익명');
const alreadyZZIM = ref(false);
onMounted(async() => {
    try {
        alreadyZZIM.value = false;
        course.value = await courseStore.getCourse(Route.params.courseid);
        reviewList.value = await reviewStore.getReviewList(Route.params.courseid);
        userNickname.value = localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).nickname : '익명';
        await zzimStore.getZzimList();

        for(const co of zzimStore.zzimList) {
            if(co.courseid === course.value.courseid) {
                alreadyZZIM.value = true;
                break;
            }
        }

    } catch (error){
        console.log(error);
    }

    watch( () => [reviewStore.reviewList],async  ([newList]) => {
        reviewList.value = newList;
        course.value = await courseStore.getCourse(Route.params.courseid);
    });

    watch( () => [zzimStore.zzimList],  ([zzimList]) => {
        alreadyZZIM.value = false;
        for(const co of zzimList) {
            if(co.courseid === course.value.courseid) {
                alreadyZZIM.value = true;
                break;
            }
        }
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

const addZzim = async function() {
    await zzimStore.insertZzim(Route.params.courseid);
}

const removeZzim = async function() {
    await zzimStore.deleteZzim(Route.params.courseid);
}
</script>

<style scoped>
.버삭{
    display: flex;
    justify-content: center;
    align-items: center;
}
.userbox{
    display: flex;
    justify-content: center;
    align-items: center;
}
.user {
    display: flex;
    flex-direction: column;
}

.마진바텀 {
    margin-bottom: 4px;
}

.stroked-text {
    -webkit-text-stroke: 0.7px #000000;
    /* Safari 및 Chrome 브라우저에서 사용 가능 */
    /* 텍스트 색상 지정 */
    font-family: 'cookierun';
}

.뒤로 {
    background-color: rgb(218, 238, 255);
    margin-right: 3px;
}

.코별 {
    margin-right: 10px;
}

.별점 {
    width: 10%;
    text-align: center;
    color: #000000;
}

.별 {
    display: flex;
    justify-content: flex-start;
    align-items: center;
}

.내용 {
    width: 60%;
}

.이름 {
    color: rgb(4, 4, 4);
    width: 20%;
    text-align: center;
}

.하위 {
    font-size: 14px;
}

.코스이름 {
    padding-left: 130px;
    padding-right: 145px;
    border: 2px solid black;
    background-color: rgb(255, 255, 255);
}

.별점찜 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 14px;
}

.InfoMain {
    display: flex;
    flex-direction: column;
    justify-content: center;
    min-height: 672px;
    max-height: 672px;
    max-width: 410px;
    min-width: 410px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    border: 2px solid #000000;
    background-color: #eef2f8
}


span {
    padding-top: 3px;
    padding-bottom: 3px;
}

.course-Info2 span {
    font-size: 14px;
}


.별아이콘 {
    color: #fffdec;
    font-size: 14px;
    /* 별 색상 지정 */
}

.별채움 {
    color: #ffd700;
    /* 채워진 별의 색상 */
    font-size: 14px;
}

button {
    background-color: #ffd8d5;
    font-family: 'cookierun';
    color: rgb(0, 0, 0);
    border: 1px solid black;
    padding: 4px 7px;
    cursor: pointer;
    border-radius: 3px;
}

.삭제 {
    padding: 5px 5px;
    background-color: #e2eaff;
}

button:hover {
    background-color: #d32f2f;
}

.course-Info2 {
    display: flex;
    flex-direction: column;
}


.map-name {
    position: sticky;
    top: 0;
    z-index: 3;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    background-color: #eef2f8;
    margin-bottom: 5px;
}


.review-head {
    margin-top: 3px;
    margin-bottom: 4px;
    display: flex;
    justify-content: end;
    position: static;
    top: 0;
    overflow: hidden;
}

.course-Info {
    position: relative;
    display: flex;
    flex-direction: column;
    flex-grow: 1;
    /* border: solid 2px #c72323; */
    overflow-y: hidden;
    /* 세로 스크롤 가능하도록 설정 */
    max-height: 407px;
    min-height: 407px;
    margin-bottom: 2px;
    /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}

.course-review {
    position: relative;
    flex-grow: 1;
    /* border: solid 1px #180a99; */
    overflow-y: auto;
    /* 세로 스크롤 가능하도록 설정 */
    min-height: 190px;
    max-height: 190px;
    max-width: 400px;
    margin-bottom: 20px;

    border-top: solid 2px #e7e7e7;
    border-bottom: solid 2px #e7e7e7;
    /* 스크롤 영역의 최대 높이를 지정, 필요에 따라 조절 가능 */
}

.코스이미지 {
    border: solid 2px #000000;
    height: 200px;
    width: 406px;
}

.review-container {
    width: 100%;
    max-width: 600px;
    /* 원하는 최대 너비로 조절 */
    margin: 0 auto;
}

.review {
    display: flex;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    padding: 10px;
    min-height: 70px;
}


.user-avatar {
    width: 40px;
    height: 40px;
    margin-right: 10px;
}

.user-name {
    font-weight: bold;
    font-size: 14px;
}

.review-content {
    display: flex;
    align-items: center;
    margin-left: 23px;
    flex-grow: 1;
    font-size: 17px;
    max-width: 180px;
    margin-right: 10px;
}


.comment {
    margin-top: 5px;
}
</style>