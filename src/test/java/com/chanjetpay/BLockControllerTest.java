package com.chanjetpay;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.dto.WxOfficialDto;
import com.chanjetpay.garlic.enums.OfficialTypeEnum;
import com.chanjetpay.result.BasicResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class BLockControllerTest {

	@Autowired
	private BlockService blockService;

	@Test
	public void testAddWx(){
		WxOfficialDto wxOfficial = new WxOfficialDto("abcd","1234","hahaha");
		wxOfficial.setOfficialType(OfficialTypeEnum.ENTERPRISE);
		wxOfficial.setUrl("thttp://badiu");
		BasicResult result = blockService.addWxOfficial("100", wxOfficial);
	}


}
