package com.anh.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anh.spring.web.dao.User;
import com.anh.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/anh/spring/web/config/dao-context.xml",
		"classpath:com/anh/spring/web/config/security-context.xml",
		"classpath:com/anh/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

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
			true, "ROLE_ADMIN");

	// Delete all data in database

	@Before
	public void init() {
		JdbcTemplate jdc = new JdbcTemplate(dataSource);
		jdc.execute("Delete from offer");
		jdc.execute("Delete from message");
		jdc.execute("Delete from users");
	}

	@Test
	public void testCreation() {

		usersDao.create(user1);

		List<User> users = usersDao.getAllUser();
		assertEquals("Number of users is ", 1, users.size());
		assertEquals("Same user", user1, users.get(0));

		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		List<User> users2 = usersDao.getAllUser();
		assertEquals("Number of users is ", 4, users2.size());
	}

	@Test
	public void testUsers() {
		JdbcTemplate jdc = new JdbcTemplate(dataSource);
		jdc.execute("Delete from users");

		usersDao.create(user2);

		List<User> users = usersDao.getAllUser();
		assertEquals("Number of users is ", 1, users.size());
		assertTrue("User exists", usersDao.exists(user2.getUsername()));
		assertFalse("User exists", usersDao.exists("Ramdom"));

		assertEquals("Same user", user2, users.get(0));
	}
	/*
	 * @Test public void testCreateUser() { // assertEquals("Testing", 1, 1);
	 * User user = new User("AnhTesting", "123456", "EmailTest", true, "User");
	 * 
	 * assertTrue("User test creat true", usersDao.create(user));
	 * 
	 * List<User> users = usersDao.getAllUser();
	 * 
	 * assertEquals("Number of users is ", 1, users.size());
	 * assertTrue("User exist", usersDao.exists(user));
	 * assertEquals("Same user", user, users.get(0)); }
	 */
}
