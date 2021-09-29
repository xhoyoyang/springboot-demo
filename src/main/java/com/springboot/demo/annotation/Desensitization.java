package com.springboot.demo.annotation;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.demo.common.enums.DesensitizationType;
import com.springboot.demo.serializer.DesensitizationSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerializer.class)
public @interface Desensitization {

    //脱敏类型
    DesensitizationType type() ;

    //前置不需要打码的长度
    int prefixNoMaskLen() default 1;

    //后置不需要打码的长度
    int suffixNoMaskLen() default 1;

    //用什么打码
    String maskStr() default "*";



}
