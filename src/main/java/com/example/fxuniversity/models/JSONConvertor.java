package com.example.fxuniversity.models;

import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONConvertor {

    public String convertArrayListToJSON(Collection<?> arrayList) {
        try {
            String json = "";
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            for (Object object : arrayList) {
                json += mapper.writeValueAsString(object)+"\n";
            }
            return json;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
