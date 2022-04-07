package com.springboot.demo.strategy;

import com.springboot.demo.vo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuhuiyang
 * @Date 2022-04-07
 */
@Service
public class OrderStrategy {

    private final Map<String, OrderStrategyService> orderStrategyMap = new ConcurrentHashMap<>();

    public OrderStrategy(Map<String, OrderStrategyService> strategyMap) {
        this.orderStrategyMap.clear();
        strategyMap.forEach((k, v)-> this.orderStrategyMap.put(k, v));
    }

    public OrderStrategyService getResource(OrderInfo orderInfo){
        return orderStrategyMap.get(orderInfo.getType());
    }
}
