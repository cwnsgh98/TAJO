import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useGroupStore = defineStore('group', () => {
    const groupList = ref([]);
    const memberList = ref([]);

    // 코스에 해당하는 전체 그룹 불러오기
    const getGroupList = async function (courseid) {
      
    };

    
    // 그룹에 해당하는 전체 멤버 불러오기
    const getMemberList = async function (groupid) {
        
      };

    // 그룹에 멤버 등록하기
    const insertMember = async function(groupid) {
    };

    // 그룹에서 탈퇴하기
    const deleteMember = async function(groupid) {
        
    };

    // 그룹 생성하기
    const makeGroup = function(courseid) {
     
    };

    return {groupList, memberList, getGroupList, getMemberList, insertMember, deleteMember, makeGroup,}
})