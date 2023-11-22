import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
export const useCourseStore = defineStore('info', () => {
    const couresstore = ref([]);
  
  
  return { couresstore }
})
