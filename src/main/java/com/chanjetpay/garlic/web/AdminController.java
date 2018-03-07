package com.chanjetpay.garlic.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("vender/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping("/home")
	public String home(HttpSession session) {
		return "home";
	}

	@RequestMapping({"","/"})
	public String dashboard(Model model){



		return "home";
	}
}
