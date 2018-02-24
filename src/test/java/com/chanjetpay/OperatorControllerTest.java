package com.chanjetpay;

import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.OperatorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class OperatorControllerTest {

	@Autowired
	private OperatorService operatorService;

	@Test
	public void testCreateOperator(){
		OperatorDto operatorDto = new OperatorDto();
		operatorDto.setBlockId("1234");
		operatorDto.setMerchantId("123456");
		operatorDto.setOperatorId("zhangsan");
		operatorDto.setPassword("123456");
		operatorDto.setSalt("abcd");

		operatorService.create("12343",operatorDto);
	}



}
