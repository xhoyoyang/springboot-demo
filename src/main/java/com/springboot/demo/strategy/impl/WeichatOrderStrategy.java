package com.springboot.demo.strategy.impl;

import com.springboot.demo.strategy.OrderStrategyService;
import com.springboot.demo.vo.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xuhuiyang
 * @Date 2022-04-07
 */
@Component("wxOrder")
@Slf4j
public class WeichatOrderStrategy implements OrderStrategyService {

    @Override
    public void order(OrderInfo orderInfo) {
      
        log.info("微信订单逻辑");
    }
}
