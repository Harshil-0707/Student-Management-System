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
            if(null == sDao.searchByEmail(email)) break;
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
        int id = sDao.save(s);
        System.out.println("Student added successfully!");
        System.out.println("Student ID: " + id);
    }

    public void view(){
        String lines = "---------------------------------";
        System.out.println(lines);
        System.out.println("ID Name Email Age Course");
        System.out.println(lines);

        ArrayList<Student> allStudents = sDao.view();
        for(Student s : allStudents){
            System.out.println(s.getId() + s.getName() + s.getEmail() + s.getAge() + s.getCourse());
        }
        System.out.println(lines);
        System.out.println("Total Students: " + allStudents.size());
    }

    public void update(Scanner sc){
        // validate id
        int id;
        while(true){
            System.out.print("Enter student ID to update: ");
            if(!sc.hasNextInt()){
                System.out.println("Id must be a natural number!!!");
                sc.nextLine();
                continue;
            }
            id = sc.nextInt();
            sc.nextLine();
            if(id < 0){
                System.out.println("Id must be positive!!!");
                sc.nextLine();
                continue;
            }
            break;
            
        }

        // Search if student with entered id exists.
        Student s = sDao.searchById(id);
        if(null == s){
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter your new name: ");
        String name = sc.nextLine();

        String email = null;
        while(true){
            System.out.print("Enter your new email: ");
            email = sc.nextLine();
            if(null == sDao.searchByEmail(email)) break;
        }

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

        sDao.update(s);
        
    }

    public void delete(Scanner sc){
        int id;
        while(true){
            System.out.print("Enter student ID to delete: ");
            if(!sc.hasNextInt()){
                System.out.println("Id must be a natural number!!!");
                sc.nextLine();
                continue;
            }
            id = sc.nextInt();
            if(id < 0){
                System.out.println("Id must be positive!!!");
                sc.nextLine();
                continue;
            }
            break;
            
        }

        if(sDao.searchById(id) == null){
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        if(sDao.delete(id)){
            System.out.println("Student deleted successfully!");
            return;
        }
    }

    public void search(Scanner sc){
        System.out.print("Enter email to search: ");
        String email = sc.nextLine();

        Student s = sDao.searchByEmail(email);

        if(null == s) System.out.println("No student found with this email.");

        System.out.println("Student Details:");
        System.out.println("-----------------------");
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Email: " + s.getEmail());
        System.out.println("Age: " + s.getAge());
        System.out.println("Course: " + s.getCourse());
    }
}