package com.gwg.demo.util;

import com.gwg.demo.common.DetailRes;

/**
 * Created
 */
public interface MessageProcess<T> {
    DetailRes process(T message);
}
