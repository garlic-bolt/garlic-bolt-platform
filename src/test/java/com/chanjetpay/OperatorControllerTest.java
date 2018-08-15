package com.chanjetpay;

import com.chanjetpay.garlic.api.OperatorService;
import com.chanjetpay.garlic.dto.OperatorDto;
import com.chanjetpay.garlic.enums.OperatorTypeEnum;
import com.chanjetpay.result.BasicResult;
import com.chanjetpay.result.GenericResult;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
		operatorDto.setBlockCode("100");
		operatorDto.setMerchantId("100");
		operatorDto.setOperatorId("zhangsan");
		operatorDto.setName("张三");
		operatorDto.setType(OperatorTypeEnum.ADMIN);
		operatorDto.setPassword("123456");

		GenericResult<OperatorDto> result = operatorService.create("100",operatorDto);
		System.out.println(result.getCode());
	}

	@Test
	public void testFindByCode(){
		GenericResult<OperatorDto> result = operatorService.find("10000201");
		System.out.println(result);

	}




}
