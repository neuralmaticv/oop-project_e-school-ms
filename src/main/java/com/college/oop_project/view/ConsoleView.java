package com.college.oop_project.view;

import com.college.oop_project.model.*;

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
        System.out.println(Question.questions);

        System.out.println("\n\n");

        System.out.println("-------------| PREDMETI |-------------");
        System.out.println(Subject.subjects);

        System.out.println("\n\n");

        System.out.println("-------------| SKOLE |-------------");
        System.out.println(School.schools);

        System.out.println("\n\n");

        System.out.println("-------------| PRISTUPNI PODACI |-------------");
        System.out.println(AccessData.allAccessData);
        System.out.println(AccessData.allAccessData.size());

        System.out.println("-------------| PROFESORI |-------------");
        System.out.println(Professor.allProfessors);
        System.out.println(Professor.allProfessors.size());

        System.out.println("-------------| UCENICI |-------------");
        System.out.println(Student.allStudents);
        System.out.println(Student.allStudents.size());


        System.out.println("-------------| OCJENE |-------------");
        System.out.println(Grade.grades);

        System.out.println("=====================================================");
        System.out.println("\t\t\tSchool Management System\t\t\t");
        System.out.println("=====================================================");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter number != -1 to continue: ");
            int controller = scanner.nextInt();
            if (controller == -1)
                break;

            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            Student s1 = Student.getStudent(username, password);
            System.out.println(s1);

            if (s1 == null) {
                Professor p1 = Professor.getProfessor(username, password);
                System.out.println(p1);
            }
        }
    }
}
