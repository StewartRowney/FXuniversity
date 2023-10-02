package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.UUID;

public class CourseClassRelationship {

    private UUID courseID;
    private ArrayList<UUID> classID;

    public CourseClassRelationship(UUID courseID) {
        this.courseID = courseID;
        classID = new ArrayList<>();
    }
}
