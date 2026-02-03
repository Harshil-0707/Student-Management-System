package com.harshil.student.service;

import java.util.Scanner;
import model.Student;
import java.util.ArrayList;

public class StudentService{

    private final ArrayList<Student> studentDetails = new ArrayList<>();

    public void add(Scanner sc){
        
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        String email = null;
        while(true){
            System.out.print("Enter email: ");
            email = sc.nextLine();
            for(Student s : studentDetails){
                if(email.equals(s.getEmail())){
                    System.out.println("Email already exists.");
                    email = null;
                    break;
                }
            }
            if(null != email) break;
        }
        while(true){
            System.out.print("Enter your age: ");
            if(!sc.hasNextInt()){
                System.out.println("Age should be a number!!!");
                sc.nextLine();
                continue;
            }
            int age = nextInt();
            if(age <= 0){
                System.out.println("Age cannot be negative!!!");
                continue;
            }else if(age == 0){
                System.out.println("Age cannot be zero!!");
            }else if(age < 16){
                System.out.println("Age should be more than 15!!!");
            }else{
                break;
            }
        }
        System.out.print("Enter your course: ");
        String course = sc.nextLine();
        Student s = new Student.builder().name(name).email(email).age(age).course(course);
        studentDetails.add(s);
        System.out.println("Student added successfully!");
        System.out.println("Student ID: " + s.getId());
    }

    public void view(){
        System.out.println("---------------------------------");
    }

    public void update(){}

    public void delete();

    public void search();
}