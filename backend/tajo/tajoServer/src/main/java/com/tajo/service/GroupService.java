package com.tajo.service;

import java.util.HashMap;
import java.util.List;

import com.tajo.dto.Group;
import com.tajo.dto.User;

public interface GroupService {

	List<Group> getGroupList();

	Group getGroup(int id);

	void writeGroup(Group group);

	void removeGroup(int id);

	void modifyGroup(Group group);

	void joinGroup(HashMap hashmap);

	void exitGroup(HashMap hashmap);

	List<User> getAttendants(int id);

}
