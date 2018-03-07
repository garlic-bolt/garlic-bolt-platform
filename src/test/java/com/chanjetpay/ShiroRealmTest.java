package com.chanjetpay;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class ShiroRealmTest {

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
