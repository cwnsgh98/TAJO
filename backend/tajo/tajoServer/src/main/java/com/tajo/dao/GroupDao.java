package com.tajo.dao;

import java.util.List;

import com.tajo.dto.Group;

public interface GroupDao {

	List<Group> selectAll();

	Group selectOne(int groupid);

	Object insertGroup(Group group);

	void deleteGroup(int groupid);

	void updateGroup(Group group);

}
