package com.example.fxuniversity.models;

import java.util.UUID;

public class ProfessorClassRelationship {

    private UUID professorID;
    private UUID classID;

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
}
