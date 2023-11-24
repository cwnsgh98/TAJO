import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useGroupStore = defineStore('group', () => {
    const groupList = ref([]);
    const memberList = ref([]);
    // 코스에 해당하는 전체 그룹 불러오기
    const getGroupList = async function (cid) {
      const courseid = Number.parseInt(cid);
      const response = await axios.get(`http://localhost:8080/api-group/Group/${courseid}`);
      groupList.value = response.data;
      return groupList.value;
    };

    
    // 그룹에 해당하는 전체 멤버 불러오기
    const getMemberList = async function (groupid) {
      
      const response = await axios.get(`http://localhost:8080/api-group/Group/${groupid}/members`);
      console.log(response.data)
      memberList.value = response.data; 
    };

    // 그룹에 멤버 등록하기
    const insertMember = async function(groupid) {
      console.log(groupid)

      const uid = JSON.parse(localStorage.getItem("loginUser")).userid;
      const groupInfo = {
        groupid : groupid,
        userid : uid,
      }
      await axios.post(`http://localhost:8080/api-group/Group/join`, groupInfo);
      await getMemberList(groupid);
    };

    // 그룹에서 탈퇴하기
    const deleteMember = async function(groupid) {
      const uid = JSON.parse(localStorage.getItem("loginUser")).userid;
      
      await axios.delete(`http://localhost:8080/api-group/Group/exit`, {
        data : {
          groupid : groupid,
          userid : uid,
        }
      });
      await getMemberList(groupid);
        
    };

    // 그룹 생성하기
    const makeGroup = async function(group) {
      await axios.post(`http://localhost:8080/api-group/Group`, group);
      await getGroupList(group.courseid);
    };

    return {groupList, memberList, getGroupList, getMemberList, insertMember, deleteMember, makeGroup,}
})