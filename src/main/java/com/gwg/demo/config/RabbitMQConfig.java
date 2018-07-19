package com.gwg.demo.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gwg.demo.util.MQAccessBuilder;
import com.gwg.demo.util.MessageConsumer;
import com.gwg.demo.util.MessageProducer;
import com.gwg.demo.util.UserMessageProcess;

/**
 * 
 * 生成ConnectionFactory
 *
 */
@Configuration
public class RabbitMQConfig {

	private static Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

	// 测试 调试环境
	@Value("${rabbitmq.host}")
	private String host;
	@Value("${rabbitmq.username}")
	private String username;
	@Value("${rabbitmq.password}")
	private String password;
	@Value("${rabbitmq.port}")
	private Integer port;

	@Value("${rabbitmq.direct.exchange}")
	private String exchangeName;

	@Value("${rabbitmq.queue}")
	private String queueName;// 同时作为rountingkey
	
	@Value("${rabbitmq.routing}")
	private String routing;// 同时作为rountingkey

	@Value("${rabbitmq.virtual-host}")
	private String virtualHost;// 同时作为rountingkey

	@Bean
	public ConnectionFactory connectionFactory() {
		logger.info("connectionFactory, host:{}, port:{}, username:{}, password:{}", host, port, username, password);
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);

		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);//设置虚拟主机
		// 设置消息手动确认模式
		connectionFactory.setPublisherConfirms(true); // enable confirm mode
		connectionFactory.setPublisherReturns(true);  // enable return mode
		// connectionFactory.getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);

		return connectionFactory;
	}

	// messsage consumer
	@Bean
	public MessageConsumer messageConsumer() throws IOException {
		logger.info("messageConsumer, exchange:{}, routing:{}, queue:{}", exchangeName, routing, queueName);
		MQAccessBuilder mqAccessBuilder = new MQAccessBuilder(connectionFactory());
		return mqAccessBuilder.buildMessageConsumer(exchangeName, routing, queueName, new UserMessageProcess(), "direct");

	}

	// message producer
	@Bean
	public MessageProducer messageProducer() throws IOException {
		logger.info("messageSender, exchange:{}, routing:{}, queue:{}", exchangeName, routing, queueName);
		MQAccessBuilder mqAccessBuilder = new MQAccessBuilder(connectionFactory());
		return mqAccessBuilder.buildMessageSender(exchangeName, routing, queueName);
	}
	
	/**
	 * 监听器配置
	 * @return
	 */
	@Bean
	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(){
		SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
		rabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
		rabbitListenerContainerFactory.setConcurrentConsumers(1);
		rabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
		return rabbitListenerContainerFactory;
	}
	

}
