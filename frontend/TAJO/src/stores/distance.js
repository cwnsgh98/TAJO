import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useDistanceStore = defineStore('distance', () => {
  const totalDist = ref(0);
  function setTotalDist(newValue) {
    totalDist.value = newValue
  }
  return { totalDist, setTotalDist}
})
