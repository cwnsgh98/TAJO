<template>
    <div>
        <div class="리뷰별점">
            리뷰별점
            <input type="number" v-model="star">

        </div>
        <div class="리뷰내용">
            리뷰내용
            <input type="text" v-model="content">
        </div>
        <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
            <button @click="writeReview">작성</button>
        </RouterLink>
        <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
            뒤로가기
        </RouterLink>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import { RouterLink } from 'vue-router';
import { useRoute } from 'vue-router';
import { useReviewStore } from '../../stores/review';
import { useCourseStore } from '../../stores/course';
const courseStore = useCourseStore();
const reviewStore = useReviewStore();
const star = ref(0);
const content = ref('');
const Route = useRoute();
const writeReview = async () => {
    try {
        const courseReview = {
            writer: localStorage.getItem("loginUser") ? localStorage.getItem("loginUser") : "익명",
            content: content.value,
            star: star.value,
            courseid: Route.params.courseid,
        };
        await reviewStore.insertReview(courseReview);
        await reviewStore.getReviewList(Route.params.courseid);
        for(const course of courseStore.courseList) {
            if(course.courseid == Route.params.courseid) {
                course.reviewCnt++;
            }
        }
    } catch (error) {
        console.error('Error inserting review:', error);
    }
};
</script>

<style scoped>
div{
    padding-top: 12px;
    padding-bottom: 40px;
}
</style>