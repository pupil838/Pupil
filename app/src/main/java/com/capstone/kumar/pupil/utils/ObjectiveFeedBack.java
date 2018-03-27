package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 3/27/2018.
 */

public class ObjectiveFeedBack {
    private static final String TAG = "ObjectiveFeedBack";

    private int one=0;
    private int two=0;
    private int three=0;
    private int four=0;
    private int five=0;
    private String userID="";
    private boolean authority;

    public ObjectiveFeedBack() {
    }

    public ObjectiveFeedBack(int one, int two, int three, int four, int five, String userID, boolean authority) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.userID = userID;
        this.authority = authority;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}
