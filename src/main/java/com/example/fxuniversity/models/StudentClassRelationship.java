package com.example.fxuniversity.models;

import java.util.UUID;

public class StudentClassRelationship {

    UUID studentID;
    UUID classID;
    public StudentClassRelationship(UUID studentID, UUID classID) {
        this.studentID = studentID;
        this.classID = classID;
    }


}
