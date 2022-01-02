package com.college.oop_project.model;

import java.util.ArrayList;
import java.util.Scanner;

public class AccessData {
    private String userName;
    private String userMail;
    private String userPassword;
    // MD5 kod, korisnicko_ime123 ???

    public static ArrayList<AccessData> allAccessData = new ArrayList<>();

    public AccessData(String userName, String userMail, String userPassword) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;

        if (!exists(this)) {
            allAccessData.add(this);
        } else {
            // print msg, exception???
        }
    }

    private boolean exists(AccessData data) {
        if (allAccessData != null) {
            for (AccessData ad : allAccessData) {
                if (ad.userName.equals(data.userName) && ad.userMail.equals(data.userMail)) {
                    return true;
                }
            }
        }

        return false;
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
}
