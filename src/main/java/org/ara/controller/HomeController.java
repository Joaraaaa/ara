package org.ara.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpSession session) {
		
//		session.invalidate();
		
		return "home";
	}
	
	@RequestMapping(value = "/signupcheck", method = RequestMethod.GET)
	public String signupcheck(Locale locale, Model model) {
		
		
		return "signupcheck";
	}
	
	@RequestMapping(value = "/nhome", method = RequestMethod.GET)
	public String nhome(Locale locale, Model model) {
		
		
		return "nhome";
	}
	
	@RequestMapping(value = "/bhome", method = RequestMethod.GET)
	public String bhome(Locale locale, Model model) {
		
		
		return "bhome";
	}
}
