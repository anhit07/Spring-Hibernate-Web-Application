package com.anh.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.anh.spring.web.dao.Offer;
import com.anh.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offDao;

	@Autowired
	public void setOffDao(OffersDao offDao) {
		this.offDao = offDao;
	}

	public List<Offer> getCurrent() {
		return offDao.getOffers();
		// return offDao.getOffers("Mai");
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void createOffer(Offer offer) {
		offDao.create(offer);

	}

	public void throwTestException() {
		offDao.getOfferById(99999);
	}

	public boolean hasOffer(String name) {

		if (name == null)
			return false;
		List<Offer> offers = offDao.getOffers(name);
		if (offers.size() == 0) {
			return false;
		}

		return true;
	}

	public Offer getOffer(String username) {

		if (username == null)
			return null;

		List<Offer> offers = offDao.getOffers(username);

		if (offers.size() == 0) {
			return null;
		}
		return offers.get(0);
	}

	public void delete(int id) {
		offDao.delete(id);
	}

}
