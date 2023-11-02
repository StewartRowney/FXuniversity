package com.example.fxuniversity.models;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterForUni {

    FileWriter writer;

    void writeJSONToFile(String writtenStuff, FileCategories category) {
        try {
            writer = new FileWriter("src/main/resources/jsondatabase/" + category.fileName + ".txt");
            writer.write(writtenStuff);
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        };
    }
}
