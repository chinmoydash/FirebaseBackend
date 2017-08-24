package com.example.chinmoydash.firebasebackend;

/**
 * Created by chinmoydash on 22/08/17.
 */




    public class DataModel {
        String name;
        String email;

    public DataModel() {

    }

    public DataModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

