package com.capstone.kumar.pupil.utils;

/**
 * Created by kumar on 4/11/2018.
 */

public class TrendingQuestionModel {

    private static final String TAG = "TrendingQuestionModel";

    private String companyName = "";
    private String TrendingQuestion = "";
    private String TrendingAnswer = "";
    private String companyKey = "";

    public TrendingQuestionModel() {
    }

    public TrendingQuestionModel(String companyName, String trendingQuestion, String trendingAnswer, String companyKey) {
        this.companyName = companyName;
        TrendingQuestion = trendingQuestion;
        TrendingAnswer = trendingAnswer;
        this.companyKey = companyKey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTrendingQuestion() {
        return TrendingQuestion;
    }

    public void setTrendingQuestion(String trendingQuestion) {
        TrendingQuestion = trendingQuestion;
    }

    public String getTrendingAnswer() {
        return TrendingAnswer;
    }

    public void setTrendingAnswer(String trendingAnswer) {
        TrendingAnswer = trendingAnswer;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }
}
