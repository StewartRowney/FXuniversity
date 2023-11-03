package com.example.fxuniversity.models;

import java.util.UUID;

public class Admin implements IUser{

    private final UUID id;

    public Admin() {
        id = UUID.randomUUID();
    }

    @Override
    public String getEmail() {
        return "admin";
    }

    public UUID getId() {
        return id;
    }
}
