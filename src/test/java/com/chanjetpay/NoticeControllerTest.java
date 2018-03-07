package com.chanjetpay;

import com.chanjetpay.garlic.api.NoticeService;
import com.chanjetpay.garlic.dto.NoticeDto;
import com.chanjetpay.result.ListResult;
import com.chanjetpay.result.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class NoticeControllerTest {

	@Autowired
	private NoticeService noticeService;

	@Test
	public void testQuery(){
		ListResult<NoticeDto> result = noticeService.queryMerchantNotice("1234");

		Assert.assertEquals(result.getCode(), Result.SUCCESS);

		System.out.println(result.getValues());
	}

	@Test
	public void testRead(){

	}
}
