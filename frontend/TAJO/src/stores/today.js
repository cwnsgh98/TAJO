import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useTodayStore = defineStore('today', () => {
  const todayDist = ref(0);
  function plusTodayDist(newValue) {
    todayDist.value += newValue
  }

  const todayCal = ref(0);
  function plusTodayCal(newValue) {
    todayCal.value += newValue
  }

  const todayTime = ref(0);
  function plusTodayTime(newValue) {
    todayTime.value += newValue
  }

  function setDefault() {
    todayDist.value = 0;
    todayCal.value = 0;
    todayTime.value = 0;
  }
  
  return { setDefault, todayDist, plusTodayDist, todayCal, plusTodayCal, todayTime, plusTodayTime}
})
