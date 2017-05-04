package com.anh.spring.web.test.tests;

import static org.junit.Assert.*;

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

import com.anh.spring.web.dao.Offer;
import com.anh.spring.web.dao.OffersDao;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/anh/spring/web/config/dao-context.xml",
		"classpath:com/anh/spring/web/config/security-context.xml",
		"classpath:com/anh/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTest {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private OffersDao offersDao;

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

	private Offer offer1 = new Offer(user1, "Test offer 1 of user 1");
	private Offer offer2 = new Offer(user2, "Test offer 2 of user 1");
	private Offer offer3 = new Offer(user2, "Test offer 1 of user 2");
	private Offer offer4 = new Offer(user3, "Test offer 1 of user 3");
	private Offer offer5 = new Offer(user3, "Test offer 2 of user 3");
	private Offer offer6 = new Offer(user3, "Test offer 3 of user 3");
	private Offer offer7 = new Offer(user4, "Test offer 1 of user 4");

	@Before
	public void init() {
		JdbcTemplate jdc = new JdbcTemplate(dataSource);
		jdc.execute("Delete from users");
		jdc.execute("Delete from offer");
	}

	@Test
	public void testCreate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.create(offer1);

		List<Offer> offers = offersDao.getOffers();
		assertEquals("Num of offers", 1, offers.size());
		assertEquals("Offer equal", offer1, offers.get(0));

		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);
		offersDao.create(offer5);
		offersDao.create(offer6);
		offersDao.create(offer7);

		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Num of offers", 6, offers2.size());

	}

	@Test
	public void testGetByUsername() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.create(offer1);
		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);
		offersDao.create(offer5);
		offersDao.create(offer6);
		offersDao.create(offer7);

		List<Offer> offers = offersDao.getOffers(user3.getUsername());
		assertEquals("Num of offers", 3, offers.size());

		List<Offer> offers2 = offersDao.getOffers("gfhfg");
		assertEquals("Num of offers", 0, offers2.size());

		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Num of offers", 2, offers3.size());
	}

	@Test
	public void testUpdate() {
		usersDao.create(user1);

		offersDao.create(offer1);

		List<Offer> offers = offersDao.getOffers();
		Offer off = offers.get(0);
		off.setText("Update offer text");
		offersDao.saveOrUpdate(off);

		Offer updatedOff = offersDao.getOfferById(off.getId());
		assertEquals("Update....", off, updatedOff);

	}

	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.create(offer1);
		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);
		offersDao.create(offer5);
		offersDao.create(offer6);
		offersDao.create(offer7);

		Offer retrieved1 = offersDao.getOfferById(offer2.getId());
		assertNotNull("Not null....", retrieved1);

		offersDao.delete(offer2.getId());
		Offer retrieved2 = offersDao.getOfferById(offer2.getId());
		assertNull("Delete....", retrieved2);

	}
}
