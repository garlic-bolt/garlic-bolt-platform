package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.MerchantService;
import com.chanjetpay.garlic.dto.MerchantDto;
import com.chanjetpay.result.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);



	@RequestMapping({"","/"})
	public String dashboard(Model model){



		return "index";
	}
}
