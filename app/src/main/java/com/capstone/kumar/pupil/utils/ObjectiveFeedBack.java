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
    private int feedbacknumber=0;
    private int total;
    private String userID="";
    private String companyId = "";
    private String company_Name = "";
    private boolean authority;

    public ObjectiveFeedBack() {
    }

    public ObjectiveFeedBack(int one, int two, int three, int four, int five, int feedbacknumber,
                             int total, String userID, String companyId, String company_Name, boolean authority) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.feedbacknumber = feedbacknumber;
        this.total = total;
        this.userID = userID;
        this.companyId = companyId;
        this.company_Name = company_Name;
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

    public int getFeedbacknumber() {
        return feedbacknumber;
    }

    public void setFeedbacknumber(int feedbacknumber) {
        this.feedbacknumber = feedbacknumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}
