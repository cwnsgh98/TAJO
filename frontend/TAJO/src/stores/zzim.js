import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useZzimStore = defineStore('zzim', () => {
    const zzimList = ref([]);


    // 찜 리스트 위한 메서드 
    const getZzimList = async function () {
        const uid = JSON.parse(localStorage.getItem("loginUser")).userid;

      try {
       
        const response = await axios.get(`http://localhost:8080/api-course/Course/like`, {params : {userid : uid}});
        zzimList.value = response.data;

      } catch (error) {
        console.error(error);
      }
    };

    const insertZzim = async function(id) {
        const uid = JSON.parse(localStorage.getItem("loginUser")).userid;
        
        const favorite = {
            userid : uid,
            courseid : Number.parseInt(id)
        }
        await axios.post(`http://localhost:8080/api-course/Course/like`, favorite);
        await getZzimList();
    }

    const deleteZzim = async function(id) {
        const uid = JSON.parse(localStorage.getItem("loginUser")).userid;

        
        await axios.delete(`http://localhost:8080/api-course/Course/unlike`, {
            data :{
            userid : uid,
            courseid : Number.parseInt(id)
        }
    });
        await getZzimList();
    }
   
   

    return { zzimList, getZzimList, insertZzim, deleteZzim, }
})