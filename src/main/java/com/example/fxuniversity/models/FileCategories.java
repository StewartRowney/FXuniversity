package com.example.fxuniversity.models;

public enum FileCategories {
    STUDENT("students"),
    COURSE("courses"),
    PROFESSOR("professors"),
    DEPARTMENT("departments"),
    CLASS("classes"),
    TRANSCRIPT("transcripts"),
    COURSECLASSRELATIONSHIP("course-class"),
    PROFESSORCLASSRELATIONSHIP("professor-class"),
    STUDENTCLASSRELATIONSHIP("student-class");

    String fileName;

    FileCategories (String fileName){
        this.fileName = fileName;
    }
}
