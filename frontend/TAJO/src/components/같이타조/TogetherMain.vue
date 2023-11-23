<template>
    <div class="Togemain">
        <!-- 모든코스가 일단 뜨는곳입니다.
        디브들은 다 라우터링크로 보낼건데 어디로보내냐 ?
                 <RouterLink to="/Togetherdetail">
                    <img class="goregist" src="@/assets/ostrich6.png">
                </RouterLink> -->

        <div class="course">
            <span class="coursetext">코스별 모집 게시판</span>
            <div class="course-list" >
                <div class="coursebox" v-for="course in coList" :key="course.courseid"  >
                    <img :src=course.img>
                    <div class="box-bottom">
                        <span>{{ course.name }}</span>
                        <RouterLink :to="`/Together/${course.courseid}/Detail`">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <!-- <div class="coursebox">
                    <img src="@/assets/ostrich2.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich3.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich4.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich5.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich6.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich7.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div>
                <div class="coursebox">
                    <img src="@/assets/ostrich8.png">
                    <div class="box-bottom">
                        <span>코스이름</span>
                        <RouterLink to="/Togetherdetail">
                            <button>파티목록</button>
                        </RouterLink>
                    </div>
                </div> -->
            </div>
        </div>




    </div>
</template>

<script setup>
import { useCourseStore } from '../../stores/course';
import {ref, onMounted} from 'vue'
const courseStore = useCourseStore();
const coList = ref([]);

onMounted(async() => {
    try {
        
        coList.value = await courseStore.getAllCourse();
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

<style  scoped>
.coursetext {
    margin-bottom: 30px;
    font-size: 25px;
}

.Togemain {
    margin-top: 35px;
    width: 100%;
    height: 640px;
    display: flex;
    justify-content: center;
}

.box-bottom {
    width: 100%;
    display: flex;
    justify-content: space-evenly;
}

.coursebox {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.course {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 1000px;
    border: solid 2px #626161;
    padding: 50px 100px 70px 100px;
}

.course-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
}

img {
    width: 210px;
    height: 160px;
}</style>