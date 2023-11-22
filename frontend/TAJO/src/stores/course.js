import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
export const useCourseStore = defineStore('course', () => {
    const courseList = ref([]);
    const course = ref(null);

    // 코스 상세를 위한 메서드 + 마커가 변경될때마다 코스리스트 변경
    function getCourse(courseid) {
      const courseResponse = axios.get(`http://localhost:8080/api-course/${courseid}`).data
      course.value = courseResponse;
      courseList.value.push(courseResponse);
    }

    function clearCourseList() {
      courseList.value = [];
    }

  
  return { courseList, getCourse, clearCourseList }
})
