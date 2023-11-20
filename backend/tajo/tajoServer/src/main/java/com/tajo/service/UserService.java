package com.tajo.service;

import java.util.List;

import com.tajo.dto.Record;
import com.tajo.dto.User;

public interface UserService {
	int signup(User user);

	User login(User user);
	
	List<User> getUserList();

	User getUser(String id);

	List<Record> getUserRecord(String userid);

	int setUserRecord(Record record);

	int getLower(int userDist);

	int getSame(int userDist);

}
