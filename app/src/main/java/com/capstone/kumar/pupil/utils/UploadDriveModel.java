package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 3/24/2018.
 */

public class UploadDriveModel {
    private static final String TAG = "UploadDriveModel";

    private String company_name="";
    private String drive_Date = "";
    private String salary_Package = "";
    private String standing_Area = "";
    private String job_Profile = "";
    private String skill_Required = "";
    private String bond_Detail = "";
    private String about_Company = "";
    private String joining_Date = "";
    private String jobLocation = "";

    public UploadDriveModel() {
    }

    public UploadDriveModel(String company_name, String drive_Date, String salary_Package,
                            String standing_Area, String job_Profile, String skill_Required,
                            String bond_Detail, String about_Company, String joining_Date
                            ,String jobLocation) {
        this.company_name = company_name;
        this.drive_Date = drive_Date;
        this.salary_Package = salary_Package;
        this.standing_Area = standing_Area;
        this.job_Profile = job_Profile;
        this.skill_Required = skill_Required;
        this.bond_Detail = bond_Detail;
        this.about_Company = about_Company;
        this.joining_Date = joining_Date;
        this.jobLocation = jobLocation;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDrive_Date() {
        return drive_Date;
    }

    public void setDrive_Date(String drive_Date) {
        this.drive_Date = drive_Date;
    }

    public String getSalary_Package() {
        return salary_Package;
    }

    public void setSalary_Package(String salary_Package) {
        this.salary_Package = salary_Package;
    }

    public String getStanding_Area() {
        return standing_Area;
    }

    public void setStanding_Area(String standing_Area) {
        this.standing_Area = standing_Area;
    }

    public String getJob_Profile() {
        return job_Profile;
    }

    public void setJob_Profile(String job_Profile) {
        this.job_Profile = job_Profile;
    }

    public String getSkill_Required() {
        return skill_Required;
    }

    public void setSkill_Required(String skill_Required) {
        this.skill_Required = skill_Required;
    }

    public String getBond_Detail() {
        return bond_Detail;
    }

    public void setBond_Detail(String bond_Detail) {
        this.bond_Detail = bond_Detail;
    }

    public String getAbout_Company() {
        return about_Company;
    }

    public void setAbout_Company(String about_Company) {
        this.about_Company = about_Company;
    }

    public String getJoining_Date() {
        return joining_Date;
    }

    public void setJoining_Date(String joining_Date) {
        this.joining_Date = joining_Date;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }
}
