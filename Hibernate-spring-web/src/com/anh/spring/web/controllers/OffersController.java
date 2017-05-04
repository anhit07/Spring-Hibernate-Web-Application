package com.anh.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anh.spring.web.dao.FormValidatonGroup;
import com.anh.spring.web.dao.Offer;
import com.anh.spring.web.dao.User;
import com.anh.spring.web.service.OffersService;

@Controller
public class OffersController {

	private OffersService offSer;

	// private Logger logger = Logger.getLogger(OffersController.class);

	@Autowired
	public void setOffSer(OffersService offSer) {
		this.offSer = offSer;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id: " + id);
		return "home";
	}

	/*
	 * @RequestMapping("/offers") public String showOffers(Model model) { //
	 * offSer.throwTestException(); List<Offer> offers = offSer.getCurrent();
	 * model.addAttribute("offers", offers); return "offers"; }
	 */
	@RequestMapping("/createoffer")
	public String createOffers(Model model, Principal principal) {

		Offer offer = null;

		if (principal != null) {
			String username = principal.getName();
			offer = offSer.getOffer(username);
			if (offer == null) {
				offer = new Offer();
				User user = new User(principal.getName(), "", "", true, "");
				offer.setUser(user);
			}
		}

		model.addAttribute("offer", offer);
		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model,
			@Validated(value = FormValidatonGroup.class) Offer offer,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		if (delete == null) {
			if (result.hasErrors()) {
				System.out.println("Form does not validated");
				List<ObjectError> errors = result.getAllErrors();
				for (ObjectError err : errors) {
					System.out.println(err.getDefaultMessage());
				}
				return "createoffer";
			}

			System.out.println("********Save*********");
			String username = principal.getName();
			offer.setUser(new User(username, "", "", true, ""));;
			offSer.createOffer(offer);
			return "offercreated";
		} else {
			System.out.println("********Delete*********");
			System.out.println(offer.getId());
			offSer.delete(offer.getId());
			return "offerdeleted";
		}

	}

	/*
	 * @RequestMapping("/") public String showHome(Model model) {
	 * 
	 * List<Offer> offers = offSer.getCurrent(); model.addAttribute("offers",
	 * offers); return "home"; }
	 */

	/*
	 * @RequestMapping("/") public ModelAndView showHome(HttpSession session){
	 * 
	 * //session.setAttribute("attr1", "Test attr"); ModelAndView mv = new
	 * ModelAndView("home"); Map<String, Object> model = mv.getModel();
	 * model.put("attr1", "Test attr"); model.put("attr2", "Test attr 2222");
	 * return mv; }
	 */

}
