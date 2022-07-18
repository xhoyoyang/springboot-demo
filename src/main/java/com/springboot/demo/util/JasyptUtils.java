package com.springboot.demo.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Created by huiyang on 2022-07-18
 */
public class JasyptUtils {

    public static void main(String[] args) {

        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("tnJpIM6iACWO");
        System.out.println(encryptor.encrypt("123456"));
    }
}
