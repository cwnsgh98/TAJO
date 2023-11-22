package com.tajo.service;

import java.util.HashMap;
import java.util.List;

import com.tajo.dto.Course;
import com.tajo.dto.CourseReview;

public interface CourseService {

	List<Course> getCourseList();

	Course getCourse(int courseid);

	void updateViewCnt(int courseid);

	List<CourseReview> getCourseReviewList(int courseid);

	int writeCourseReview(CourseReview review);

	void removeCourseReview(int reviewid);

	CourseReview detailCourseReview(int reviewid);

	int favoriteCourse(HashMap<String, Object> map);

	void unFavoriteCourse(HashMap<String, Object> map);

	List<Course> getFavorite(String userid);

	double getStarAvg(int courseid);

}
