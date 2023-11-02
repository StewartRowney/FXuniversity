package com.example.fxuniversity.models;

public enum FileCategories {
    STUDENT("students"),
    COURSE("courses"),
    PROFESSOR("professors"),
    DEPARTMENT("departments"),
    CLASS("classes"),
    TRANSCRIPT("transcripts");

    String fileName;

    FileCategories (String fileName){
        this.fileName = fileName;
    }
}
