package com.harshil.student.model;

public class Student{
    private int id;
    private String name;
    private String email;
    private int age;
    private String course;

    public Student(String name , String email , int age , String course){
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public Student(int id,String name , String email , int age , String course){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }
    
    // Setters

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setCourse(String course){
        this.course = course;
    }

    // Getters

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getAge(){
        return this.age;
    }

    public String getCourse(){
        return this.course;
    }

}