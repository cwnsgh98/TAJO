import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useCourseStore = defineStore('course', () => {
    const courseList = ref([]);
    const course = ref({});

    // 코스 상세를 위한 메서드 
    const getCourse = async function (id) {
      try {
        const response = await axios.get(`http://localhost:8080/api-course/Course/${id}`);
        return response.data; // 추가: 메서드가 완료되면 결과값 반환
      } catch (error) {
        console.error(error);
      }
    };
    //+ 마커가 변경될때마다 코스리스트 변경
    const getMarkerCourse = async function (id) {
      try {
        const response = await axios.get(`http://localhost:8080/api-course/Course/${id}`);
        courseList.value.push(response.data);
        return response.data; // 추가: 메서드가 완료되면 결과값 반환
      } catch (error) {
        console.error(error);
      }
    };
    
    const clearCourseList = function() {
      courseList.value = [];
    }

    return { courseList, getCourse, clearCourseList, course, getMarkerCourse }
})