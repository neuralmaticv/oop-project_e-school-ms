package com.college.oop_project.app;

import com.college.oop_project.view.ConsoleView;
import com.college.oop_project.view.GUIView;
import com.college.oop_project.view.View;

public class Main {
    public static void main(String[] args) {
        View GUIView = new GUIView();
        View CMDView = new ConsoleView();

        StudentManagementSystem smsapp = new StudentManagementSystem(CMDView);
        smsapp.run();
    }
}
