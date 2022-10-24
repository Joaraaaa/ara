package org.ara.controller;

import javax.servlet.http.HttpSession;

import org.ara.model.StoreVO;
import org.ara.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NormalController {
	
	@Autowired
	StoreService ss;
	
	@RequestMapping(value = "normal/storelist", method = RequestMethod.GET)
	public String storelist(Model model,StoreVO store) {
		System.out.println(store);
		model.addAttribute("list",ss.selectAll());
		System.out.println(ss.select(store));
		return "normal/storelist";
	}
}
