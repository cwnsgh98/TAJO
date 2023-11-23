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

import com.tajo.dto.Group;
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
		/*@GetMapping("/Group")
		@ApiOperation(value="그룹 조회", notes="전체 그룹 리스트를 가져온다.")
		public ResponseEntity<?> list(int courseid){
			List<Group> list = GroupService.getGroupList(courseid); //전체 조회
			if(list == null || list.size() == 0)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Group>>(list, HttpStatus.OK);
		}*/
		
		@PostMapping("/Group/{id}/join") 	
		@ApiOperation(value="그룹 참가")
		public ResponseEntity<Void> join(@PathVariable int id, String userid){
			HashMap<String, String> map = new HashMap<>();
			map.put("groupid", String.valueOf(id));
			map.put("userid", userid);
			GroupService.joinGroup(map);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@DeleteMapping("/Group/{id}/exit") 
		@ApiOperation(value="그룹 탈퇴")
		public ResponseEntity<Void> exit(@PathVariable int id, String userid){
			HashMap<String, Object> map = new HashMap<>();
			map.put("groupid", id);
			map.put("userid", userid);
			
			GroupService.exitGroup(map);
		    return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@PostMapping("/Group/{id}/members") 
		@ApiOperation(value="그룹 참가 멤버 조회")
		public ResponseEntity<?> getAttendants(@PathVariable int id){
			List<User> list = GroupService.getAttendants(id); 
			//위와같은 상황 대비
		    return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
	

}	
