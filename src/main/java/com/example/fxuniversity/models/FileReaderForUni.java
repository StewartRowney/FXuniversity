package com.example.fxuniversity.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderForUni {

    private Scanner myScanner;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Object> readCategoryDataFromDatabase(FileCategories category){
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            myScanner = new Scanner(new File("src/main/resources/jsondatabase/" + category.fileName + ".txt"));
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.registerModule(new JavaTimeModule());

            while(myScanner.hasNextLine()) {
                StringReader jsonReader = new StringReader(myScanner.nextLine());
                Object newObject = objectMapper.readValue(jsonReader, category.classType);
                arrayList.add(newObject);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return arrayList;
    }
}
