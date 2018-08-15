package com.chanjetpay.garlic.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易处理
 */
@Controller
public class TradeController {

	public String home(){

		return "trade-home";
	}



	@Autowired
	private JmsTemplate jmsTemplate;


	@Autowired
	private Queue queue;

	@Autowired
	private Topic topic;

	@RequestMapping("/queue/{name}")
	public String alarmQueue(@PathVariable String name){

		List<String> alarmStrList = new ArrayList<>();
		alarmStrList.add(name+"out fence01");
		alarmStrList.add(name+"out fence02");
		alarmStrList.add(name+"in fence01");
		alarmStrList.add(name+"in fence02");


		for (String alarmStr : alarmStrList) {
			jmsTemplate.convertAndSend(queue, alarmStr);
		}

		return "login";
	}

	@RequestMapping("/topic/{name}")
	public String topicSend(@PathVariable String name){

		List<String> alarmStrList = new ArrayList<>();
		alarmStrList.add(name+"out fence01--topic");
		alarmStrList.add(name+"out fence02--topic");
		alarmStrList.add(name+"in fence01--topic");
		alarmStrList.add(name+"in fence02--topic");


		for (String alarmStr : alarmStrList) {
			jmsTemplate.convertAndSend(topic, alarmStr);
		}

		return "login";
	}

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.queue",containerFactory="queueListenerFactory")
	@SendTo("out.queue") //为了实现双向队列
	public void receiveQueue(String text) {
		if(StringUtils.isNotBlank(text)){
			System.out.println("AlarmConsumer收到的报文为:"+text);
		}
	}

	@JmsListener(destination = "mytest.topic",containerFactory="topicListenerFactory")
	public void receiveTopic(String text) {
		if(StringUtils.isNotBlank(text)) {
			System.out.println("Consumer2=" + text);
		}
	}

	@JmsListener(destination = "mytest.topic",containerFactory="topicListenerFactory")
	public void receiveTopic2(String text) {
		if(StringUtils.isNotBlank(text)) {
			System.out.println("Consumer3=" + text);
		}
	}
}
