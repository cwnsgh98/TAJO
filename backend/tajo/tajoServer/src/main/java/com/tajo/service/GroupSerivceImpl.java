package com.tajo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajo.dao.GroupDao;
import com.tajo.dto.Group;
import com.tajo.dto.GroupInfo;
import com.tajo.dto.User;

@Service
public class GroupSerivceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;
	
	@Override
	public List<Group> getGroupList(int courseid) {
		return groupDao.selectAll(courseid);
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
	public void joinGroup(GroupInfo groupInfo) {
		groupDao.plusPeople(groupInfo);
		groupDao.insertUserGroup(groupInfo);
	}

	@Override
	public void exitGroup(GroupInfo groupInfo) {
		groupDao.minusPeople(groupInfo);
		groupDao.deleteUserGroup(groupInfo);
	}

	@Override
	public List<User> getAttendants(int groupid) {
		
		return groupDao.getAttendants(groupid);
	}

	@Override
	public int makeGroup(Group group) {
		return groupDao.insertGroup(group);
	}
	
}
