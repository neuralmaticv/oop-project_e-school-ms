package com.college.oop_project.app;

import com.college.oop_project.sql.DBUtils;
import com.college.oop_project.view.View;

public class StudentManagementSystem {
    private final View view;

    public StudentManagementSystem(View view) {
        this.view = view;
    }

    public void run() {
        DBUtils.getDataFromDB();
        view.drawView();
    }
}
