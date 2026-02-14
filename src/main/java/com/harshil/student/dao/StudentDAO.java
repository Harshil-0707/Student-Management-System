package com.harshil.student.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import com.harshil.student.model.Student;

public class StudentDAO{

    private Connection con;

    public StudentDAO(Connection con){
        this.con = con;
    }

    public void save(Student student) throws SQLException {

        String sql = "INSERT INTO student(id,name,email,age,course) VALUES (?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,student.getId());
        ps.setString(2,student.getName());
        ps.setString(3,student.getEmail());
        ps.setInt(4,student.getAge());
        ps.setString(5,student.getCourse());

        ps.executeUpdate();

    }
    
    public void update(Student student) throws SQLException {
        String sql = "UPDATE student SET id = ? , name = ? email = ?,age = ?,course = ? WHERE id = ?" ;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,student.getId());
        ps.setString(2,student.getName());
        ps.setString(3,student.getEmail());
        ps.setInt(4,student.getAge());
        ps.setString(5,student.getCourse());

        ps.executeUpdate();

    }

    public void delete(Student student) throws SQLException {
        
        String sql = "DELETE FROM student WHERE id = ?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,student.getId());
        ps.executeUpdate();
    }

    public ArrayList<Student> view() throws SQLException {
        String sql = "SELECT * FROM student";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Student> allStudents = new ArrayList<>();

        Student s = null;

        while(rs.next()){
            String name = rs.getString("name");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            String course = rs.getString("course");
            s = new Student(name,email,age,course);
            allStudents.add(s);
        }

        return allStudents;
    }

    public Student search(Student student,String email) throws SQLException {
        String sql = "SELECT * FROM student WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();

        Student s = null;

        while(rs.next()){
            String name = rs.getString("name");
            String newEmail = rs.getString("email");
            int age = rs.getInt("age");
            String course = rs.getString("course");
            s = new Student(name,newEmail,age,course);
        }
        return s;
    }

}