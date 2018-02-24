package com.chanjetpay;

import com.chanjetpay.garlic.api.BillingService;
import com.chanjetpay.result.BasicResult;
import com.chanjetpay.result.GenericResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class BillingControllerTest {

	@Autowired
	private BillingService billingService;

	@Test
	public void testFindName(){
		GenericResult<String> result = billingService.findName("zhangsan");
		System.out.println(result.getValue());
	}

	@Test
	public void testUpdateName(){
		BasicResult result = billingService.updateName("zhangsan啊","lisi里");
	}
}
