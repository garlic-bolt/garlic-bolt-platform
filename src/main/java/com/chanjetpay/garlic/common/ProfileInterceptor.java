package com.chanjetpay.garlic.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		System.out.println(">>>ProfileInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		UserEntity user = new UserEntity();
		user.setAvatar("/official_accounts.png");
		user.setNickName("张老三");
		user.setRole("社区管理员");
		user.setAttentionCount(12);
		user.setMessageCount(3);
		user.setTaskCount(0);

		modelAndView.addObject("user",user);

		System.out.println(">>>ProfileInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		System.out.println(">>>ProfileInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}
}
