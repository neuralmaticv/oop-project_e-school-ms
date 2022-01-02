package com.college.oop_project.view;

import com.college.oop_project.model.AccessData;
import com.college.oop_project.model.Question;
import com.college.oop_project.model.School;
import com.college.oop_project.model.Subject;

import java.util.Scanner;

public class ConsoleView implements View {
    @Override
    public void drawView() {
        /*
        ****************************************************************
        * SAMO ZA TEST
        ****************************************************************
        * */

        System.out.println("-------------| PITANJA |-------------");
        Question.getQuestionsFromDB();
        System.out.println(Question.questions);

        System.out.println("\n\n");

        System.out.println("-------------| PREDMETI |-------------");
        Subject.getSubjectsFromDB();
        System.out.println(Subject.subjects);

        System.out.println("\n\n");

        System.out.println("-------------| SKOLE |-------------");
        School.getSchoolsFromDB();
        System.out.println(School.schools);

        System.out.println("\n\n");

        System.out.println("-------------| PRISTUPNI PODACI |-------------");
        AccessData.getAccessDataFromDB();
        System.out.println(AccessData.allAccessData);


        System.out.println("=====================================================");
        System.out.println("\t\t\tSchool Management System\t\t\t");
        System.out.println("=====================================================");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            int userID = AccessData.checkUser(username, password);

            if (userID != -1) {
                AccessData a1 = AccessData.getUser(userID);
                System.out.println(a1);
            } else {
                System.out.println("PRISTUPNI PODACI NISU ISPRAVNI ILI KORISNIK NE POSTOJI");
            }

            int controller = scanner.nextInt();
            if (controller == -1)
                break;
        }
    }
}
