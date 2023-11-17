package com.tajo.service;

import java.util.List;

import com.tajo.dto.Group;

public interface GroupService {

	List<Group> getGroupList();

	Group getGroup(int id);

	void writeGroup(Group group);

	void removeGroup(int id);

	void modifyGroup(Group group);

}
