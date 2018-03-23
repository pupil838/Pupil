package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 3/11/2018.
 */

public class User_SignUp {
    private static final String TAG = "User_SignUp";

    private String user_ID  = "";
    private String user_Name = "";
    private String user_Email = "";
    private String user_password = "";
    private double user_cgpa;

    public User_SignUp() {
    }

    public User_SignUp(String user_ID, String user_Name, String user_Email, String user_password, double user_cgpa) {
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.user_Email = user_Email;
        this.user_password = user_password;
        this.user_cgpa = user_cgpa;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public double getUser_cgpa() {
        return user_cgpa;
    }

    public void setUser_cgpa(double user_cgpa) {
        this.user_cgpa = user_cgpa;
    }

    @Override
    public String toString() {
        return "User_SignUp{" +
                "user_ID='" + user_ID + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", user_Email='" + user_Email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_cgpa=" + user_cgpa +
                '}';
    }
}
