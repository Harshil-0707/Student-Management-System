package com.harshil.student.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.harshil.student.model.Student;

public class StudentDAO{

    private Connection con;

    public StudentDAO(Connection con){
        this.con = con;
    }

    public int save(Student student) {

        String sql = "INSERT INTO student(name,email,age,course) VALUES (?,?,?,?)";
        int generatedId = 0;

        try (
                PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ) {
            ps.setString(1,student.getName());
            ps.setString(2,student.getEmail());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getCourse());
            
            int rows = ps.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    student.setId(generatedId);
                    System.out.println("Inside save " + generatedId);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return generatedId;
    }

    
    public void update(Student student) {
       
        String sql = "UPDATE student SET name = ?, email = ?,age = ?,course = ? WHERE id = ?" ;

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,student.getName());
            ps.setString(2,student.getEmail());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getCourse());
            ps.setInt(5,student.getId());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean delete(int id) {
        
        String sql = "DELETE FROM student WHERE id = ?";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Student> view(){
        String sql = "SELECT * FROM student";
        ArrayList<Student> allStudents = new ArrayList<>();
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            Student s = null;
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String course = rs.getString("course");
                s = new Student(id , name , email , age , course);
                allStudents.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return allStudents;
    }

    public Student searchByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email = ?";
        Student s = null;
       
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String course = rs.getString("course");
                s = new Student(id , name , email , age , course);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return s;
    }

    public Student searchById(int id){
        String sql = "SELECT * FROM student WHERE id = ?";
        Student s = null;
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String course = rs.getString("course");
                s = new Student(id,name,email,age,course);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }

}