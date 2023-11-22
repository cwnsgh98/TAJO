package com.tajo.dao;

import java.util.HashMap;
import java.util.List;

import com.tajo.dto.Course;
import com.tajo.dto.CourseReview;

public interface CourseDao {

	List<Course> selectAll();

	Course selectOne(int courseid);

	void updateViewCnt(int courseid);

	List<CourseReview> selectAllCourseReview(int courseid);

	int insertReview(CourseReview review);

	void deleteReview(int reviewid);

	CourseReview selectCourseReview(int reviewid);

	int insertFavorite(HashMap<String, Object> map);

	void deleteFavorite(HashMap<String, Object> map);

	List<Course> selectAllFavorite(String userid);

	double selectStarAvg(int courseid);

}
