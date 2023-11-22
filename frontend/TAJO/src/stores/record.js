import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
export const useRecordStore = defineStore('record', () => {
  const recordList= ref(null);
  const dayCount = ref(0);
  const grade = ref("");
  function getRecord(userid) {
    axios.get(`http://localhost:8080/api-user/record`, {params:{
      userid:userid
    }}).then((res) => {
    
    const currentMonth = (new Date()).getMonth() + 1; // 현재 월을 구합니다.
    
    recordList.value = filterRecordsByCurrentMonth(res.data, currentMonth);   
    dayCount.value = countUniqueDates(recordList.value);
  
    });


  }
  function filterRecordsByCurrentMonth(records, currentMonth) {
    return records.filter(record => {
      // record.distance를 파싱하여 월을 추출하고, 현재 월과 비교합니다.
      const recordMonth = new Date(record.ridedate).getMonth() + 1;    
      return recordMonth === currentMonth;
    });
  }

  function countUniqueDates(records) {
    const uniqueDates = new Set();
  
    records.forEach(record => {
      const recordDate = new Date(record.ridedate).toDateString();
      uniqueDates.add(recordDate);
    });
  
    return uniqueDates.size;
  }

  function setGrade(newGrade) {
    grade.value = newGrade;
  }
  
  
  return { recordList, getRecord,dayCount, grade, setGrade }
}, {persist : true})
