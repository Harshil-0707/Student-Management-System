package com.harshil.student.service;

import java.util.Scanner;
import java.util.ArrayList;
import com.harshil.student.model.Student;
import com.harshil.student.dao.StudentDAO;

public class StudentService{

    private final ArrayList<Student> studentDetails = new ArrayList<>();

    private StudentDAO sDao = null;

    public StudentService(StudentDAO sDao){
        this.sDao = sDao;
    }

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
        int age;
        while(true){
            System.out.print("Enter your age: ");
            if(!sc.hasNextInt()){
                System.out.println("Age should be a natural number!!!");
                sc.nextLine();
                continue;
            }
            age = sc.nextInt();
            sc.nextLine();
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
        Student s = new Student(name , email , age , course);
        try{
            sDao.save(s);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Student added successfully!");
        System.out.println("Student ID: " + s.getId());
    }

    public void view(){
        String lines = "---------------------------------";
        System.out.println(lines);
        System.out.println("ID Name Email Age Course");
        System.out.println(lines);

        ArrayList<Student> allStudents = null;
        try{
            allStudents = sDao.view();
        }catch(Exception e){
            // e.printStackTrace();
            System.out.println("Error");
        }

        for(Student s : allStudents){
            System.out.println(s.getId() + s.getName() + s.getEmail() + s.getAge() + s.getCourse());
        }
        System.out.println(lines);
        System.out.println("Total Students: " + studentDetails.size());
    }

    public void update(Scanner sc){
        System.out.print("Enter student ID to update: ");
        int id;
        while(true){
            if(!sc.hasNextInt()){
                System.out.println("Id must be a natural number!!");
            }
            id = sc.nextInt();
            if(id < 0){
                System.out.println("Id must be a natural number!!");
            }else{
                break;
            }
            
        }

        Student s = null;
        for(Student student : studentDetails){
            if(id == student.getId()){
                s = student;
                break;
            }else {
                s = null;
            }
        }
        if(null == s){
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter your new name: ");
        String name = sc.nextLine();
        System.out.print("Enter your new email: ");
        String email = sc.nextLine();

        int age;
        while(true){
            System.out.print("Enter new age: ");
            if(!sc.hasNextInt()){
                System.out.println("Age should be a natural number!!!");
                sc.nextLine();
                continue;
            }
            age = sc.nextInt();
            sc.nextLine();
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
        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        s.setName(name);
        s.setEmail(email);
        s.setAge(age);
        s.setCourse(course);
        
    }

    public void delete(Scanner sc){
        System.out.println("Enter student ID to delete: ");
        int id;
        while(true){
            if(!sc.hasNextInt()){
                System.out.println("Id must be a natural number!!");
            }
            id = sc.nextInt();
            if(id < 0){
                System.out.println("Id must be a natural number!!");
            }else{
                break;
            }
            
        }
        boolean found = false;
        for(Student s : studentDetails){
            if(id == s.getId()){
                studentDetails.remove(s);
                System.out.println("Student deleted successfully!");
                found = true;
                break;
            }
        }
        if(!found) System.out.println("Student not found.");
    }

    public void search(Scanner sc){
        System.out.print("Enter email to search: ");
        String email = sc.nextLine();

        boolean found = false;

        for(Student s : studentDetails){
            if(email.equals(s.getEmail())){
                System.out.println("Student Details:");
                System.out.println("-----------------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Email: " + s.getEmail());
                System.out.println("Age: " + s.getAge());
                System.out.println("Course: " + s.getCourse());
                found = true;
                break;
            }
        }
        if(!found) System.out.println("No student found with this email.");
    }
}