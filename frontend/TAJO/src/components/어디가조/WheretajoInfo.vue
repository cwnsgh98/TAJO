<template>
    <div class="InfoMain">
        <div class="한마디">현재페이지 자전거길 목록</div>
        <div class="course-list">
            <div class="listbox" v-for="course in paginatedCourses" :key="course.courseid">
                <RouterLink :to="`/Wherego/${course.courseid}/Detail`">
                    <div class="오른쪽">
                         <!--<img class="코스이미지" :src="`@/assets/${course.img}`">-->
                    </div>
                    <div class="왼쪽">
                        <span class="코스이름">{{ course.name }}</span>
                        <span class="별점">별점 : {{ course.starCnt }}</span>
                        <span class="리뷰">라이더리뷰 개수 : {{ course.reviewCnt }}</span>
                    </div>
                </RouterLink>
            </div>
        </div>
        <div class="버튼들">
            <!-- 페이지네이션 버튼 -->
            <button class="버튼버튼" @click="prevPage" :disabled="currentPage === 1">이전 페이지</button>
            <span class="가운데">{{ currentPage }} / {{ totalPages }}</span>
            <button class="버튼버튼" @click="nextPage" :disabled="currentPage === totalPages">다음 페이지</button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { RouterLink } from 'vue-router';
import { useCourseStore } from '../../stores/course';
const courseStore = useCourseStore();

const itemsPerPage = 3;
const currentPage = ref(1);

// 현재 페이지에 해당하는 데이터를 동적으로 계산
const paginatedCourses = computed(() => {
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return courseStore.courseList.slice(startIndex, endIndex);
});

// 전체 페이지 수 계산
const totalPages = computed(() => Math.ceil(courseStore.courseList.length / itemsPerPage));

// 다음 페이지로 이동하는 함수
const nextPage = () => {
    if (currentPage.value < totalPages.value) {
        currentPage.value += 1;
    }
};

// 이전 페이지로 이동하는 함수
const prevPage = () => {
    if (currentPage.value > 1) {
        currentPage.value -= 1;
    }
};
</script>
  

<style scoped>
.버튼버튼 {
    background-color: aliceblue;
    font-family: 'cookierun';
    padding: 3px;
}

.가운데 {
    margin-left: 7px;
    margin-right: 7px;
}

.버튼들 {
    margin-top: 22px;
    margin-left: 10px;
}

.한마디 {
    font-size: 18px;
    margin-top: 5px;
    margin-bottom: 5px;
}

.course-list {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

img {
    margin-top: 2px;
    height: 55px;
    width: 55px;
    margin-left: 10px;
    margin-right: 10px;
}

.listbox {
    display: flex;
    align-items: center;
    min-width: 200px;
    min-height: 140px;
    border: 2px solid #000000;
    margin-top: 17px;
    border-radius: 13px;
}

.오른쪽 {
    display: flex;
    justify-content: center;
    align-items: center;
}

.왼쪽 {
    margin-left: 12px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
}

.InfoMain {
    margin-top: 20px;
    padding: 20px;
    border: 2px solid #000000;
}</style>