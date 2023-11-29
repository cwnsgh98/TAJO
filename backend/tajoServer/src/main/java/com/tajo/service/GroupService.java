package com.tajo.service;

import java.util.HashMap;
import java.util.List;

import com.tajo.dto.Group;
import com.tajo.dto.GroupInfo;
import com.tajo.dto.User;

public interface GroupService {

	List<Group> getGroupList(int courseid);

	Group getGroup(int id);

	void writeGroup(Group group);

	void removeGroup(int id);

	void modifyGroup(Group group);

	void joinGroup(GroupInfo groupInfo);

	void exitGroup(GroupInfo groupInfo);

	List<User> getAttendants(int id);

	int makeGroup(Group group);

}
