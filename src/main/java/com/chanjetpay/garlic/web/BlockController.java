package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.api.MerchantService;
import com.chanjetpay.garlic.common.ProfileUtil;
import com.chanjetpay.garlic.dto.BlockDto;
import com.chanjetpay.garlic.dto.MerchantDto;
import com.chanjetpay.garlic.dto.WxOfficialDto;
import com.chanjetpay.garlic.pojo.ProfileEntity;
import com.chanjetpay.result.BasicResult;
import com.chanjetpay.result.ListResult;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("vender/block")
public class BlockController {

	@Autowired
	private BlockService blockService;

	@Autowired
	private MerchantService merchantService;

	//社区欢迎页
	@RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
	public String index(Model model){
		//社区信息
		ProfileEntity profile = ProfileUtil.getProfile();
		BlockDto blockDto = blockService.findByBlockCode(profile.getBlockCode()).getValue();
		if(blockDto.getWxOfficial() == null){
			blockDto.setWxOfficial(new WxOfficialDto());
		}
		model.addAttribute("block", blockDto);

		return "main";
	}

	@RequestMapping(value = "/wxOfficial", method = RequestMethod.POST)
	public String modifyWxOfficial(@ModelAttribute WxOfficialDto wxOfficial, RedirectAttributes attr) {

		ProfileEntity profile = ProfileUtil.getProfile();
		//保存失败，跳转到首页
		BasicResult result = blockService.addWxOfficial(profile.getBlockCode(), wxOfficial);

		if (result.isError()) {
			attr.addFlashAttribute("message", result.getDesc());
		}else{
			attr.addFlashAttribute("message", "微信支付信息保存成功");
		}

		return "redirect:/vender/block";
	}

	@RequestMapping(value = "/merchant", method = RequestMethod.GET)
	public String merchant(@ModelAttribute MerchantDto query, Model model){
		ProfileEntity profile = ProfileUtil.getProfile();
		ListResult<MerchantDto> result = merchantService.queryALl(profile.getBlockCode());

		model.addAttribute("query",query);
		model.addAttribute("merchantList", result.getValues());
		return "block-index";
	}
}
