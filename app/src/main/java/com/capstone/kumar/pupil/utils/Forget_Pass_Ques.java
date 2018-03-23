package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 3/11/2018.
 */

public class Forget_Pass_Ques {
    private static final String TAG = "Forget_Pass_Ques";

    private String user_ID = "";
    private String first_ques = "";
    private String second_ques = "";
    private String first_ans = "";
    private String second_ans = "";

    public Forget_Pass_Ques() {
    }

    public Forget_Pass_Ques(String user_ID, String first_ques, String second_ques, String first_ans, String second_ans) {
        this.user_ID = user_ID;
        this.first_ques = first_ques;
        this.second_ques = second_ques;
        this.first_ans = first_ans;
        this.second_ans = second_ans;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getFirst_ques() {
        return first_ques;
    }

    public void setFirst_ques(String first_ques) {
        this.first_ques = first_ques;
    }

    public String getSecond_ques() {
        return second_ques;
    }

    public void setSecond_ques(String second_ques) {
        this.second_ques = second_ques;
    }

    public String getFirst_ans() {
        return first_ans;
    }

    public void setFirst_ans(String first_ans) {
        this.first_ans = first_ans;
    }

    public String getSecond_ans() {
        return second_ans;
    }

    public void setSecond_ans(String second_ans) {
        this.second_ans = second_ans;
    }

    @Override
    public String toString() {
        return "Forget_Pass_Ques{" +
                "user_ID='" + user_ID + '\'' +
                ", first_ques='" + first_ques + '\'' +
                ", second_ques='" + second_ques + '\'' +
                ", first_ans='" + first_ans + '\'' +
                ", second_ans='" + second_ans + '\'' +
                '}';
    }
}
