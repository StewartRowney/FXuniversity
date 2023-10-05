package com.example.fxuniversity.models;

import java.util.UUID;

public class Admin implements IUser{

    @Override
    public String getEmailAddress() {
        return "admin@email.com";
    }

}
