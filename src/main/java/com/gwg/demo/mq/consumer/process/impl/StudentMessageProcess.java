package com.gwg.demo.mq.consumer.process.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gwg.demo.mq.common.DetailRes;
import com.gwg.demo.mq.consumer.process.MessageProcess;
import com.gwg.demo.mq.message.StudentMessage;

/**
 * 学生消息处理逻辑
 */
public class StudentMessageProcess implements MessageProcess<StudentMessage> {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentMessageProcess.class);
	
    @Override
    public DetailRes process(StudentMessage studentMessage) {
    	logger.info("student 消息内容:{}", JSON.toJSON(studentMessage));
        
        return new DetailRes(true, "");//消息被正确消费
        //return new DetailRes(false, "");//消息消费异常
    }
}
