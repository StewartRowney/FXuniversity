package com.example.fxuniversity.models;

public enum FileCategories {
    STUDENT("students"), COURSE("courses"), PROFESSOR("professors");
    String fileName;

    FileCategories (String fileName){
        this.fileName = fileName;
    }
}
