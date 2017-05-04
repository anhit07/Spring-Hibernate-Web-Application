package com.anh.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.anh.spring.web.dao.Message;
import com.anh.spring.web.dao.MessagesDao;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

	public void createUser(User user) {
		usersDao.create(user);
	}

	public boolean exists(User user) {
		return usersDao.exists(user.getUsername());
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUser() {
		return usersDao.getAllUser();
	}

	public void sendMessage(Message message) {
		messagesDao.saveOrUpdate(message);
	}

	public User getUser(String username) {
		return usersDao.getUser(username);
	}

	public List<Message> getMessage(String username) {
		// TODO Auto-generated method stub
		return messagesDao.getMessages(username);
	}

}
