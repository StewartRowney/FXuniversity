package com.example.fxuniversity.models;

import com.example.fxuniversity.models.relationships.CourseClassRelationship;
import com.example.fxuniversity.models.relationships.ProfessorClassRelationship;
import com.example.fxuniversity.models.relationships.StudentClassRelationship;

public enum FileCategories {
    STUDENT("students", Student.class),
    COURSE("courses", Course.class),
    PROFESSOR("professors", Professor.class),
    DEPARTMENT("departments", Department.class),
    CLASS("classes", Class.class),
    TRANSCRIPT("transcripts", Transcript.class),
    COURSECLASSRELATIONSHIP("course-class", CourseClassRelationship.class),
    PROFESSORCLASSRELATIONSHIP("professor-class", ProfessorClassRelationship.class),
    STUDENTCLASSRELATIONSHIP("student-class", StudentClassRelationship.class);

    final String fileName;
    final java.lang.Class<?> classType;

    FileCategories (String fileName, java.lang.Class<?> classType){
        this.fileName = fileName;
        this.classType = classType;
    }
}
