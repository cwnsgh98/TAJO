<template>
  <div class="_main">
    <RouterLink to="/Together">
      <button>뒤로가기</button>
    </RouterLink>

    <div class="mainbox">
      <div class="box-left">
        <div class="courseinfo">
          <img src="@/assets/ostrich5.png" />
          <span>{{courseName}} 게시판</span>
        </div>
        <div class="tablebox">
          <table>
            <thead>
              <tr>
                <th>번호</th>
                <th>내용</th>
                <th>코스</th>
                <th>파티장</th>
                <th>인원</th>
                <th>일시</th>
                <th>모집</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="group in groupList" :key="group.groupid">
                <td>{{ group.groupid }}</td>
                <td>{{ group.content }}</td>
                <td>{{ courseName }}</td>
                <td>{{ group.writer }}</td>
                <td>{{ group.people }} / {{ group.limit }}</td>
                <td>{{ group.date }}</td>
                <td v-if="group.people!=group.limit"> <!--여기서 그룹에 참여한 인원 수와 limit을 비교하여 마감/입장 나누고 싶다..-->
                  <button @click="toggleDetail(group.groupid)">파티 입장</button>
                </td>
                <td v-else>
                  모집 마감
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="파티모집"></div>
      </div>

      <div class="box-right" v-show='showDetail'>
        <TogetherDetailmore @close-toggle="closeToggle"/>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import TogetherDetailmore from "@/components/같이타조/TogetherDetailmore.vue";
import { useGroupStore } from "../../stores/group";
import {useCourseStore} from '@/stores/course';
import { useRoute } from "vue-router";
const groupStore = useGroupStore();
const courseStore = useCourseStore();
const groupList = ref([]);
const courseName = ref('');
const route = useRoute();
const closeToggle = function() {
  showDetail.value = false;
}
const showDetail = ref(false);

const toggleDetail = async (groupid) => {
  await groupStore.getMemberList(groupid); 
  showDetail.value = !showDetail.value;
};

onMounted(async() => {
    try {
        groupList.value = await groupStore.getGroupList(route.params.courseid);
        courseName.value = await courseStore.getCourse(route.params.courseid).name
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


.디테일 {
background-color: white; /* 배경색을 흰색 또는 다른 적절한 색상으로 설정하세요 */
}

.courseinfo {
  display: flex;
  align-items: center;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

.box-right {
  width: 17%;
  height: 100%;
  background-color: white;
  border-left: solid 2px #7f7f7f;
}

.box-left {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 70%;
}

img {
  width: 210px;
  height: 160px;
}

._main {
  width: 100%;
  display: flex;
  justify-content: center;
}

.mainbox {
  display: flex;
  justify-content: center;
  width: 95%;
  height: 750px;
}
</style>