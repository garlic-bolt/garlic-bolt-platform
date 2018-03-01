package com.chanjetpay.garlic.common;

import com.chanjetpay.garlic.api.AuthorityService;
import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.AuthorityDto;
import com.chanjetpay.garlic.dto.OperatorDto;
import com.chanjetpay.garlic.pojo.NoticeEntity;
import com.chanjetpay.garlic.pojo.ProfileEntity;
import com.chanjetpay.result.ListResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProfileInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInterceptor.class);

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private AuthorityService authorityService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

		Subject currentUser = SecurityUtils.getSubject();
		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();

		ProfileEntity profile = null;
		if(session.getAttribute("login_user") != null) {
			profile = (ProfileEntity)session.getAttribute("login_user");
		} else {
			String loginName = (String)currentUser.getPrincipal();
			OperatorDto operatorDto = operatorService.find(loginName).getValue();

			profile.setOperator(operatorDto);
			profile.setUserName(operatorDto.getNickName());
			profile.setAvatarUrl(operatorDto.getAvatar());

			//todo:通过服务获取
			List<NoticeEntity> alertMessages = new ArrayList<>();
			NoticeEntity entity1 = new NoticeEntity();
			entity1.setIconUrl("/u");
			entity1.setTitle("报警");
			alertMessages.add(entity1);
			profile.setAlertMessages(alertMessages);

			ListResult<AuthorityDto> authListResult = authorityService.queryByOperator(loginName);
			profile.setAuthorities(authListResult.getValues());
			profile.setMenus(authListResult.getValues());

			session.setAttribute("login_user",profile);
		}

		modelAndView.addObject("user", profile);
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
