package com.anh.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anh.spring.web.dao.Offer;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.service.OffersService;
import com.anh.spring.web.service.UsersService;

@Controller
public class HomeController {

	private UsersService userService;

	@Autowired
	private OffersService offersService;

	//private static Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String showHome(Model model, Principal pricipal) {
		// logger.debug(">>>>>>>>>>>>Show Home Page>>>>>>>>>>>>>>>>>>>>>>");
		// logger.info(">>>>>>>>>>>>Show Home Page>>>>>>>>>>>>>>>>>>>>>>");//shorter
		// way
		List<Offer> offers = offersService.getCurrent();
		model.addAttribute("offers", offers);
		boolean hasOffer = false;
		if(pricipal != null){
			hasOffer = offersService.hasOffer(pricipal.getName());
		}
		model.addAttribute("hasOffer", hasOffer);
		return "home";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = userService.getAllUser();
		model.addAttribute("users", users);

		return "admin";
	}

}
