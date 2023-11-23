<template>
    <div class="InfoMain">
        <div class="한마디">현재페이지 자전거길 목록</div>
        <div class="course-list">
            <div class="listbox" v-for="course in paginatedCourses" :key="course.courseid">
                <RouterLink :to="`/Wherego/${course.courseid}/Detail`">
                    <div class="박스정렬">
                    <div class="오른쪽">
                         <img class="코스이미지" :src="`/src/assets/${course.img}`">
                         {{console.log(course.img)}}
                    </div>
                    <div class="왼쪽">
                        <span class="코스이름 stroked-text">{{ course.name }}</span>
                        <span class="별점 ">별점 : {{ course.starCnt }}</span>
                        <span class="리뷰 ">라이더리뷰 개수 : {{ course.reviewCnt }}</span>
                    </div>
                </div>
                </RouterLink>
            </div>
        </div>
        <div class="버튼들">
            <button class="버튼버튼" @click="prevPage" :disabled="currentPage === 1">이전 페이지</button>
            <span class="가운데">{{ currentPage }} / {{ totalPages }}</span>
            <button class="버튼버튼" @click="nextPage" :disabled="currentPage === totalPages">다음 페이지</button>
        </div>
    </div>
</template>

<script setup>


import { ref, computed ,onMounted, watch} from 'vue';
import { RouterLink } from 'vue-router';
import { useCourseStore } from '../../stores/course';

const courseStore = useCourseStore();
const coList = ref([]);
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

onMounted(async() => {
    console.log(courseStore.courseList)
    try {
        coList.value = courseStore.courseList;
    } catch (error){
        console.log(error);
    }

    // watch( () => [courseStore.courseList], async  ([newList]) => {
    //     coList.value = newList;
    //     console.log(courseStore.courseList)
    //     // course.value = await courseStore.getCourse(Route.params.courseid);
    // });

});
</script>
  

<style scoped>
/* .slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.25);
  }
  100% {
    transform: scale(1);
  }
} */

.코스이미지{
    min-width: 160px;
    max-width: 160px;
    min-height: 100px;
    max-height: 100px;
    border: 2px solid #242424;
}
.박스정렬{
    display: flex;
}

.별점, .리뷰{
color: #4c4c4c;
}
.stroked-text {
    -webkit-text-stroke: 0.3px #000000;
    /* Safari 및 Chrome 브라우저에서 사용 가능 */
    color: white;
    /* 텍스트 색상 지정 */
    font-size: 17px;
    font-family: 'cookierun';
}
.코스이름{
    font-size: 25px;
    color: #163ec3
}
a{
    text-decoration: none;
}
.왼쪽 span{
    margin-top: 6px;
    margin-bottom: 6px;
}
.버튼버튼 {
    background-color: rgb(244, 250, 255);
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
    -webkit-text-stroke: 1px rgb(177, 218, 255);
    font-size: 23px;
    margin-top: 5px;
    margin-bottom: 5px;
    color: #00268d
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
    background-color: aliceblue;
    display: flex;
    align-items: center;
    min-width: 200px;
    min-height: 140px;
    border: 2px solid #b8b8b8;
    margin-top: 20px;
    border-radius: 13px;
}

.오른쪽 {
    display: flex;
    justify-content: center;
    align-items: center;
    min-width: 170px;
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
    /* padding: 20px; */
    /* border: 4px solid #000000; */
}</style>