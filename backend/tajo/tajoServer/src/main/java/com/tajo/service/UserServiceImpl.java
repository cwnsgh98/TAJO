package com.tajo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajo.dao.UserDao;
import com.tajo.dto.Record;
import com.tajo.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getUserid());
		
		if(tmp != null && tmp.getPassword().equals(user.getPassword()))
			return tmp;
		return null;
	}

    @Override
    public List<User> getUserList() {
        
        return userDao.selectAll();
    }

	@Override
	public User getUser(String id) {
		
		return userDao.selectOne(id);
	}

	@Override
	public Record getUserRecord(User user) {
		return userDao.loadRecord(user);
	}

	@Override
	public int setUserRecord(Record record) {
		return userDao.saveRecord(record);
	}

	@Override
	public int getLower(int userDist) {
		
		return userDao.selectLower(userDist);
	}

	@Override
	public int getSame(int userDist) {
		return userDao.selectSame(userDist);
	}

	


}
