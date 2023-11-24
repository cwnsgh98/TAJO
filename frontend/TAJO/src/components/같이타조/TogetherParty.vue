<template>
    <div class="partymain">
      <div class="mainmain">
        <div class="head">
          <button class="뒤로" @click="$emit('closeToggle')">&lt;</button>
          <div class="partyheader">
            <span class="파티이름">파티 {{ groupid }}</span>
          </div>
        </div>
        <div class="파티원목록">
          <div class="파티원정보">
            <table>
              <thead>
                <tr>
                  <th style="width: 10%;">번호</th>
                  <th style="width: 30%;">닉네임</th>
                  <th style="width: 30%;">타조 등급</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(user, index) in userList " :key="user.userid">
                  <td style="width: 10%;">{{ index }}</td>
                  <td style="width: 30%;">{{ user.nickname }}</td>
                  <td style="width: 30%;"><img :src="user.srcImg"></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="버튼">
            <button class="참가" @click="join(groupid)">참가</button>
            <button @click="exit(groupid)">탈퇴</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import {ref, onMounted, watch} from 'vue'
import {useGroupStore} from '@/stores/group'
import axios from 'axios'
import eggImage from '@/assets/egg.png';
import ostrich1Image from '@/assets/ostrich1.png';
import ostrich2Image from '@/assets/ostrich2.png';
import ostrich3Image from '@/assets/ostrich3.png';
import ostrich4Image from '@/assets/ostrich4.png';
import ostrich5Image from '@/assets/ostrich5.png';
import ostrich6Image from '@/assets/ostrich6.png';
import ostrich7Image from '@/assets/ostrich7.png';
import ostrich8Image from '@/assets/ostrich8.png';
const props = defineProps({
  groupid:Number,
})
const groupStore = useGroupStore();
const userList = ref([])
const join = async function(groupid) {
  await groupStore.insertMember(groupid);
  await groupStore.getMemberList(groupid);
}

const exit = async function(groupid) {
  await groupStore.deleteMember(groupid);
  await groupStore.getMemberList(groupid);
}

const getOstrichImage = (grade) => {
  switch (grade) {
    case 'egg':
      return eggImage;
    case 'bronze':
      return ostrich1Image;
    case 'silver':
      return ostrich2Image;
    case 'gold':
      return ostrich3Image;
    case 'platinum':
      return ostrich4Image;
    case 'emerald':
      return ostrich5Image;
    case 'diamond':
      return ostrich6Image;
    case 'master':
      return ostrich7Image;
    case 'challenger':
      return ostrich8Image;
    default:
      return ostrich1Image; // 기본 값
  }
};
onMounted(async ()=>{
  watch(() => [groupStore.memberList], async ([memList]) => {
  userList.value = memList;
  
  for(const user of userList.value) {

        await axios.get(`http://localhost:8080/api-user/grade`, {
        params: { userid: user.userid },
      }).then((res)=>{
        user.grade=res.data;
      });
      user.srcImg = getOstrichImage(user.grade);
      console.log(user.srcImg)
      
      //console.log(user.grade)
        
    }
});
    userList.value = groupStore.memberList;

    
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
img {
    width: 47px;
    height: 50px;
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
  .boxbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 6px;
}
  </style>
  