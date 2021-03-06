package com.capstone.kumar.pupil.studentFeedBack;

/**
 * Created by kumar on 3/18/2018.
 */

public class StudentFeedModel {
    private static final String TAG = "StudentFeedModel";


    private String user_ID = "";
    private String company_Name = "";
    private String technical_feedBack = "";
    private String hr_feedBack = "";
    private String extra_feedBack = "";
    private String company_ID = "";
    private String authority = "";

    public StudentFeedModel() {
    }

    public StudentFeedModel(String user_ID, String company_Name, String technical_feedBack, String hr_feedBack, String extra_feedBack, String company_ID, String authority) {
        this.user_ID = user_ID;
        this.company_Name = company_Name;
        this.technical_feedBack = technical_feedBack;
        this.hr_feedBack = hr_feedBack;
        this.extra_feedBack = extra_feedBack;
        this.company_ID = company_ID;
        this.authority = authority;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public String getTechnical_feedBack() {
        return technical_feedBack;
    }

    public void setTechnical_feedBack(String technical_feedBack) {
        this.technical_feedBack = technical_feedBack;
    }

    public String getHr_feedBack() {
        return hr_feedBack;
    }

    public void setHr_feedBack(String hr_feedBack) {
        this.hr_feedBack = hr_feedBack;
    }

    public String getExtra_feedBack() {
        return extra_feedBack;
    }

    public void setExtra_feedBack(String extra_feedBack) {
        this.extra_feedBack = extra_feedBack;
    }

    public String getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(String company_ID) {
        this.company_ID = company_ID;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
