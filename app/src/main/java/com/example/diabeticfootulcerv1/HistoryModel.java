package com.example.diabeticfootulcerv1;

public class HistoryModel {
    String FeetID;
    String Condition;
    String Datetime;
    String Accuracy;

    String Image;



    public HistoryModel(String condition, String accuracy, String feetID, String image, String datetime) {
        Condition = condition;
        Datetime = datetime;
        Accuracy = accuracy;
        Image = image;
        FeetID = feetID;

    }

    public String getFeetID() {
        return FeetID;
    }

    public String getCondition() {
        return Condition;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public String getImage() {
        return Image;
    }

    public String getDatetime() {
        return Datetime;
    }
}

