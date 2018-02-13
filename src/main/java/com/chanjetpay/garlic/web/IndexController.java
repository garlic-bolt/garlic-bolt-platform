package com.chanjetpay.garlic.web;

//import com.chanjetpay.garlic.api.BlockService;
//import com.chanjetpay.garlic.dto.UserDto;
import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.api.MerchantService;
import com.chanjetpay.garlic.dto.MerchantDto;
import com.chanjetpay.result.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by libaoa on 2017/11/7.
 */
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private MerchantService merchantService;

	@RequestMapping({"/",""})
	public String index(){
		return "login";
	}

	@RequestMapping("/enroll")
	public String enroll(){
		//MerchantDto merchantDto = new MerchantDto();
		//merchantDto.setBizType("disnahgn");
		//merchantDto.setAdminName("张三");
		//merchantDto.setAdminMobile("13488746533");
		//GenericResult<MerchantDto> result = merchantService.createMerchant(merchantDto);

		//model.addAttribute("adminInfo",result.getValue());

		//result.getValue().setBizType("测试类型");
		//
		//logger.info("result1:{}", result);
		//
		//
		//
		//GenericResult<MerchantDto> result2 = GenericResult.newGenericResult();
		//MerchantDto merchantDto2 = new MerchantDto();
		//merchantDto.setAdminName("张三123");
		//result.setValue(merchantDto);
		//result.getValue().setBizType("测试类型");
		//logger.info("result2:" + result.getValue());


		return  "enroll";
	}

	@RequestMapping("/logoff")
	public String logoff(){
		return  "login";
	}

	@RequestMapping("/excp")
	public String excp(){
		return  "404";
	}
}
