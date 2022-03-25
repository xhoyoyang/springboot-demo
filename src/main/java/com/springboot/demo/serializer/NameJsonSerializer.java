package com.springboot.demo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class NameJsonSerializer extends JsonSerializer {


    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value != null && value instanceof String) {

            String val = value.toString();
            jsonGenerator.writeString(val.replaceAll("(.)(.*)", "$1**"));

        } else {
            jsonGenerator.writeObject(value);
        }
    }
}
