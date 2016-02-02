package com.henry.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.henry.entity.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<User> get() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM User ORDER BY id")
				.list();
	}
	
	public User getById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
}
