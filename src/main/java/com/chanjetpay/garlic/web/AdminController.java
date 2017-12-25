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

	@Autowired
	private MerchantService merchantService;

	@RequestMapping({"","/"})
	public String dashboard(Model model){

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


		return "index";
	}
}
