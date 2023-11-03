package com.example.fxuniversity.models.relationships;

import java.util.ArrayList;
import java.util.UUID;

public class CourseClassRelationship {

    private UUID courseID;
    private ArrayList<UUID> classIDs = new ArrayList<>();

    public CourseClassRelationship() {

    }
    public CourseClassRelationship(UUID courseID) {
        this.courseID = courseID;
    }
    public CourseClassRelationship(UUID courseID, ArrayList<UUID> classes) {
        this.courseID = courseID;
        classIDs = classes;
    }

    public UUID getCourseID() {
        return courseID;
    }
    public ArrayList<UUID> getClassIDs() {
        return classIDs;
    }

    public void setCourseID(UUID courseID) {
        this.courseID = courseID;
    }
    public void setClassIDs(ArrayList<UUID> classIDs) {
        this.classIDs = classIDs;
    }

    public void addClass(UUID classId) {
        classIDs.add(classId);
    }

    public void removeClass(UUID classId) {
        classIDs.remove(classId);
    }
}
