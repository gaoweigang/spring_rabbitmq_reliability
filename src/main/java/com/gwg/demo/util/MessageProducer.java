package com.gwg.demo.util;

import com.gwg.demo.common.DetailRes;
import com.gwg.demo.common.MessageWithTime;

/**
 * 
 */
public interface MessageProducer {
    DetailRes send(Object message);

    DetailRes send(MessageWithTime messageWithTime);
}
