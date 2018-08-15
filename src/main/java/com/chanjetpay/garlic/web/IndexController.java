package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.dto.BlockDto;
import com.chanjetpay.garlic.pojo.EnrollForm;
import com.chanjetpay.garlic.pojo.LoginForm;
import com.chanjetpay.result.GenericResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by libaoa on 2017/11/7.
 */
@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private BlockService blockService;

	//根目录跳转到首页
	@RequestMapping({"/index", ""})
	public String index() {
		return "redirect:/login";
	}

	//用户登录
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(HttpServletRequest request, @ModelAttribute LoginForm loginForm, Model model) {
		ModelAndView mav = new ModelAndView();

		// 安全操作
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			logger.info("已登录状态");
			return "redirect:/vender/index";
		}

		//get请求返回登录页
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "login";
		}

		//post请求进行用户认证
		UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getLoginName(), loginForm.getLoginPass());
		//this is all you have to do to support 'remember me' (no config - built in!):
		token.setRememberMe(loginForm.getRememberMe());
		//尝试进行登录用户，如果登录失败了，我们进行一些处理
		try {
			currentUser.login(token);
			//当我们获登录用户之后
			logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("loginForm", loginForm);
			return "login";
		}

		return "redirect:/vender/index";

	}

	@RequestMapping("/logout")
	public String logout() {
	/*
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

		//登出
		currentUser.logout();*/
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String noauth() {
		return "403";
	}
}
