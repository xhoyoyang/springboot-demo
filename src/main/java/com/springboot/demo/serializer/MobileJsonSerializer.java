package com.springboot.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MobileJsonSerializer extends JsonSerializer {


    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value != null && value instanceof String){

            String val = value.toString();
            jsonGenerator.writeString(val.replaceAll("(\\d{3})(\\d{5})(\\d{3})","$1***$2"));

        } else {
            jsonGenerator.writeObject(value);
        }
    }
}
