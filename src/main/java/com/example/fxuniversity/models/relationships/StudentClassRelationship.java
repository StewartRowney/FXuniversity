package com.example.fxuniversity.models.relationships;

import java.util.UUID;

public class StudentClassRelationship {

    private UUID studentID;
    private UUID classID;
    public StudentClassRelationship(UUID studentID, UUID classID) {
        this.studentID = studentID;
        this.classID = classID;
    }

    public UUID getStudentID() {
        return studentID;
    }
    public UUID getClassID() {
        return classID;
    }

}
