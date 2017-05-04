package com.anh.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
// For mapping Hibernate Exception Translator with the current DAO
@Transactional
@Component("messageDao")
public class MessagesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria cri = session().createCriteria(Message.class);
		return cri.list();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Criteria cri = session().createCriteria(Message.class);
		cri.add(Restrictions.eq("username", username));

		return cri.list();
	}

	public Message getMessageById(int id) {

		Criteria cri = session().createCriteria(Message.class);
		cri.add(Restrictions.idEq(id));

		return (Message)cri.uniqueResult();
	}

	public void create(Message message) {
		session().save(message);
	}

	public void update(Message message) {
		session().update(message);
	}
	
	public void saveOrUpdate(Message message) {
		session().saveOrUpdate(message);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}
}
