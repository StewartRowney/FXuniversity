package com.example.fxuniversity.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderForUni {

    public ArrayList<Student> readStudentsFromDatabase(){
        ArrayList<Student> studentsArrayList = new ArrayList<>();
        try {
            Scanner myScanner = new Scanner(new File("src/main/resources/jsondatabase/"+FileCategories.STUDENT.fileName+".txt"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            while(myScanner.hasNextLine()) {
                String studentJSON = myScanner.nextLine();
                StringReader studentJsonReader = new StringReader(studentJSON);
                Student student = objectMapper.readValue(studentJsonReader,Student.class);
                studentsArrayList.add(student);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return studentsArrayList;
    }
}
