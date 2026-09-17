# Student Management System

A desktop-based Student Management System developed using **Java Swing and AWT**.  
The application provides a professional dashboard for managing student records, academic performance, attendance, and reports.

## 📌 Project Overview

The Student Management System is designed to simplify the management of student information through a user-friendly graphical interface.

The system allows users to:

- Add new students
- Update student information
- Delete student records
- Search students
- View complete student profiles
- Manage CGPA and academic performance
- Track attendance
- View dashboard statistics
- Generate student reports
- Export reports to CSV
- Store student data locally

## ✨ Features

### 1. Dashboard

The dashboard provides an overview of the student database.

It displays:

- Total Students
- CSE Students
- AIML Students
- Average CGPA
- Average Attendance
- Students by Department
- Students by Semester
- System Status

### 2. Student Management

The Student Management module allows users to:

- Add students
- Edit student details
- Delete students
- Search students
- View student profiles

Student information includes:

- Student ID
- Name
- Course
- Semester
- Email
- Phone
- CGPA
- Attendance

### 3. Academic Performance

The Academic Performance module provides:

- CGPA tracking
- Grade calculation
- Performance status
- CGPA updating

Grades are calculated automatically according to CGPA.

### 4. Attendance Management

The Attendance module allows users to:

- Record total classes
- Record attended classes
- Calculate attendance percentage
- Display attendance status

Attendance status includes:

- Good
- Warning
- Low

### 5. Reports

The Reports module provides a complete overview of student performance.

Users can:

- View student reports
- Refresh reports
- Export reports to CSV

The exported file is:

`student_report.csv`

## 🛠️ Technologies Used

- **Java**
- **Java Swing**
- **Java AWT**
- **Java Collections**
- **Java Serialization**
- **File Handling**
- **Object-Oriented Programming**
- **Graphics2D**

No external libraries or database are required.

## 💻 Requirements

Before running the project, make sure you have:

- Java JDK 17 or later
- Visual Studio Code
- Java Extension Pack for VS Code

The project can also be executed directly using the Java compiler from the terminal.

## 📂 Project Structure

```text
Student-Management-System/
│
├── Main.java
├── Student.java
├── StudentManager.java
├── ChartPanel.java
├── StudentPanel.java
├── AcademicPanel.java
├── AttendancePanel.java
├── ReportPanel.java
├── README.md
└── .gitignore