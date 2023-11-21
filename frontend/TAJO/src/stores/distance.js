import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useDistanceStore = defineStore('distance', () => {
  const totalDist = ref(0);
  const distRank = ref(0);

  function setTotalDist(newValue) {
    totalDist.value = newValue
  }

  const getDistRank = function (userid) {
    axios.get(`http://localhost:8080/api-user/average`, {
        params: { userid : userid}
      }).then((res) => {
        distRank.value = res.data;
      });
  };
  

  return { totalDist, setTotalDist, distRank, getDistRank };
}, {persist : true})
