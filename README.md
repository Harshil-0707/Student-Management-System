# 📘 Student Management System (Console + JDBC)

A console-based **Student Management System** built using **Java, JDBC, Maven, and MySQL**.  
This project demonstrates layered architecture, database integration, and clean code structure.

---

## 🚀 Features

- ➕ Add Student  
- 📋 View All Students  
- ✏️ Update Student  
- 🗑️ Delete Student  
- 🔍 Search Student by Email  
- ⚠️ Proper exception handling  
- 🗄️ JDBC-based database integration  
- 📦 Maven dependency management  

---

## 🛠️ Tech Stack

- Java 17
- JDBC
- MySQL Server
- Maven
- VS Code

---

## 📂 Project Structure

```
student-management-system/
│
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── harshil/
                    └── student/
                        ├── app/
                        │     └── App.java
                        ├── model/
                        │     └── Student.java
                        ├── dao/
                        │     └── StudentDAO.java
                        ├── service/
                        │     └── StudentService.java
                        └── util/
                             └── DBConnection.java
                         
```

---

## 🧱 Architecture

This project follows a layered architecture:

- **App Layer** → Entry point (`main()` method)
- **Service Layer** → Business logic
- **DAO Layer** → Database operations
- **Model Layer** → Entity classes
- **Utility Layer** → Database connection management

---

## 🗄️ Database Setup

### 1️⃣ Create Database

```sql
CREATE DATABASE student_db;
USE student_db;
```

### 2️⃣ Create Table

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100)
);
```

---

## ⚙️ Configuration

Update your database credentials inside:

```
DBConnection.java
```

Example:

```java
private static final String URL = "jdbc:mysql://localhost:3306/student_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## ▶️ How to Run

### Step 1: Compile the project

```bash
mvn clean compile
```

### Step 2: Run the application

```bash
mvn exec:java
```

OR

```bash
mvn exec:java -Dexec.mainClass="com.harshil.student.app.App"
```

---

## 🧠 Concepts Demonstrated

- JDBC connection handling
- PreparedStatement usage
- executeQuery() vs executeUpdate()
- ResultSet to object mapping
- Exception handling
- Try-with-resources
- Maven dependency management
- Clean layered architecture

---

## 🔐 Important JDBC Methods Used

- `DriverManager.getConnection()`
- `Connection.prepareStatement()`
- `PreparedStatement.executeUpdate()`
- `PreparedStatement.executeQuery()`
- `ResultSet.next()`
- `Connection.setAutoCommit()`

---

## 📌 Future Improvements

- Input validation improvements
- Logging integration
- Unit testing (JUnit)
- REST API version using Spring Boot
- Connection pooling (HikariCP)
- Docker containerization

---

## 👨‍💻 Author

**Harshil Chaurasiya**  
B.Sc IT Student | Java Backend Developer  

---

## 📄 License

This project is created for educational purposes.
