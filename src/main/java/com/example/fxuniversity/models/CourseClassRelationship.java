package com.example.fxuniversity.models;

import java.util.UUID;

public class CourseClassRelationship {

    private UUID courseID;
    private UUID classID;

    public CourseClassRelationship(UUID courseID, UUID classID) {
        this.courseID = courseID;
        this.classID = classID;
    }
}
