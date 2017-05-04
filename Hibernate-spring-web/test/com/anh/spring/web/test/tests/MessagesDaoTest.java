package com.anh.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anh.spring.web.dao.Message;
import com.anh.spring.web.dao.MessagesDao;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/anh/spring/web/config/dao-context.xml",
		"classpath:com/anh/spring/web/config/security-context.xml",
		"classpath:com/anh/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTest {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private DataSource dataSource;

	// List of user used to test
	private User user1 = new User("LanAnhTest1", "12345678", "anh1@gmail.com",
			true, "ROLE_USER");
	private User user2 = new User("LanAnhTest2", "12345678", "anh2@gmail.com",
			true, "ROLE_ADMIN");
	private User user3 = new User("LanAnhTest3", "12345678", "anh3@gmail.com",
			true, "ROLE_USER");
	private User user4 = new User("LanAnhTest4", "12345678", "anh4@gmail.com",
			false, "ROLE_ADMIN");

	@Before
	public void init() {
		JdbcTemplate jdc = new JdbcTemplate(dataSource);
		jdc.execute("Delete from offer");
		jdc.execute("Delete from message");
		jdc.execute("Delete from users");
	}

	@Test
	public void testSave() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		Message mess = new Message("Test Subject 1", "Content 1", "Lan", "lan@gmail.com", user1.getUsername());
		messagesDao.saveOrUpdate(mess);
	}
}
