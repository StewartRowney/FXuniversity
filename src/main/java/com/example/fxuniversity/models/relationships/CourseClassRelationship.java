package com.example.fxuniversity.models.relationships;

import java.util.ArrayList;
import java.util.UUID;

public class CourseClassRelationship {

    private UUID courseID;
    private ArrayList<UUID> classID = new ArrayList<>();

    public CourseClassRelationship(UUID courseID) {
        this.courseID = courseID;
    }
    public CourseClassRelationship(UUID courseID, ArrayList<UUID> classes) {
        this.courseID = courseID;
        classID = classes;
    }

    public UUID getCourseID() {
        return courseID;
    }

    public ArrayList<UUID> getClassIDs() {
        return classID;
    }

    public void addClass(UUID classId) {
        classID.add(classId);
    }

    public void removeClass(UUID classId) {
        classID.remove(classId);
    }
}
