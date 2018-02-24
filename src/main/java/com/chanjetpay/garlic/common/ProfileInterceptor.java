package com.chanjetpay.garlic.common;

import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.OperatorDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProfileInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInterceptor.class);

	@Autowired
	private OperatorService operatorService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

		Subject currentUser = SecurityUtils.getSubject();
		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();

		OperatorDto operatorDto = null;
		if(session.getAttribute("login_user") == null){
			String loginName = (String)currentUser.getPrincipal();
			operatorDto = operatorService.find(loginName).getValue();

			session.setAttribute("login_user",operatorDto);
		}

		operatorDto = (OperatorDto)session.getAttribute("login_user");

		//
		//MenuEntity settingMenuEntity = new MenuEntity("1030","系统设置","/admin/setting");
		//MenuEntity my11 = new MenuEntity("1031","个人信息","11");
		//MenuEntity my12 = new MenuEntity("2032","系统安全","22");
		//settingMenuEntity.addSubMenu(my11).addSubMenu(my12).addSubMenu(my11);
		//
		//
		//MenuEntity tradeMenuEntity = new MenuEntity("1010","交易管理","/admin/trade");
		//MenuEntity my01 = new MenuEntity("101010","我的","1111");
		//MenuEntity my02 = new MenuEntity("101011","三方","1111");
		//tradeMenuEntity.addSubMenu(my01).addSubMenu(my02).addSubMenu(my11).addSubMenu(my12);
		//
		//menuEntities.add(settingMenuEntity);
		//menuEntities.add(new MenuEntity("1020","区域管理","/admin"));
		//menuEntities.add(tradeMenuEntity);
		//
		//Collections.sort(menuEntities);

		//operatorDto.setAvatar("/official_accounts.png");
		//operatorDto.setNickName("张老三");
		//operatorDto.setRole("社区管理员");
		//operatorDto.setAttentionCount(12);
		//operatorDto.setMessageCount(3);
		//operatorDto.setTaskCount(0);
		//operatorDto.setAttentionCount(0);


		modelAndView.addObject("user", operatorDto);
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
