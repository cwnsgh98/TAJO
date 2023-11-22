import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
export const useRecordStore = defineStore('record', () => {
  const recordList= ref(null);
  const dayCount = ref(0);
  const grade = ref("");
  const day = ref(null);
  const thisDayDist = ref(0);

  function getRecord(userid) {
    axios.get(`http://localhost:8080/api-user/record`, {params:{
      userid:userid
    }}).then((res) => {
    
    const currentMonth = (new Date()).getMonth() + 1; // 현재 월을 구합니다.
    recordList.value = filterRecordsByCurrentMonth(res.data, currentMonth);   
    dayCount.value = countUniqueDates(recordList.value);
    console.log(day.value)
  
    });


  }
  function filterRecordsByCurrentMonth(records, currentMonth) {
    return records.filter(record => {
      // record.distance를 파싱하여 월을 추출하고, 현재 월과 비교합니다.
      const recordMonth = new Date(record.ridedate).getMonth() + 1;    
      return recordMonth === currentMonth;
    });
  }

  function plusThisDayDist(newDay) {
    for(const record of recordList.value) {
     if(new Date(record.ridedate).getDate() === newDay)
      thisDayDist.value += record.distance;
    }
  }

  // function dayget(record){
  //   return records.filter(record => {
  //     // record.distance를 파싱하여 월을 추출하고, 현재 월과 비교합니다.
  //     const recordDay = new Date(record.ridedate).getDate();    
  //     return recordDay === currentDay;
  //   });
  // }
  function countUniqueDates(records) {
    const uniqueDates = new Set();
  
    records.forEach(record => {
      const recordDate = new Date(record.ridedate).getDate()  ;
      uniqueDates.add(recordDate);
    
    });
    day.value=uniqueDates;
    return uniqueDates.size;
  }



  function setGrade(newGrade) {
    grade.value = newGrade;
  }
  
  
  return { recordList, getRecord,dayCount, grade, setGrade ,day ,thisDayDist, plusThisDayDist}
}, {persist : true})
