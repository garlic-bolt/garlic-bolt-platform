package com.chanjetpay.garlic.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

		System.out.println(">>>MenuInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

		// 只有返回true才会继续向下执行，返回false取消当前请求
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

		List<MenuEntity> menuEntities = new ArrayList<MenuEntity>();


		MenuEntity settingMenuEntity = new MenuEntity("1030","系统设置","/admin/setting");
		MenuEntity my11 = new MenuEntity("1031","个人信息","11");
		MenuEntity my12 = new MenuEntity("2032","系统安全","22");
		settingMenuEntity.addSubMenu(my11).addSubMenu(my12).addSubMenu(my11);


		MenuEntity tradeMenuEntity = new MenuEntity("1010","交易管理","/admin/trade");
		MenuEntity my01 = new MenuEntity("101010","我的","1111");
		MenuEntity my02 = new MenuEntity("101011","三方","1111");
		tradeMenuEntity.addSubMenu(my01).addSubMenu(my02).addSubMenu(my11).addSubMenu(my12);

		menuEntities.add(settingMenuEntity);
		menuEntities.add(new MenuEntity("1020","区域管理","/admin"));
		menuEntities.add(tradeMenuEntity);

		Collections.sort(menuEntities);
		modelAndView.addObject("menus", menuEntities);

		System.out.println(">>>MenuInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		System.out.println(">>>MenuInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}
}
