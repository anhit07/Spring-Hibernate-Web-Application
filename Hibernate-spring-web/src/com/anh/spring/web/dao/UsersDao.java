package com.anh.spring.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UsersDao implements Serializable{

	private static final long serialVersionUID = -5706861069249947961L;

	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	// Get the current session of the sessionFactory ~ same as JdbcTemplate
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public UsersDao() {
		System.out.println("Loaded Offer DAO");
	}

	/*
	 * @Autowired public void setDataSource(DataSource jdbc) { this.jdbc = new
	 * NamedParameterJdbcTemplate(jdbc); }
	 */

	// Using JdbcTemplate
	/*
	 * @Transactional public boolean create(User user) {
	 * 
	 * BeanPropertySqlParameterSource param = new
	 * BeanPropertySqlParameterSource( user);
	 * 
	 * MapSqlParameterSource params = new MapSqlParameterSource();
	 * params.addValue("username", user.getUsername());
	 * params.addValue("password", passwordEncoder.encode(user.getPassword()));
	 * params.addValue("email", user.getEmail()); params.addValue("enabled",
	 * user.isEnabled()); params.addValue("authority", user.getAuthority());
	 * 
	 * jdbc.update("INSERT INTO USERS (username, password, email, enabled) " +
	 * "VALUES (:username, :password, :email, :enabled)", params); return
	 * jdbc.update("INSERT INTO AUTHORITIES (username, authority) " +
	 * "VALUES (:username, :authority)", params) == 1; }
	 */

	// Using Hibernate
	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	// Jdbc Template
	/*
	 * public boolean exists(User user) { return jdbc.queryForObject(
	 * "SELECT COUNT(*) FROM USERS WHERE USERNAME = :username", new
	 * MapSqlParameterSource("username", user.getUsername()), Integer.class) >
	 * 0; }
	 */

	// Hibernate
	public boolean exists(String username) {
		User user = getUser(username);
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		/*
		 * return jdbc .query(
		 * "SELECT * FROM USERS, AUTHORITIES WHERE USERS.USERNAME = AUTHORITIES.USERNAME"
		 * , BeanPropertyRowMapper.newInstance(User.class));
		 */
		// Using hiberate SQL
		return session().createQuery("from User").list();// from User is the name of bean
	}

	public User getUser(String username){
		Criteria cri = session().createCriteria(User.class);
		cri.add(Restrictions.idEq(username));
		return (User)cri.uniqueResult();
	}
	
}
