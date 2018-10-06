package com.rs.app.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.rs.app.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public int save(User user) {
		System.out.println("save User...");
		/*int id = 0;
		try (Session session = this.sessionFactory.openSession()) {
			System.out.println("Session: " + session);
			id = (int) session.save(user);
		} catch (HibernateException he) {
			System.err.println(he.getMessage());
		}
		return id;*/
		return (int) this.hibernateTemplate.save(user);
	}

	@Override
	public User getUser(int id) {
		User user = null;
//		try (Session session = this.sessionFactory.openSession()) {
//			user = session.get(User.class, id);
//		}
		return this.hibernateTemplate.get(User.class, id);
	}
}