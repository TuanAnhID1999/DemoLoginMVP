package com.ttcsolutons.demologinmvp.model;

public class ObjectPass {
    private int ID;
    private String email;
    private String passWord;


    public ObjectPass(int ID, String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
