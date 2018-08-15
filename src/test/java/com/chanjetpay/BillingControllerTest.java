package com.chanjetpay;

import com.chanjetpay.garlic.api.BillingService;
import com.chanjetpay.garlic.dto.QueryRegionDto;
import com.chanjetpay.result.GenericResult;
import com.chanjetpay.result.ListResult;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class BillingControllerTest {

	@Autowired
	private BillingService billingService;

	@Test
	public void testFindName(){
		ListResult<String> result = billingService.query("123455",new QueryRegionDto());

		System.out.println(result.getData());
	}

	@Test
	public void testUpdateName() throws IOException {
		GenericResult<String> result = billingService.download("123455","2018-02-26-crebas.txt");

		byte[] bt = Base64Utils.decodeFromString(result.getData());

		FileUtils.writeByteArrayToFile(new File("C:/billings/123ttt.txt"),bt);

		System.out.println(result.getData());
	}
}
