package com.springboot.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.springboot.demo.util.DesensitizationUtil;
import com.springboot.demo.annotation.Desensitization;
import com.springboot.demo.common.enums.DesensitizationType;

import java.io.IOException;
import java.util.Objects;

/**
 * 脱敏序列化
 */
public class DesensitizationSerializer extends JsonSerializer<String> implements ContextualSerializer {

    //脱敏类型
    DesensitizationType type;

    //前置不需要打码的长度
    int prefixNoMaskLen;

    //后置不需要打码的长度
    int suffixNoMaskLen;

    //用什么打码
    String maskStr;

    public DesensitizationSerializer(){}

    public DesensitizationSerializer(DesensitizationType type, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr) {
        this.type = type;
        this.prefixNoMaskLen = prefixNoMaskLen;
        this.suffixNoMaskLen = suffixNoMaskLen;
        this.maskStr = maskStr;
    }


    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if(null != value && value instanceof String){
            switch (type) {
                case CHINESE_NAME:
                    jsonGenerator.writeString(DesensitizationUtil.chineseName(value));
                    break;
                case ID_CARD:
                    jsonGenerator.writeString(DesensitizationUtil.idCardNum(value));
                    break;
                case TELE_PHONE:
                    jsonGenerator.writeString(DesensitizationUtil.telePhone(value));
                    break;
                case MOBILE_PHONE:
                    jsonGenerator.writeString(DesensitizationUtil.mobilePhone(value));
                    break;
                case ADDRESS:
                    jsonGenerator.writeString(DesensitizationUtil.address(value));
                    break;
                case EMAIL:
                    jsonGenerator.writeString(DesensitizationUtil.email(value));
                    break;
                case BANK_CARD:
                    jsonGenerator.writeString(DesensitizationUtil.bankCard(value));
                    break;
                case PASSWORD:
                    jsonGenerator.writeString(DesensitizationUtil.password(value));
                    break;
                case KEY:
                    jsonGenerator.writeString(DesensitizationUtil.key(value));
                    break;
                default:
                    jsonGenerator.writeString(DesensitizationUtil.desValue(value, prefixNoMaskLen, suffixNoMaskLen, maskStr));
            }
        }else{
            jsonGenerator.writeObject(value);
        }
        
    }



    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
                if (desensitization == null) {
                    desensitization = beanProperty.getContextAnnotation(Desensitization.class);
                }
                if (desensitization != null) {
                    return new DesensitizationSerializer(desensitization.type(), desensitization.prefixNoMaskLen(),desensitization.suffixNoMaskLen(), desensitization.maskStr());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

    
}
