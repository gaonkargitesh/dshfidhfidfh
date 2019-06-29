package com.example.dshfidhfidfh;

public class Users {


    String name;
    String gender;
    public Users(){

    }

    public Users(String name, String gender, String hobby) {
        this.name = name;
        this.gender = gender;
        this.hobby = hobby;
    }


    String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
