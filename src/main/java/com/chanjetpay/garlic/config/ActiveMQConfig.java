package com.chanjetpay.garlic.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMQConfig {

	@Bean
	public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	@Bean
	public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(false);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("mytest.queue");
	}

	@Bean
	public Topic topic() {
		return new ActiveMQTopic("mytest.topic");
	}
}
