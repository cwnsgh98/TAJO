package com.tajo.dao;

import java.util.List;

import com.tajo.dto.Record;
import com.tajo.dto.User;

public interface UserDao {
	
		List<User> selectAll();

		int insertUser(User user);
		
		User selectOne(String id);

		Record loadRecord(User user);
		
		int saveRecord(Record record);

		double loadAverage();
}
