package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.Scanner;

public class AccessData {
    private final int userID;
    private final String userName;
    private final String userMail;
    private String userPassword;
    public static ArrayList<AccessData> allAccessData = new ArrayList<>();

    public static int checkUser(String username, String userpass) {
        for (AccessData ad : allAccessData) {
            if (ad.userName.equals(username) && ad.userPassword.equals(userpass)) {
                return ad.userID - 1;
            }
        }

        return -1;
    }

    public AccessData(int userID, String userName, String userMail, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;

        if (!exists(this)) {
            allAccessData.add(this);
        } else {
            // print msg, exception???
            System.out.println("Postoji osoba sa ovakvim podacima");
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

    public String getUserMail() {
        return userMail;
    }

    public static AccessData getUser(int userID) {
        return allAccessData.get(userID);
    }

    public boolean setNewPassword() {
        // Only for testing
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter current password: ");
        String userInput = scanner.next();

        if (userInput.equals(userPassword)) {
            System.out.println("Enter new password: ");
            String newPassword = scanner.next();
            System.out.println("Re-enter password: ");
            String reePassword = scanner.next();

            if (newPassword.equals(reePassword)) {
                this.userPassword = reePassword;
                System.out.println("Password has successfully changed");
                // TODO:
                // send mail
                return true;
            } else {
                System.out.println("Passwords doesn't match!");
                return false;
            }
        } else {
            System.out.println("Try again");
            return false;
        }
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
