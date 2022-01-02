package com.college.oop_project.view;

import com.college.oop_project.model.Question;
import com.college.oop_project.model.Subject;

import java.util.Scanner;

public class ConsoleView implements View {
    @Override
    public void drawView() {
        System.out.println("=====================================================");
        System.out.println("\t\t\tSchool Management System\t\t\t");
        System.out.println("=====================================================");
        /*
        ****************************************************************
        * SAMO ZA TEST
        ****************************************************************
        * */

        Scanner scanner = new Scanner(System.in);
        /*
        while (true) {
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            int controller = scanner.nextInt();
            if (controller == -1)
                break;
        }
        */


        Question.getQuestionsFromDB();
        System.out.println(Question.questions);
        Question q1 = Question.getQuestion(0);
        System.out.println(q1);
        q1.setAnswer("Tacno");
        System.out.println(Question.questions);



        Subject.getSubjectsFromDB();
        Subject s1 = new Subject("Matematika", 6);
        Subject s2 = new Subject("Matematika", 8);
        Subject s3 = new Subject("Informatika", 8);

        System.out.println(Subject.subjects);
    }
}
