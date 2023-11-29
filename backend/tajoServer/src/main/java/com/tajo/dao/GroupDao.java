package com.tajo.dao;

import java.util.List;

import com.tajo.dto.Group;
import com.tajo.dto.GroupInfo;
import com.tajo.dto.User;

public interface GroupDao {

	List<Group> selectAll(int courseid);

	Group selectOne(int groupid);

	int insertGroup(Group group);

	void deleteGroup(int groupid);

	void updateGroup(Group group);



	List<User> getAttendants(int groupid);

	void insertUserGroup(GroupInfo groupInfo);

	void deleteUserGroup(GroupInfo groupInfo);

	void plusPeople(GroupInfo groupInfo);

	void minusPeople(GroupInfo groupInfo);

}
