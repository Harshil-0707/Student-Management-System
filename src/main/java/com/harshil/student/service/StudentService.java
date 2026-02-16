package com.harshil.student.service;

import java.util.Scanner;
import java.util.ArrayList;
import com.harshil.student.model.Student;
import com.harshil.student.dao.StudentDAO;

public class StudentService{

    private StudentDAO sDao = null;

    public StudentService(StudentDAO sDao){
        this.sDao = sDao;
    }

    private int getAge(Scanner sc){
        int age = 0;
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
                System.out.println("Age must be greater than 0!");
                continue;
            }
            if(age < 16){
                System.out.println("Age should be more than 15!!!");
                continue;
            }
            break;
        }
        return age;
    }

    public void add(Scanner sc){
        
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        String email = null;
        while(true){
            System.out.print("Enter email: ");
            email = sc.nextLine();
            if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                System.out.println("Invalid Email address!!!");
                continue;
            }
            if(null == sDao.searchByEmail(email)) break;
            else System.out.println("Email already exists!!!");
        }
        int age = getAge(sc);
        
        System.out.print("Enter your course: ");
        String course = sc.nextLine();
        
        Student s = new Student(name , email , age , course);
        
        int id = sDao.save(s);
        System.out.println("Student added successfully!");
        System.out.println("Student ID: " + id);
    }

    public void view() {

        ArrayList<Student> allStudents = sDao.view();

        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        // Step 1: Default column widths (based on header length)
        int idWidth = "ID".length();
        int nameWidth = "Name".length();
        int emailWidth = "Email".length();
        int ageWidth = "Age".length();
        int courseWidth = "Course".length();

        // Step 2: Find maximum width for each column
        for (Student s : allStudents) {
            idWidth = Math.max(idWidth, String.valueOf(s.getId()).length());
            nameWidth = Math.max(nameWidth, s.getName().length());
            emailWidth = Math.max(emailWidth, s.getEmail().length());
            ageWidth = Math.max(ageWidth, String.valueOf(s.getAge()).length());
            courseWidth = Math.max(courseWidth, s.getCourse().length());
        }

        // Step 3: Create format string dynamically
        String format = "%-" + (idWidth + 2) + "s"
                    + "%-" + (nameWidth + 2) + "s"
                    + "%-" + (emailWidth + 2) + "s"
                    + "%-" + (ageWidth + 2) + "s"
                    + "%-" + (courseWidth + 2) + "s\n";

        int totalWidth = idWidth + nameWidth + emailWidth + ageWidth + courseWidth + 10;
        String line = "-".repeat(totalWidth);

        System.out.println(line);
        System.out.printf(format, "ID", "Name", "Email", "Age", "Course");
        System.out.println(line);

        // Step 4: Print Data
        for (Student s : allStudents) {
            System.out.printf(format,
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getAge(),
                    s.getCourse());
        }

        System.out.println(line);
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
            if(id < 1){
                System.out.println("Id must be positive!!!");
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

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        String email = null;
        while(true){
            System.out.print("Enter your email: ");
            email = sc.nextLine();
            if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                System.out.println("Invalid Email address!!!");
                continue;
            }
            Student existing =  sDao.searchByEmail(email);
            if(existing == null || existing.getId() == id) break;
            else System.out.println("Email already exists!!!");
        }

        int age = getAge(sc);
       
        System.out.print("Enter your course: ");
        String course = sc.nextLine();

        s.setName(name);
        s.setEmail(email);
        s.setAge(age);
        s.setCourse(course);

        if(sDao.update(s)) System.out.println("Details updated successfully!!!");
    
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
            sc.nextLine();

            if(id < 1){
                System.out.println("Id must be positive!!!");
                continue;
            }

            break;
        }

        if(sDao.delete(id)){
            System.out.println("Student detail deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }


    public void search(Scanner sc){
        System.out.print("Enter email to search: ");
        String email = sc.nextLine();
        if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            System.out.println("Invalid Email address!!!");
            return;
        }

        Student s = sDao.searchByEmail(email);

        if(s == null){
            System.out.println("No student found with this email.");
            return;
        }

        System.out.println("Student Details:");
        System.out.println("-----------------------");
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Email: " + s.getEmail());
        System.out.println("Age: " + s.getAge());
        System.out.println("Course: " + s.getCourse());
    }
}