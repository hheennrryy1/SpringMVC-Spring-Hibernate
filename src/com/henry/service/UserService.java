package com.henry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henry.dao.UserDao;
import com.henry.entity.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	public void delete(User user) {
		userDao.delete(user);
	}
	
	public void update(User user) {
		userDao.update(user);
	}
	
	public List<User> get() {
		return userDao.get();
	}

	public User getById(int id) {
		return userDao.getById(id);
	}
	
}
