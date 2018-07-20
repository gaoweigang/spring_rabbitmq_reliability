package com.gwg.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.gwg.demo.mq.common.DetailRes;
import com.gwg.demo.mq.consumer.UserConsumerExample;
import com.gwg.demo.mq.message.StudentMessage;
import com.gwg.demo.mq.message.UserMessage;
import com.gwg.demo.mq.producer.StudentProducerExample;
import com.gwg.demo.mq.producer.UserProducerExample;

/**
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-rabbitmq.xml") 
public class RabbitMQTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQTest.class);
	
	@Autowired
	private UserProducerExample userProducerExample;
	
	@Autowired
	private StudentProducerExample studentProducerExample;
	
	@Autowired
	private UserConsumerExample ConsumerExample;
	
	@Test
	public void produce(){
		for(int i = 0; i < 10; i++){
			UserMessage userMessage = new UserMessage(1000+i, "gaoweigang"+i);
			userProducerExample.send(userMessage);
			
			StudentMessage studentMessage = new StudentMessage(1000+i, "gaoweigang"+i, new Date());
			studentProducerExample.send(studentMessage);
		}
	}
	
/*	@Test
	public void consumer(){
		
		DetailRes result = ConsumerExample.consume();
		logger.info("消费：{}", JSON.toJSON(result));
	}
	*/
	
	
	



}
