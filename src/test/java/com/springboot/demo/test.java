package com.springboot.demo;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author xuhuiyang
 * @Date 2022-03-30
 */
public class test {

    @Test
    public void testEquals(){
        Integer a = 1;
        long b =1;
        System.out.println(a == b);
        System.out.println(Objects.equals(a,b));
    }
}
