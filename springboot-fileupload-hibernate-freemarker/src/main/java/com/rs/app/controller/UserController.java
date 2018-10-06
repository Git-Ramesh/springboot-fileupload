package com.rs.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.dao.UserDao;
import com.rs.app.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userDao;

	@PostMapping("/save")
	@Transactional
	public User saveUser(@RequestBody User user) {
		int id = this.userDao.save(user);
		return this.userDao.getUser(id);
//		return this.userDao.save(user);
	}
}
