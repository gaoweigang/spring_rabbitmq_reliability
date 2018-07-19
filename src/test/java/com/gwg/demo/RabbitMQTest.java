package com.gwg.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.gwg.demo.common.DetailRes;
import com.gwg.demo.consumer.ConsumerExample;
import com.gwg.demo.domain.UserMessage;
import com.gwg.demo.producer.ProducerExample;

/**
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-rabbitmq.xml") 
public class RabbitMQTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQTest.class);
	
	@Autowired
	private ProducerExample producerExample;
	
	@Autowired
	private ConsumerExample ConsumerExample;
	
	@Test
	public void produce(){
		for(int i = 0; i < 10; i++){
			UserMessage userMessage = new UserMessage(1000+i, "gaoweigang"+i);
			producerExample.send(userMessage);

		}
		
		
	}
	
/*	@Test
	public void consumer(){
		
		DetailRes result = ConsumerExample.consume();
		logger.info("消费：{}", JSON.toJSON(result));
	}
	*/
	
	
	



}
