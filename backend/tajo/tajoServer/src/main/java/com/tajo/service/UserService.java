package com.tajo.service;

import java.util.List;

import com.tajo.dto.Record;
import com.tajo.dto.User;

public interface UserService {
	int signup(User user);

	User login(User user);
	
	List<User> getUserList();

	User getUser(String id);

	Record getUserRecord(User user);

	int setUserRecord(Record record);

	int getAverage();
}
