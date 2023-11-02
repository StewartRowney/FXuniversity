package com.example.fxuniversity.models;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterForUni {

    FileWriter writer;

    void writeJSONToFile(String writtenStuff, FileCategories category) throws IOException {
        writer = new FileWriter(category.fileName+".txt");
        writer.write(writtenStuff);
        writer.close();
    }
}
