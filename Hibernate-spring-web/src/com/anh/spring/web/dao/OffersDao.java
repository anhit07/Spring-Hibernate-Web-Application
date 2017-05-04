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
@Component("offerDao")
public class OffersDao {

	@Autowired
	private SessionFactory sessionFactory;

	// Get the current session of the sessionFactory ~ same as JdbcTemplate
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		Criteria cri = session().createCriteria(Offer.class);
		cri.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return cri.list();
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {
		Criteria cri = session().createCriteria(Offer.class);
		cri.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		cri.add(Restrictions.eq("u.username", username));

		return cri.list();
	}

	public Offer getOfferById(int id) {

		Criteria cri = session().createCriteria(Offer.class);
		cri.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		cri.add(Restrictions.idEq(id));

		return (Offer)cri.uniqueResult();
	}

	public void create(Offer offer) {
		session().save(offer);
	}

	public void update(Offer offer) {
		session().update(offer);
	}
	
	public void saveOrUpdate(Offer offer) {
		session().saveOrUpdate(offer);
	}

	// *********Search for transational creation on Hibernate
	/*@Transactional
	public int[] create(List<Offer> offers) {

		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(offers.toArray());

		return jdbc
				.batchUpdate(
						"insert into offers (username, text) values (:username, :text)",
						params);
	}*/

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}
}
