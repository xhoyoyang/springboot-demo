package com.springboot.demo.strategy;

import com.springboot.demo.vo.OrderInfo;

/**
 * @author xuhuiyang
 * @Date 2022-04-07
 */
public interface OrderStrategyService {

    void order(OrderInfo orderInfo);
}
