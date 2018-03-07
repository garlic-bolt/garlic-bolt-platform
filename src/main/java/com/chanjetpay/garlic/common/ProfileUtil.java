package com.chanjetpay.garlic.common;

import com.chanjetpay.garlic.pojo.ProfileEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ProfileUtil {

	public static void saveProfile(ProfileEntity profile) {
		Subject currentUser = SecurityUtils.getSubject();
		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();
		session.setAttribute("login_user", profile);
	}

	public static ProfileEntity getProfile() {
		Subject currentUser = SecurityUtils.getSubject();
		// 在应用的当前会话中设置属性
		Session session = currentUser.getSession();
		Object obj = session.getAttribute("login_user");

		return obj == null ? null : (ProfileEntity) obj;
	}
}
