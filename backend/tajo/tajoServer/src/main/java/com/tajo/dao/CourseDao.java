package com.tajo.dao;

import java.util.HashMap;
import java.util.List;

import com.tajo.dto.Course;
import com.tajo.dto.CourseReview;
import com.tajo.dto.SearchCondition;

public interface CourseDao {

	List<Course> selectAll(SearchCondition condition);

	Course selectOne(int courseid);

	void updateViewCnt(int courseid);

	List<CourseReview> selectAllCourseReview(int courseid);

	int insertReview(CourseReview review);

	void deleteReview(int reviewid);

	CourseReview selectCourseReview(int reviewid);

	int insertFavorite(HashMap<String, Object> map);

	void deleteFavorite(HashMap<String, Object> map);

	List<Course> selectAllFavorite(int userid);

}
