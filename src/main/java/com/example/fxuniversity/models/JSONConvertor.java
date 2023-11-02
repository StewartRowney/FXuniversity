package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

public class JSONConvertor {

    public String convertStudentArrayListToJSON(Collection<Student> studentArrayList) throws JsonProcessingException {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        for (Student student : studentArrayList) {
            json += mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        }
        return json;
    }

}
