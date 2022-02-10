package com.college.oop_project.model;

import java.util.ArrayList;

public class AccessData {
    private final int userID;
    private final String userName;
    private final String userMail;
    private String userPassword;
    public static ArrayList<AccessData> allAccessData = new ArrayList<>();

    public AccessData(int userID, String userName, String userMail, String userPassword) throws Exception {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;

        if (!exists(this)) {
            allAccessData.add(this);
        } else {
            throw new Exception("Postoji korisnicki nalog sa zadatim podacima!");
        }
    }

    private boolean exists(AccessData data) {
        if (allAccessData != null) {
            for (AccessData ad : allAccessData) {
                if (ad.userID != data.userID && ad.userName.equals(data.userName) && ad.userMail.equals(data.userMail)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setNewUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public static AccessData getUser(int userID) {
        for (AccessData ad : allAccessData) {
            if (ad.userID == userID) {
                return ad;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserID:").append(this.userID).append("\n");
        sb.append("UserName:").append(this.userName).append("\n");
        sb.append("UserMail:").append(this.userMail).append("\n");
        sb.append("UserPass:").append(this.userPassword).append("\n");

        return sb.toString();
    }
}
