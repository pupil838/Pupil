package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 4/7/2018.
 */

public class AmcatMarksUpload {
    private String regNum = "";
    private String english = "";
    private String quant = "";
    private String logical = "";
    private String ampi = "";
    private String info = "";
    private String compProg = "";
    private String compSci = "";

    public AmcatMarksUpload() {
    }

    public AmcatMarksUpload(String regNum, String english, String quant, String logical,
                            String ampi, String info, String compProg, String compSci) {
        this.regNum = regNum;
        this.english = english;
        this.quant = quant;
        this.logical = logical;
        this.ampi = ampi;
        this.info = info;
        this.compProg = compProg;
        this.compSci = compSci;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getLogical() {
        return logical;
    }

    public void setLogical(String logical) {
        this.logical = logical;
    }

    public String getAmpi() {
        return ampi;
    }

    public void setAmpi(String ampi) {
        this.ampi = ampi;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCompProg() {
        return compProg;
    }

    public void setCompProg(String compProg) {
        this.compProg = compProg;
    }

    public String getCompSci() {
        return compSci;
    }

    public void setCompSci(String compSci) {
        this.compSci = compSci;
    }
}
