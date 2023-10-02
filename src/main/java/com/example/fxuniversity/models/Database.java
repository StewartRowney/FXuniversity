package com.example.fxuniversity.models;

import java.util.HashMap;
import java.util.UUID;

public class Database {

    HashMap<UUID,Student> studentHashMap = new HashMap<UUID, Student>();
    HashMap<UUID,Course> courseHashMap = new HashMap<UUID, Course>();
    HashMap<UUID, Professor> professorHashMap = new HashMap<>();
}
