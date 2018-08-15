package com.chanjetpay;

import com.chanjetpay.garlic.dto.MerchantDto;
import com.chanjetpay.result.GenericResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class ArrayTest {

	private static final Logger logger = LoggerFactory.getLogger(ArrayTest.class);

	@Test
	public void testLogger(){
		GenericResult<MerchantDto> result = GenericResult.newGenericResult();
		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setAdminName("张三123");
		result.setData(merchantDto);

		logger.info("result:"+ result);
	}

	@Test
	public void testSort(){
		List<String> arr = new ArrayList<>();
		arr.add("1011");
		arr.add("10");
		arr.add("1020");
		arr.add("2010");
		arr.add("1021");
		arr.add("20");
		arr.add("1010");
		arr.add("2030");


		//Collections.sort(arr);

		for(String str : arr){
			System.out.println(str.length() / 2);
		}

	}
}
