package com.anh.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anh.spring.web.dao.FormValidatonGroup;
import com.anh.spring.web.dao.Message;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService userService;

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@Autowired
	private MailSender mailSender;

	// @RequestMapping("/login")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	/*
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET) public
	 * String logoutPage(HttpServletRequest request, HttpServletResponse
	 * response) { Authentication auth = SecurityContextHolder.getContext()
	 * .getAuthentication();
	 * 
	 * if (auth != null) { new SecurityContextLogoutHandler().logout(request,
	 * response, auth); } return "redirect:/login?logout";// You can redirect
	 * wherever you want, // but generally it's a good practice to // show login
	 * screen again. }
	 */

	/*
	 * @RequestMapping("/loggedout") public String showLoggedOut() { return
	 * "loggedout"; }
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	// public String createAccout(@Valid User user, BindingResult result) {
	public String createAccout(@Validated(FormValidatonGroup.class) User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newaccount";
		}

		user.setEnabled(true);
		user.setAuthority("ROLE_USER");

		if (userService.exists(user)) {
			System.out.println("Caught duplicate username");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		} else {
			try {
				userService.createUser(user);
			} catch (DuplicateKeyException ex) {
				result.rejectValue("username", "DuplicateKey.user.username",
						"This username already exist");
				// System.out.println(ex.getClass());
				return "newaccount";
			}
			return "accountcreated";
		}
	}

	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	// change return data to json format
	public Map<String, Object> getMessages(Principal principal) {

		List<Message> messages = null;

		if (principal == null) {
			messages = new ArrayList<Message>();
		} else {
			String username = principal.getName();
			messages = userService.getMessage(username);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		System.out.println(data);
		return data;
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	// change return data to json format
	public Map<String, Object> sendMessage(Principal principal,
			@RequestBody Map<String, Object> data) {

		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("home.carpenter2017@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re:" + name + ", your message");
		mail.setText(text);

		Map<String, Object> rval = new HashMap<String, Object>();
		try {
			mailSender.send(mail);
			rval.put("success", true);
			rval.put("target", target);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Fail sending message");
			rval.put("error", true);
		}

		return rval;
	}
}
