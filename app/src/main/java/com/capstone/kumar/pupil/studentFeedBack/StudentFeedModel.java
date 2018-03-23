package com.capstone.kumar.pupil.studentFeedBack;

/**
 * Created by kumar on 3/18/2018.
 */

public class StudentFeedModel {
    private static final String TAG = "StudentFeedModel";


    private String user_Name = "";
    private String reg_No = "";
    private String drive_Name = "";
    private String feedback = "";
    private boolean authority;

    public StudentFeedModel() {
    }

    public StudentFeedModel(String user_Name, String reg_No, String drive_Name, String feedback, boolean authority) {
        this.user_Name = user_Name;
        this.reg_No = reg_No;
        this.drive_Name = drive_Name;
        this.feedback = feedback;
        this.authority = authority;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getReg_No() {
        return reg_No;
    }

    public void setReg_No(String reg_No) {
        this.reg_No = reg_No;
    }

    public String getDrive_Name() {
        return drive_Name;
    }

    public void setDrive_Name(String drive_Name) {
        this.drive_Name = drive_Name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "StudentFeedModel{" +
                "user_Name='" + user_Name + '\'' +
                ", reg_No='" + reg_No + '\'' +
                ", drive_Name='" + drive_Name + '\'' +
                ", feedback='" + feedback + '\'' +
                ", authority=" + authority +
                '}';
    }
}
