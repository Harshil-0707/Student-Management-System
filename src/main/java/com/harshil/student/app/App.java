package com.harshil.student.app;

import java.util.Scanner;
import service.StudentService;

public class App{

    public static void main(String[] args){

        final Scanner sc = new Scanner(System.in);
        StudentService ss = new StudentService();

        boolean running = true;

        while(running){
            System.out.println("=================================");
            System.out.println("\tSTUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by Email");
            System.out.println("6. Exit");
            System.out.print("Enter your choice:");

            if(!sc.hasNextInt()){
                System.out.println("Enter a number!!!");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    ss.add(sc);
                    break;
                case 2:
                    ss.view(sc);
                    break;
                case 3:
                    ss.update(sc);
                    break;
                case 4:
                    ss.delete(sc);
                    break;
                case 5:
                    ss.search(sc);
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Student Management System!");
                    System.out.println("Application closed successfully.");
                    break;
                default: 
                    System.out.println("Invalid choice!!!");
                    continue;
            }

        }
        sc.close();

    }

}