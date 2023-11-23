<template>
    <div class="partymain">
      <div class="mainmain">
        <div class="head">
          <button class="뒤로" @click="$emit('closeToggle')">&lt;</button>
          <div class="partyheader">
            <span class="파티이름">이거길면어떨게되능ㄴ더?</span>
          </div>
        </div>
        <div class="파티원목록">
          <div class="파티원정보">
            <table>
              <thead>
                <tr>
                  <th style="width: 10%;">번호</th>
                  <th style="width: 30%;">닉네임</th>
                  <th style="width: 30%;">타조</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(user, index) in userList " :key="user.userid">
                  <td style="width: 10%;">{{ index }}</td>
                  <td style="width: 30%;">{{ user.nickname }}</td>
                  <td style="width: 30%;">{{ user.grade }}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="버튼">
            <button class="참가">참가</button>
            <button>탈퇴</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import {ref, onMounted} from 'vue'
import {useGroupStore} from '@/stores/group'
import axios from 'axios'
const groupStore = useGroupStore();
const userList = ref([])
onMounted(async ()=>{
    userList.value = groupStore.memberList;
    for(const user in userList.value) {
        const gradeResponse = await axios.get(`http://localhost:8080/api-user/grade`, {
        params: { userid: user.userid },
      });
        user.grade = gradeResponse.data;
    }
})
  </script>
  
  <style scoped>
    .뒤로 {
    background-color: rgb(218, 238, 255);
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
.참가{
    margin-right: 20px;
}
.파티이름 {
font-size: 18px;
    margin-left: 10px;
    /* border: 2px solid black; */
    background-color: rgb(223, 255, 143);
}
tr th{
    color: rgb(83, 83, 83);
}
thead{
    margin-bottom: 10px;
}
.partymain{
    margin: 26px;
    width: 380px;
    margin-top: 50px;
    border: 3px solid black;
    background-color: rgb(254, 255, 225);
  }
  .파티원목록 {
    /* border: 2px solid green; */
    min-width: 320px;
    max-width: 320px;
  }
  .mainmain {
    padding: 30px;
  }
  
  th {
    width: 30px;
   
    font-size: 16px;
    /* border: 2px solid red; */
  }
  
  td {
    margin: 20px;
    max-height: 20px;
    min-height: 20px;
    padding: 10px;
    font-size: 14px;
    border-radius: 20px;
    background-color: rgba(226, 255, 250, 0.777);
    /* border: 2px solid blue; */
  }
  
  .head {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }
  .버튼 {
    margin-right: 30px;
    margin-top: 24px;
    display: flex;
    justify-content: end;
  }
  
  tr td {
    text-align: center;
  }
  </style>
  