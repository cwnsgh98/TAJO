<template>
    <div class="Togemain">
        <div class="course">
            <span class="coursetext">코스별 모집 게시판</span>
            <div class="course-list">
                <div class="coursebox" v-for="course in coList" :key="course.courseid">
                    <RouterLink class="중앙" :to="`/Together/${course.courseid}/Detail`">
                        <img :src="`src/assets/${course.img}`">
                        <div class="box-bottom">
                            <span class="stroked-text">{{ course.name }}</span>
                        </div>
                    </RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useCourseStore } from '../../stores/course';
import { ref, onMounted } from 'vue'
const courseStore = useCourseStore();
const coList = ref([]);

onMounted(async () => {
    try {

        coList.value = await courseStore.getAllCourse();
    } catch (error) {
        console.log(error);
    }

    // watch( () => [courseStore.courseList], async  ([newList]) => {
    //     coList.value = newList;
    //     console.log(courseStore.courseList)
    //     // course.value = await courseStore.getCourse(Route.params.courseid);
    // });

});

</script>

<style  scoped>
.stroked-text {
    -webkit-text-stroke: 0.9px #a1f44f;
    /* Safari 및 Chrome 브라우저에서 사용 가능 */
    color: #179404;
    /* 텍스트 색상 지정 */
    font-size: 20px;
    font-family: 'cookierun';
}
.coursetext {
    margin-bottom: 30px;
    font-size: 30px;
    color: #137304;
}
.중앙{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.Togemain {
    margin-top: 35px;
    width: 100%;
    height: 640px;
    display: flex;
    justify-content: center;
}

a {
    text-decoration: none;
}
.box-bottom {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
}

button {
    margin-top: 20px;
}

.coursebox {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    margin-right: 12px;
    margin-left: 12px;
    border-radius: 13px;
    border: 2px solid rgb(207, 207, 207);
    transition: transform 0.3s ease-in-out;
}

.coursebox:hover {
    transform: scale(1.1);
    border: 2px solid rgb(98, 98, 98);
    background-color: rgb(255, 246, 213)
}
.course {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    width: 1500px;
    /* border: solid 1px #626161; */
    /* padding: 50px 100px 70px 100px; */
}

.course-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
}

img {
    width: 210px;
    height: 300px;
    border: 2px solid black;
}</style>