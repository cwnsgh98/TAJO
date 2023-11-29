<template>
  <div class="top">
    <div class="main">
      <span class="파티만들기">파티만들기</span>
      <div class="파티">
        <span class="파티명">파티제목 : </span>
        <input class="내용담는곳" type="text" v-model="content" />
      </div>
      <div class="인원정하기">
        <span>참여 인원을 선택해주세요 : </span>
        <select v-model="selectedParticipants">
          <option v-for="num in participantsRange" :key="num">{{ num }}</option>
        </select>
      </div>
      <div class="날짜정하기">
        <span class="날짜">날짜를 선택하세요 </span>
        <datepicker v-model="selectedDate" format="yyyy-MM-dd"/>
      </div>
      <div class="시작시간">
        <span>시작 시간을 선택해주세요 : </span>
        <select v-model="selectedSetTime">
          <option v-for="num in TimeRange" :key="num">{{ num }}</option>
        </select>
      </div>
      <div class="버튼">
      <button @click="handleCreate">만들기</button>
      <button class="취소" @click="$emit('closeToggle2')">취소</button>
    </div>
    </div>
  </div>
</template>

<script setup>
import Datepicker from 'vue3-datepicker';
import { useGroupStore } from '../../stores/group';
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';

const route = useRoute();
const router = useRouter();
const groupStore = useGroupStore();
const content = ref('');
const participantsRange = Array.from({ length: 10 }, (_, i) => i + 1);
const TimeRange = Array.from({ length: 24 }, (_, i) => i + 1);
const selectedParticipants = ref(1);
const selectedSetTime = ref('');
const selectedDate = ref(null);
const date = ref('');
const handleCreate = async () => {
  // 선택한 날짜가 있는지 확인
  if (selectedDate.value&&selectedSetTime.value&&selectedParticipants.value&&content.value) {
    // Date 객체를 사용하여 원하는 형식으로 문자열로 변환
    const dateString = selectedDate.value.toLocaleDateString('ko-KR', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    });

    date.value= (dateString + ' ' + selectedSetTime.value + '시');
    const group = {
      courseid: route.params.courseid,
      content : content.value,
      writer : localStorage.getItem('loginUser') ? JSON.parse(localStorage.getItem('loginUser')).nickname : '익명' ,
      limit : selectedParticipants.value,
      date : date.value,
    }

    await groupStore.makeGroup(group);
    //지금 전체 그룹 리스트 받아온다음에 거기서 그룹아이다가 가장높은놈이 방금 만든놈이니까 걔를 전체리스트에서 정렬해서 찾아서
    // 걔랑 지금 로그인한 아이디로 그룹에 넣는다.
    const getGroupList = await groupStore.getGroupList(route.params.courseid);
    const gid = getGroupList[0].groupid;
    groupStore.insertMember(gid);
    router.go(`/Toteger/${route.params.courseid}/Detail`)
    // console.log(selectedFinishTime.value)
  } else {
    aalert('모든 항목을 입력하세요.');
  }
};
</script>

<style scoped>
.파티만들기{
  font-size: 30px;
  margin-bottom: 40px;
}
span{
  font-size: 17px;
}
.취소{
  margin-left: 20px;
}
.버튼{
  margin-top: 15px;
  display: flex;
  justify-content: end;
}
.날짜{
  margin-right: 10px;
}
.파티, .인원정하기 ,.날짜정하기 ,.시작시간{
margin-bottom: 20px;
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
.날짜정하기{
  display: flex;
  flex-direction: column;
}
.main{
  margin: 30px;
  margin-top: 60px;
  width: 300px;
  padding: 40px;
  display: flex;
  flex-direction: column;
  border: 3px solid black;
  background-color: rgb(223, 255, 143);
}

</style>
  