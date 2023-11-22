<template>
    <div class="InfoMain">
        <div class="한마디">현재페이지 자전거길 목록</div>
        <div class="course-list">
            <div class="listbox" v-for="course in courseStore.courseList" :key="course.courseid">
                <div class="오른쪽">
                    <!--<img class="코스이미지" :src="`@/assets/${course.img}`">-->
                </div>
                <div class="왼쪽">
                    <span class="코스이름">{{ course.name }}</span>
                    <span class="별점">별점 : {{ course.starCnt }}</span>
                    <span class="리뷰">라이더리뷰 개수 : {{ course.reviewCnt }}</span>
                </div>
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
import { useCourseStore } from '../../stores/course';
const courseStore = useCourseStore();
// 가상의 데이터 배열 (실제 데이터로 교체해야 함)
const courses = [
    { name: '코스1', rating: 4.5, reviewCount: 10 },
    { name: '코스2', rating: 3.8, reviewCount: 8 },
    { name: '코스3', rating: 4.2, reviewCount: 12 },
    { name: '코스4', rating: 4.0, reviewCount: 15 },
    { name: '코스5', rating: 4.7, reviewCount: 20 },
    { name: '코스6', rating: 3.5, reviewCount: 5 },
    { name: '조담현', rating: 4.5, reviewCount: 10 },
    { name: '이재신', rating: 3.8, reviewCount: 8 },
    { name: '최준호', rating: 4.2, reviewCount: 12 },
    { name: '최은희', rating: 4.0, reviewCount: 15 },
    { name: '코스코코코5', rating: 4.7, reviewCount: 20 },
    { name: '스스스스', rating: 3.5, reviewCount: 5 },
    { name: '코스1', rating: 4.5, reviewCount: 10 },
    { name: '코스2', rating: 3.8, reviewCount: 8 },
    { name: '코스3', rating: 4.2, reviewCount: 12 },
    { name: '코스4', rating: 4.0, reviewCount: 15 },
    { name: '코스5', rating: 4.7, reviewCount: 20 },
    { name: '코스6', rating: 3.5, reviewCount: 5 },
    // ... 더 많은 데이터
];

const itemsPerPage = 6;
const currentPage = ref(1);

// 현재 페이지에 해당하는 데이터를 동적으로 계산
const paginatedCourses = computed(() => {
    const startIndex = (currentPage.value - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return courses.slice(startIndex, endIndex);
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
.버튼버튼{
    background-color: aliceblue;
    font-family: 'cookierun';
    padding: 3px;
}
.가운데{
    margin-left: 7px;
    margin-right: 7px;
}
.버튼들{
    margin-top: 14px;
}
.한마디 {
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
    min-height: 70px;
    border: 2px solid #000000;
    margin-top: 17px;
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