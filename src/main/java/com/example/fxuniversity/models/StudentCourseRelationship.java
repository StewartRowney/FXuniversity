package com.example.fxuniversity.models;

import java.util.UUID;

public class StudentCourseRelationship {

    UUID studentID;
    UUID courseID;
    public StudentCourseRelationship(UUID studentID, UUID courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }


}
