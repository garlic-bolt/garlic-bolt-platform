package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.api.MerchantService;
import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.common.ProfileUtil;
import com.chanjetpay.garlic.dto.*;
import com.chanjetpay.garlic.pojo.ProfileEntity;
import com.chanjetpay.result.BasicResult;
import com.chanjetpay.result.GenericResult;
import com.chanjetpay.result.ListResult;
import com.chanjetpay.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("vender")
public class VenderController {

	@Autowired
	private BlockService blockService;

	@Autowired
	private MerchantService merchantService;

	@RequestMapping(value = {"","/","index"})
	public String index(){
		ProfileEntity profile = ProfileUtil.getProfile();

		if(profile.isBlockOperator()){
			return "redirect:/vender/block";
		}else{
			return "redirect:/vender/merchant";
		}
	}

	@RequestMapping("notice")
	public String notice(){
		return "notice";
	}

	@RequestMapping("alert")
	public String alert(){
		return "alert";
	}

	@RequestMapping(value = "block",method = RequestMethod.GET)
	public String block(@RequestParam(name = "tab", required = false) String tab, Model model) {
		//社区信息
		ProfileEntity profile = ProfileUtil.getProfile();
		GenericResult<BlockDto> blockResult = blockService.findByBlockCode(profile.getBlockCode());
		BlockDto blockDto = blockResult.getData();
		WxOfficialDto wxOfficialDto = blockResult.getData().getWxOfficial();
		if (wxOfficialDto == null) {
			wxOfficialDto = new WxOfficialDto();
		}

		//basic, wx, alipay
		String curTab = "";
		if("wx".equals(tab)){
			curTab = "wx";
		}else if("alipay".equals(tab)){
			curTab = "alipay";
		}else{
			curTab = "basic";
		}

		model.addAttribute("curTab",curTab);
		model.addAttribute("wxOfficial", wxOfficialDto);
		model.addAttribute("block", blockDto);

		return "main";
	}

	@RequestMapping(value = "wxOfficial", method = RequestMethod.POST)
	public String modifyWxOfficial(@ModelAttribute WxOfficialDto wxOfficial, RedirectAttributes attr) {

		ProfileEntity profile = ProfileUtil.getProfile();
		//保存失败，跳转到首页
		BasicResult result = blockService.addWxOfficial(profile.getBlockCode(), wxOfficial);

		if (!result.getCode().equals(Result.SUCCESS)) {
			attr.addFlashAttribute("message", result.getDesc());
		}else{
			attr.addFlashAttribute("message", "微信支付信息保存成功");
		}

		return "redirect:/vender/block?tab=wx";
	}

	@RequestMapping(value = "merchant-index", method = RequestMethod.GET)
	public String merchantIndex(@ModelAttribute MerchantDto query, Model model){
		ProfileEntity profile = ProfileUtil.getProfile();
		ListResult<MerchantDto> result = merchantService.queryALl(profile.getBlockCode());

		model.addAttribute("query",query);
		model.addAttribute("merchantList", result.getData());
		return "merchant-index";
	}

	@RequestMapping(value = "merchant", method = RequestMethod.GET)
	public String merchant(){
		return "merchant";
	}

	@Autowired
	private OperatorService operatorService;

	@RequestMapping(value = "operator-index", method = RequestMethod.GET)
	public String operatorIndex(@ModelAttribute OperatorDto query, Model model){
		ProfileEntity profile = ProfileUtil.getProfile();

		ListResult<OperatorDto> result = operatorService.query(profile.getMerchantId(), query);

		if(!result.getCode().equals(Result.SUCCESS)){
			model.addAttribute("query",query);
			model.addAttribute("message", result.getDesc());
			return "operator-index";
		}

		model.addAttribute("query",query);
		model.addAttribute("list", result.getData());
		return "operator-index";
	}

	@RequestMapping(value = "member-index", method = RequestMethod.GET)
	public String memberIndex(@ModelAttribute MemberDto query, Model model){
		ProfileEntity profile = ProfileUtil.getProfile();
		//ListResult<OperatorDto> result = merchantService.queryALl(profile.getBlockCode());

		model.addAttribute("query",query);
		//model.addAttribute("merchantList", result.getData());
		return "operator-index";
	}

	@RequestMapping(value = "claim-index", method = RequestMethod.GET)
	public String claimIndex(@ModelAttribute ClaimDto query, Model model){
		ProfileEntity profile = ProfileUtil.getProfile();
		//ListResult<OperatorDto> result = merchantService.queryALl(profile.getBlockCode());

		model.addAttribute("query",query);
		//model.addAttribute("merchantList", result.getData());
		return "claim-index";
	}

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String account(){
		return "account";
	}

	@RequestMapping(value = "billing", method = RequestMethod.GET)
	public String billing(){
		return "billing";
	}

	@RequestMapping(value = "statistic", method = RequestMethod.GET)
	public String statistic(){
		return "statistic";
	}

	@RequestMapping(value = "personal", method = RequestMethod.GET)
	public String personal(){
		return "personal";
	}

	@RequestMapping(value = "rate", method = RequestMethod.GET)
	public String rate(){
		return "rate";
	}

	@RequestMapping(value = "publish", method = RequestMethod.GET)
	public String publish(){
		return "publish";
	}
}
