package com.gwg.demo.util;

import org.springframework.stereotype.Component;

import com.gwg.demo.common.DetailRes;
import com.gwg.demo.domain.UserMessage;

/**
 * 消息处理类
 */
public class UserMessageProcess implements MessageProcess<UserMessage> {
    @Override
    public DetailRes process(UserMessage userMessage) {
        System.out.println(userMessage);

        
        //return new DetailRes(true, "");//消息被正确消费
        return new DetailRes(false, "");//消息消费异常
    }
}
