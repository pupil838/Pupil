package com.capstone.kumar.pupil.studentFeedBack;

/**
 * Created by kumar on 3/18/2018.
 */

public class StudentFeedModel {
    private static final String TAG = "StudentFeedModel";


    private String user_ID = "";
    private String technical_feedBack = "";
    private String hr_feedBack = "";
    private String extra_feedBack = "";
    private String company_ID = "";
    private boolean authority;

    public StudentFeedModel() {
    }

    public StudentFeedModel(String user_ID, String technical_feedBack, String hr_feedBack,
                            String extra_feedBack, String company_ID, boolean authority) {
        this.user_ID = user_ID;
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

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
}
