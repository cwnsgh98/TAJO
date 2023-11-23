<template>
    <div class="realmain">
        <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
            <button class="뒤로">&lt;</button>
        </RouterLink>
        <div class="진짜마지막">
            <div class="mainbox">
                <div class="리뷰별점">
                    <span>리뷰남기기</span>
                    <div class="별그룹">
                        <p class="별점등록"> 별점등록: {{ selectedStars }}/5</p>
                        <div class="row">
                            <div class="별디브" v-for="index in 5" :key="index" @click="selectStar(index)">
                                <img :src="index <= selectedStars ? '/src/assets/ostrich3.png' : '/src/assets/egg.png'"
                                    alt="star" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="리뷰내용">
                    <span class="내용">리뷰내용</span>
                    <input class="리뷰담는곳" type="text" v-model="content">
                </div>
            </div>
            <div class="작성옆으로">
                <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
                    <button @click="writeReview">작성</button>
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
import { useRoute } from 'vue-router';
import { useReviewStore } from '../../stores/review';
import { useCourseStore } from '../../stores/course';
const courseStore = useCourseStore();
const reviewStore = useReviewStore();
const star = ref(0);
const content = ref('');
const Route = useRoute();
const selectedStars = ref(0);
const writeReview = async () => {
    try {
        console.log(localStorage.getItem("loginUser"));
        const courseReview = {
            writer: localStorage.getItem("loginUser") ? JSON.parse(localStorage.getItem("loginUser")).nickname : "익명",
            content: content.value,
            star: selectedStars.value,
            courseid: Route.params.courseid,
        };
        await reviewStore.insertReview(courseReview);
        await reviewStore.getReviewList(Route.params.courseid);
        for (const course of courseStore.courseList) {
            if (course.courseid == Route.params.courseid) {
                course.reviewCnt++;
            }
        }
    } catch (error) {
        console.error('Error inserting review:', error);
    }
};


const selectStar = (star) => {
    selectedStars.value = star;
};
</script>

<style scoped>
.realmain{
    display: flex;
    margin-top: 40px;
}
.작성옆으로 {
    min-width: 290px;
    max-width: 290px;
    display: flex;
    justify-content: end;
}

.별점등록 {
    margin-top: 16px;
    margin-bottom: 16px;
    font-size: 17px;
    color: rgb(73, 73, 73);
}

.row {
    display: flex;
}

.mainbox {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    min-height: 400px;
    max-height: 400px;
}

button {
    background-color: rgb(218, 238, 255);
    font-family: 'cookierun';
    padding: 4px 7px;
    border: 1px solid black;
    cursor: pointer;
    border-radius: 3px;
}

.내용 {
    padding-bottom: 10px;
}

.리뷰담는곳 {
    min-width: 290px;
    max-width: 290px;
    min-height: 140px;
    max-height: 140px;
    font-family: 'cookierun';
    outline: none;
    border: 2px solid black;
    padding-left: 12px;
}

.리뷰담는곳:focus {
    border-color: rgb(115, 164, 208);
    /* 포커스 시 보더 색상 변경 */
    font-family: 'cookierun';
    padding-left: 15px;

}

.별그룹 {
    display: flex;
    flex-direction: column;
}

.리뷰별점 span {
    font-size: 20px;
}

.리뷰별점 {
    min-height: 100px;
    max-height: 100px;
}

.리뷰내용 {
    display: flex;
    flex-direction: column;
    font-family: 'cookierun';
}

.별디브 {
    display: flex;
    flex-direction: row;
    cursor: pointer;
}

img {
    width: 50px;
    height: 50px;
    margin: 5px;
}
.진짜마지막{
    padding-top: 15px;
    padding-bottom: 30px;
    padding-left: 45px;
    min-width: 350px;
    background-color: aliceblue;
    border:  2px solid black;
}
.뒤로{
    margin-top: 1px;
    margin-right: 5px;
    padding: 10px;
}
</style>