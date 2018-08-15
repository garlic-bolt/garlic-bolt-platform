package com.chanjetpay;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class ShiroRealmTest {

	private static final Logger logger = LoggerFactory.getLogger(ShiroRealmTest.class);

	@Test
	public void testMd5() {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		String salt = "xyz1";
		int hashIterations = 1024;
		SimpleHash obj = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(obj.toHex());
	}

	@Test
	public void test2(){
		String algorithmName = "md5";
		String username = "admin";
		String password = "123";
		String salt1 = "xyz1";
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salt1, hashIterations);
		String encodedPassword = hash.toHex();
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testCheckPassword(){

		SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) applicationContext.getBean(org.apache.shiro.mgt.SecurityManager.class));
		Subject sj = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("10000102", "123456");
		try {
			sj.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sj.isAuthenticated());

		if(sj.isAuthenticated())
			logger.info("用户 "+sj.getPrincipal() +" 登录成功");

		//是否有role1这个角色
		if(sj.hasRole("role1")){
			logger.info("有角色role1");
		}else{
			logger.info("没有角色role1");
		}
		//是否有对打印机进行打印操作的权限
		if(sj.isPermitted("printer:print")){
			logger.info("可以对打印机进行打印操作");
		}else {
			logger.info("不可以对打印机进行打印操作");
		}
	}

	@Test
	public void test3(){
		ByteSource u = ByteSource.Util.bytes("QouRPz");
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				"101000", //用户名
				"1a6ef1492a68d7f3cb012b5a88b2260b", //密码
				u,//salt
				"webShiroRealm"  //realm name
		);


	}

}
