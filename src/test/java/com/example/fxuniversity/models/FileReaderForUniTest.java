package com.example.fxuniversity.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderForUniTest {

    FileReaderForUni fileReader = new FileReaderForUni();

    @Test
    void readCategoryDataFromDatabase() {
        fileReader.readCategoryDataFromDatabase(FileCategories.DEPARTMENT);
    }
}