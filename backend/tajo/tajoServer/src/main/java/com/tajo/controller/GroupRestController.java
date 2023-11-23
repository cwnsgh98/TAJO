package com.tajo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tajo.dto.Group;
import com.tajo.dto.GroupInfo;
import com.tajo.dto.User;
import com.tajo.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-group")
@Api(tags="그룹 컨트롤러")
public class GroupRestController {


		@Autowired
		private GroupService GroupService;


		//1. 코스 번호에 해당하는 그룹 목록
		@GetMapping("/Group/{courseid}")
		@ApiOperation(value="그룹 조회", notes="전체 그룹 리스트를 가져온다.")
		public ResponseEntity<?> list(@PathVariable int courseid){
			List<Group> list = GroupService.getGroupList(courseid); //전체 조회
			if(list == null || list.size() == 0)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Group>>(list, HttpStatus.OK);
		}
		
		@PostMapping("/Group")
		@ApiOperation(value="그룹 생성", response = Group.class)
		public ResponseEntity<Integer> makeGroup(@RequestBody Group group) {
			int result = GroupService.makeGroup(group);
			
			//result 가 0이면 등록 x
			//result 가 1이면 등록 o
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		
		@PostMapping("/Group/join") 	
		@ApiOperation(value="그룹 참가")
		public ResponseEntity<Void> join(@RequestBody GroupInfo groupInfo){
			GroupService.joinGroup(groupInfo);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@DeleteMapping("/Group/exit") 
		@ApiOperation(value="그룹 탈퇴")
		public ResponseEntity<Void> exit(@RequestBody GroupInfo groupInfo){
			
			GroupService.exitGroup(groupInfo);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@GetMapping("/Group/{groupid}/members") 
		@ApiOperation(value="그룹 참가 멤버 조회")
		public ResponseEntity<?> getAttendants(@PathVariable int groupid){
			List<User> list = GroupService.getAttendants(groupid); 
			//위와같은 상황 대비
		    return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
	

}	
