package com.example.fxuniversity.models.relationships;
import java.util.UUID;

public class ProfessorClassRelationship {

    private UUID professorID;
    private UUID classID;

    public ProfessorClassRelationship() {
    }
    public ProfessorClassRelationship(UUID professorID, UUID classID) {
        this.professorID = professorID;
        this.classID = classID;
    }

    public UUID getProfessorID() {
        return professorID;
    }
    public UUID getClassID() {
        return classID;
    }

    public void setProfessorID(UUID professorID) {
        this.professorID = professorID;
    }
    public void setClassID(UUID classID) {
        this.classID = classID;
    }
}
