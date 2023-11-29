package com.tajo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajo.dao.CourseDao;
import com.tajo.dto.Course;
import com.tajo.dto.CourseReview;
import com.tajo.dto.Favorite;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseDao courseDao;

	@Override
	public List<Course> getCourseList() {
		return courseDao.selectAll();
	}

	@Override
	public Course getCourse(int courseid) {
		return courseDao.selectOne(courseid);
	}

	@Override
	public void updateViewCnt(int courseid) {
		courseDao.updateViewCnt(courseid);
	}

	@Override
	public List<CourseReview> getCourseReviewList(int courseid) {
		return courseDao.selectAllCourseReview(courseid);
	}

	@Override
	public int writeCourseReview(CourseReview review) {
		return courseDao.insertReview(review);
	}

	@Override
	public void removeCourseReview(int reviewid) {
		courseDao.deleteReview(reviewid);
		
	}

	@Override
	public CourseReview detailCourseReview(int reviewid) {
		
		return courseDao.selectCourseReview(reviewid);
	}

	@Override
	public int favoriteCourse(Favorite favorite) {
		return courseDao.insertFavorite(favorite);
	}

	@Override
	public void unFavoriteCourse(Favorite favorite) {
		courseDao.deleteFavorite(favorite);
	}

	@Override
	public List<Course> getFavorite(String userid) {
		return courseDao.selectAllFavorite(userid);
	}

	@Override
	public double getStarAvg(int courseid) {
		return courseDao.selectStarAvg(courseid);
	}

	

}
