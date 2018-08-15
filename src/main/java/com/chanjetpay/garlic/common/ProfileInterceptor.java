package com.chanjetpay.garlic.common;

import com.chanjetpay.garlic.api.AuthorityService;
import com.chanjetpay.garlic.api.NoticeService;
import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.AuthorityDto;
import com.chanjetpay.garlic.dto.NoticeDto;
import com.chanjetpay.garlic.dto.OperatorDto;
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

public class ProfileInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ProfileInterceptor.class);

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private NoticeService noticeService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("ProfileInterceptor 请求 :" + request.getRequestURI());

		ProfileEntity profile = ProfileUtil.getProfile();
		if(profile == null){
			Subject currentUser = SecurityUtils.getSubject();
			// 在应用的当前会话中设置属性
			Session session = currentUser.getSession();
			String loginName = (String)currentUser.getPrincipal();
			OperatorDto operatorDto = operatorService.find(loginName).getData();

			profile = new ProfileEntity();
			profile.setOperator(operatorDto);
			profile.setBlockCode(operatorDto.getBlockCode());
			profile.setMerchantId(operatorDto.getMerchantId());
			profile.setUserName(operatorDto.getName());
			profile.setAvatarUrl(operatorDto.getAvatar());

			ListResult<NoticeDto> result = noticeService.queryMerchantNotice(operatorDto.getMerchantId());
			profile.setAlertMessages(result.getData());

			ListResult<AuthorityDto> authListResult = authorityService.queryByOperator(loginName);
			profile.setAuthorities(authListResult.getData());
			profile.setMenus(authListResult.getData());

			ProfileUtil.saveProfile(profile);
		}

		request.setAttribute("user", profile);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
