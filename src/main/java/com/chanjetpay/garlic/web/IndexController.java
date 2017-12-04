package com.chanjetpay.garlic.web;

//import com.chanjetpay.garlic.api.BlockService;
//import com.chanjetpay.garlic.dto.UserDto;
import com.chanjetpay.garlic.api.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by libaoa on 2017/11/7.
 */
@Controller
public class IndexController {

	@Autowired
	@Qualifier("blockService")
	private BlockService blockService;

	@RequestMapping({"/",""})
	public String index(){
		return "index1";
	}

	@RequestMapping("/test")
	public String home(){
		return "home";
	}


	//
	//@RequestMapping("/")
	//public String index(){
	//
	//	List<UserDto> users = blockService.queryUsers("hehe哈哈哈");
	//
	//	for(UserDto user : users){
	//		System.out.println(user);
	//	}
	//
	//	return "index";
	//}
	//
	//
	//@RequestMapping("/save")
	//public String save(){
	//
	//	UserDto d1 = new UserDto();
	//	d1.setNickName("张磊磊");
	//	UserDto dto = blockService.saveUser(d1);
	//
	//	System.out.println(dto);
	//
	//
	//	return "home";
	//}
}
