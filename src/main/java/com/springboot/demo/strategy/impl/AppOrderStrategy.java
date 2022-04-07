package com.springboot.demo.strategy.impl;

import com.springboot.demo.strategy.OrderStrategyService;
import com.springboot.demo.vo.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuhuiyang
 * @Date 2022-04-07
 */
@Component("appOrder")
@Slf4j
public class AppOrderStrategy implements OrderStrategyService {

    @Override
    public void order(OrderInfo orderInfo) {

        log.info("app订单逻辑");
    }
}
