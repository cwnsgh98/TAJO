package com.tajo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajo.dao.GroupDao;
import com.tajo.dto.Group;
import com.tajo.dto.User;

@Service
public class GroupSerivceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;
	
	@Override
	public List<Group> getGroupList() {
		return groupDao.selectAll();
	}

	@Override
	public Group getGroup(int groupid) {
		return groupDao.selectOne(groupid);
	}

	@Override
	public void writeGroup(Group group) {
		groupDao.insertGroup(group);
	}

	@Override
	public void removeGroup(int groupid) {
		groupDao.deleteGroup(groupid);
	}

	@Override
	public void modifyGroup(Group group) {
		groupDao.updateGroup(group);
	}

	@Override
	public void joinGroup(HashMap hashmap) {
		groupDao.insertUserGroup(hashmap);
	}

	@Override
	public void exitGroup(HashMap hashmap) {
		groupDao.deleteUserGroup(hashmap);
	}

	@Override
	public List<User> getAttendants(int id) {
		groupDao.getAttendants(id);
		return null;
	}
	
}
