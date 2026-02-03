package com.harshil.student.model;
import java.util.UUID;

public class Student{
    private String id;
    private String name;
    private String email;
    private int age;
    private String course;

    private Student(Builder builder){
        this.id = UUID.randomUUID().toString().subString(0,6);
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.course = builder.course;
    }

    public static class Builder{
        private String name;
        private String email;
        private int age;
        private String course;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder course(String course){
            this.course = course;
            return this;
        }

    }

    // Getters

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getAge(int age){
        return this.age;
    }

    public String getCourse(){
        return this.course;
    }

}