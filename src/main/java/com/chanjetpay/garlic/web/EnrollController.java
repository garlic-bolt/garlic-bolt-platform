package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.dto.BlockDto;
import com.chanjetpay.garlic.pojo.EnrollForm;
import com.chanjetpay.result.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 注册
 * Created by libaoa on 2017/11/9.
 */
@Controller
public class EnrollController {

	private static final Logger logger = LoggerFactory.getLogger(EnrollController.class);

	@Autowired
	private BlockService blockService;

	@RequestMapping(value = "/reg/{blockCode}/invite/{inviteCode}", method = RequestMethod.GET)
	public String register(@PathVariable String blockCode, @PathVariable String inviteCode, Model model){
		GenericResult<BlockDto> result = blockService.findByBlockCode(blockCode);
		if (result.isError() || !inviteCode.equals(result.getData().getInviteCode())) {
			logger.error("社区不存在或邀请码不匹配");
			throw new RuntimeException("社区不存在或邀请码不匹配");
		}

		BlockDto blockDto = result.getData();
		EnrollForm enrollForm = new EnrollForm(blockDto.getInviteCode(),blockDto.getBlockCode(), blockDto.getBlockName(), blockDto.getWardenEmail());

		model.addAttribute("enroll", enrollForm);
		return "enroll";
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	public String enroll(@ModelAttribute EnrollForm enrollForm, Model model){

		String blockCode = enrollForm.getBlockCode();
		String inviteCode = enrollForm.getInviteCode();

		if(!enrollForm.validate()){
			model.addAttribute("enroll", enrollForm);
			model.addAttribute("msg", "参数校验不通过，请检查后重新提交");
			return "enroll";
		}

		GenericResult<BlockDto> blockResult = blockService.findByBlockCode(blockCode);

		if(blockResult.isError()  || !blockResult.getData().getInviteCode().equals(inviteCode)){
			model.addAttribute("enroll", enrollForm);
			model.addAttribute("msg", blockResult.getDesc());
			return "enroll";
		}

		BlockDto blockDto = blockResult.getData();

		blockDto.setWardenName(enrollForm.getWardenName());
		blockDto.setPassword(enrollForm.getPassword());
		blockDto.setDistrict(enrollForm.getAddress());
		blockDto.setMemo(enrollForm.getIndustry());

		GenericResult<BlockDto> result = blockService.complete(inviteCode, blockDto);
		if(result.isError()){
			model.addAttribute("enroll", enrollForm);
			model.addAttribute("msg", result.getDesc());
			return "enroll";
		}

		return "redirect:/login";
	}
}
