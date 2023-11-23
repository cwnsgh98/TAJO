import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useReviewStore = defineStore('Review', () => {
    const reviewList = ref([]);


    // 전체 리뷰를 위한 메서드

    const getReviewList = async function (courseid) {
      try {
        const response = await axios.get(`http://localhost:8080/api-course/Course/${courseid}/review`);
        reviewList.value = response.data;
        return response.data; // 추가: 메서드가 완료되면 결과값 반환
      } catch (error) {
        console.error(error);
      }
    };
    
    const insertReview = async function(courseReview) {
        try {
            await axios.post(`http://localhost:8080/api-course/Course/${courseReview.courseid}/review`, courseReview);
          } catch (error) {
            console.error(error);
          }
    };

    const deleteReview = async function(reviewid) {
        try {
            await axios.delete(`http://localhost:8080/api-course/Course/review/${reviewid}`);
          } catch (error) {
            console.error(error);
          }
    };

    const clearReviewList = function() {
      reviewList.value = [];
    };

    return { reviewList, getReviewList, clearReviewList, insertReview, deleteReview,}
})