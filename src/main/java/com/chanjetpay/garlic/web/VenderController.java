package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.common.ProfileUtil;
import com.chanjetpay.garlic.pojo.ProfileEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vender")
public class VenderController {

	@RequestMapping(value = {"","/","index"})
	public String index(){
		ProfileEntity profile = ProfileUtil.getProfile();

		if(profile.isBlockOperator()){
			return "redirect:/vender/block";
		}else{
			return "redirect:/vender/merchant";
		}
	}

	@RequestMapping("personal")
	public String personal(){
		return "personal";
	}

	@RequestMapping("password")
	public String password(){
		return "password";
	}

	@RequestMapping("notice")
	public String notice(){
		return "notice";
	}

	@RequestMapping("alert")
	public String alert(){
		return "alert";
	}
}
