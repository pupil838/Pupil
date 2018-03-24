package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 3/24/2018.
 */

public class UploadDriveModel {
    private static final String TAG = "UploadDriveModel";

    private String company_name="";

    public UploadDriveModel() {
    }

    public UploadDriveModel(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "UploadDriveModel{" +
                "company_name='" + company_name + '\'' +
                '}';
    }

}
