package com.chanjetpay.garlic.common;

import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.OperatorDto;
import com.chanjetpay.result.GenericResult;
import com.chanjetpay.result.Result;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;


//		//UnknownAccountException 账户不存在
//		//IncorrectCredentialsException 密码不正确
//		//LockedAccountException 用户被锁定了
//		//AuthenticationException 未授权的用户

public class WebShiroRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(WebShiroRealm.class);

	@Autowired
	private OperatorService operatorService;

	/**
	 * 此方法调用  hasRole,hasPermission的时候才会进行回调.
	 *
	 * 权限信息.(授权):
	 * 1、如果用户正常退出，缓存自动清空；
	 * 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
	 * （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
	 * 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		/*
		 * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
		 * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		 * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
		 * 缓存过期之后会再次执行。
		 */
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String loginName = (String) principals.getPrimaryPrincipal();

		//设置权限信息.
/*		for (UserRoleDto role : userInfo.getUserRoleList()) {
			authorizationInfo.addRole(role.getRoleId());
			for (UserPermDto perm : role.getUserPermList()) {
				authorizationInfo.addStringPermission(perm.getPermission());
			}
		}*/

		return authorizationInfo;
	}

	/**
	 * 认证信息(身份验证)
	 * Authentication 是用来验证用户身份
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		//获取用户的输入的账号.
		String loginName = (String)token.getPrincipal();

		logger.info("doGetAuthenticationInfo credentials:" + token.getCredentials());

		// 获取用户名称
		if (StringUtils.isEmpty(loginName)) {
			throw new UnknownAccountException("非法的用户名");
		}

		//通过username从数据库中查找 User对象
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		GenericResult<OperatorDto> result = operatorService.find(loginName);

		if(!result.getCode().equals(Result.SUCCESS) || result.getData() == null){
			throw new UnknownAccountException("用户不存在");
		}

		String password = result.getData().getPassword();
		String salt = result.getData().getSalt();

		ByteSource u = ByteSource.Util.bytes(salt);

		//加密方式;
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				loginName, //用户名
				password, //密码
				u,//salt
				getName()  //realm name
		);


		//明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
/*		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo.getLoginName(), //用户名
				userInfo.getPassword(), //密码
				getName()  //realm name
		);*/

		return authenticationInfo;
	}
}
