package com.gwg.demo.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gwg.demo.mq.common.DetailRes;
import com.gwg.demo.mq.common.MessageConsumer;

/**
 * Created 
 */
@Component
public class StudentConsumerExample {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentConsumerExample.class);

    @Autowired
	@Qualifier("studentMessageConsumer")
    private MessageConsumer messageConsumer;

    public void consume() {
		logger.info("学生消息消费 start .....");
        DetailRes result = messageConsumer.consume();
        logger.info("学生消息消费，返回结果：{}", JSON.toJSON(result));
    }

}
