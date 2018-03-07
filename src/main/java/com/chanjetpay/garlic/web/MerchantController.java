package com.chanjetpay.garlic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vender/merchant")
public class MerchantController {

	@RequestMapping(value = {"", "/", "index"})
	public String index(){
		return "home";
	}
}
