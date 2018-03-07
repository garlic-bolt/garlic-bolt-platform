package com.chanjetpay.garlic.web;

//import com.chanjetpay.garlic.api.BlockService;
//import com.chanjetpay.garlic.dto.UserDto;

import com.chanjetpay.garlic.api.MerchantService;
import com.chanjetpay.garlic.common.ProfileUtil;
import com.chanjetpay.garlic.pojo.LoginForm;
import com.chanjetpay.garlic.pojo.ProfileEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.font.TrueTypeFont;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by libaoa on 2017/11/7.
 */
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private MerchantService merchantService;

	//根目录跳转到首页
	@RequestMapping({"/index", ""})
	public String index() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute LoginForm loginForm) {
		ModelAndView mav = new ModelAndView();

		// 安全操作
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			logger.info("已登录状态");
			return "redirect:/vender/index";
		}

		//当前我们的用户是匿名的用户，我们尝试进行登录，
		if(request.getMethod().toUpperCase().equals("POST")){
			UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getLoginName(), loginForm.getLoginPass());
			//this is all you have to do to support 'remember me' (no config - built in!):
			token.setRememberMe(true);
			//尝试进行登录用户，如果登录失败了，我们进行一些处理
			try {
				currentUser.login(token);
				//当我们获登录用户之后
				logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msg", e.getMessage());
				return "redirect:/login";
			}

			return "redirect:/vender/index";
		} else {
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
/*		// 安全操作
		Subject currentUser = SecurityUtils.getSubject();
		//登出
		currentUser.logout();*/
		return "redirect:/login";
	}

	@RequestMapping("/enroll")
	public String enroll() {


		// 安全操作
		Subject currentUser = SecurityUtils.getSubject();

		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();
		session.setAttribute("key", "value");

		// 查看用户是否有指定的角色
		if (currentUser.hasRole("client")) {
			logger.info("Look is in your role");
		} else {
			logger.info(".....");
		}

		// 查看用户是否有某个权限
		if (currentUser.isPermitted("look:desk")) {
			logger.info("You can look.  Use it wisely.");
		} else {
			logger.info("Sorry, you can't look.");
		}

		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			logger.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
					"Here are the keys - have fun!");
		} else {
			logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}


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


		return "enroll";
	}

	@RequestMapping("/403")
	public String noauth() {
		return "403";
	}
}
