package com.tajo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tajo.dto.Course;
import com.tajo.dto.CourseReview;
import com.tajo.dto.SearchCondition;
import com.tajo.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-course")
@Api(tags = "코스 컨트롤러")
public class CourseRestController {
	@Autowired
	private CourseService CourseService;

	// 1. 전체 코스(검색조건 있을 수도 있고 없을 수도 있다.)
	@GetMapping("/Course")
	@ApiOperation(value = "코스 조회", notes = "전체 코스 리스트를 가져온다.")
	public ResponseEntity<?> list(SearchCondition condition) {
		List<Course> list = CourseService.getCourseList(condition); // 전체 조회
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}

	// 2. 상세보기
	@GetMapping("/Course/{id}")
	@ApiOperation(value = "코스 상세", response = Course.class)
	public ResponseEntity<Course> detail(@PathVariable int courseid) {
		Course course = CourseService.getCourse(courseid);
		// 조회수 증가
		CourseService.updateViewCnt(courseid);
		
		// 별점 평균 가져오기
//		course.setStarAvg(CourseService.getStarAvg());
		// 리뷰 개수 가져오기
		course.setReviewCnt(CourseService.getCourseReviewList(courseid).size());
		// 정석이라면 게시글 제목을 클릭해서 상세보기로 들어갈 거니까 여기서 매무리 해도 된다.
		// 꼬옥 주소창을 통해 접근하려고 하는 악의무리가 있기 때문에 만약 없는 값을 보냈을때... 처리를 해주어라. (해볼것)
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	// 3. 코스의 리뷰 조회
	@GetMapping("/Course/{id}/review")
	@ApiOperation(value = "코스의 전체 리뷰 조회")
	public ResponseEntity<?> reviewList(@PathVariable int courseid) {
		List<CourseReview> list = CourseService.getCourseReviewList(courseid);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<CourseReview>>(list, HttpStatus.OK);
	}

	// 4. 코스의 리뷰 등록
	@PostMapping("/Course/{id}/review")
	@ApiOperation(value = "코스에 리뷰 등록")
	public ResponseEntity<Integer> write(@RequestBody CourseReview review, @PathVariable int id) {
		review.setCourseid(id);
		int result = CourseService.writeCourseReview(review);
		// ID는 어차피 중복이 안되서 무조건 게시글이 등록이 된다.
		// 문제 발생해서 게시글이 등록이 안될 경우도 있다더라....
		// I / U / D 테이블의 행의 변환 개수를 반환해 주더라 이걸 이용해서 처리를 해볼 수도 있겠다....
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	// 5. 코스의 리뷰 삭제
	@DeleteMapping("/Course/review/{id}")
	@ApiOperation(value = "코스 리뷰 삭제")
	public ResponseEntity<Void> deleteReview(@PathVariable int reviewid) {
		CourseService.removeCourseReview(reviewid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 6. 코스의 리뷰 상세
	@GetMapping("/Course/review/{id}")
	@ApiOperation(value = "코스 리뷰 상세")
	public ResponseEntity<?> reviewDetail(@PathVariable int reviewid) {
		CourseReview review = CourseService.detailCourseReview(reviewid);
		return new ResponseEntity<CourseReview>(review, HttpStatus.OK);
	}
	
	// 7. 코스 찜하기
	@PostMapping("/Course/{id}/like")
	@ApiOperation(value = "코스 찜하기")
	public ResponseEntity<Void> favorite(String userid, @PathVariable int id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("courseid", id);
		CourseService.favoriteCourse(map);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 8. 코스 찜 해제하기
	@DeleteMapping("/Course/{id}/unlike")
	@ApiOperation(value = "코스 찜해제")
	public ResponseEntity<Void> unFavorite(String userid, @PathVariable int id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("courseid", id);
		CourseService.unFavoriteCourse(map);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// 9. 찜한 코스 목록 불러오기
	@GetMapping("/Course/like")
	@ApiOperation(value = "코스 찜삭제")
	public ResponseEntity<?> getFavorite(int userid) {
		List<Course> list = CourseService.getFavorite(userid);
		return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
	}
}
