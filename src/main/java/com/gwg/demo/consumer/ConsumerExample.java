package com.gwg.demo.consumer;

import javax.xml.soap.Detail;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gwg.demo.common.DetailRes;
import com.gwg.demo.util.MessageConsumer;

/**
 * Created 
 */
@Component
public class ConsumerExample {

    @Autowired
    private MessageConsumer messageConsumer;

	@RabbitListener(queues = "${rabbitmq.queue}")
    public void consume() {
        DetailRes result = messageConsumer.consume();
        System.out.println("返回结果"+JSON.toJSON(result));
    }

}
