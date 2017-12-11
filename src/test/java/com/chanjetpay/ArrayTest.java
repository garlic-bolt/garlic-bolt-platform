package com.chanjetpay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarlicBoltPlatformApplication.class)
public class ArrayTest {

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
