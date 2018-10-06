package com.rs.app.dao;

import com.rs.app.model.User;

public interface UserDao {
	int save(User user);
	User getUser(int id);
}
