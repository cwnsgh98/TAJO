<template>
  <div class="_main">
    <RouterLink to="/Together">
      <button>뒤로가기</button>
    </RouterLink>

    <div class="mainbox">
      <div class="box-left">
        <div class="courseinfo">
          <img src="@/assets/ostrich5.png" />
          <span>유성온천코스 게시판</span>
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
                <th>go?</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in items" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.content }}</td>
                <td>{{ item.course }}</td>
                <td>{{ item.leader }}</td>
                <td>{{ item.members }}</td>
                <td>{{ item.date }}</td>
                <td>
                  <button @click="toggleDetail(item.id)">파티 입장</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="파티모집"></div>
      </div>

      <!-- <div class="box-right">
        <div class="지원">
          <span>지원한코스리트스</span>
          <div class="지원한코스들"></div>
        </div>
      </div> -->

      <div class="box-right" v-show='showDetail'>
        <TogetherDetailmore @close-toggle="closeToggle"/>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import TogetherDetailmore from "@/components/같이타조/TogetherDetailmore.vue";
const items = [
  { id: 1, content: "즐겁게 달릴분", course: "유성온천코스", leader: "쮸롱이", members: "1 / 4", date: "2023-01-01" },
  { id: 2, content: "건강해집시다", course: "유성온천코스", leader: "조담현", members: "1 / 4", date: "2023-02-01" },
  { id: 3, content: "싸피등원같이하실분", course: "유성온천코스", leader: "여진구", members: "1 / 4", date: "2023-03-01" },
  { id: 4, content: "출근하기싫다", course: "유성온천코스", leader: "정민우", members: "1 / 4", date: "2023-04-01" },
];
const closeToggle = function() {
  showDetail.value = false;
}
const showDetail = ref(false);
const selectedItemId = ref(null);

const toggleDetail = (itemId) => {
  selectedItemId.value = itemId;
  showDetail.value = !showDetail.value;
};
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