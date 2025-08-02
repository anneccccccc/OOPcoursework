Student Attendance Management System (SAMS)
Project Overview
This is a Student Attendance Management System (SAMS) developed to facilitate the tracking and reporting of student attendance for educational institutions. The system helps administrative staff and lecturers manage courses, schedule classes, monitor student attendance, and generate various attendance-related reports.

Key Features
Course Management: Create, read, update, and delete (CRUD) courses and subjects.

Student Management: Manage student profiles, including registration numbers and contact details.

Lecturer Management: Maintain profiles for lecturers and their assigned subjects.

Class Scheduling: Create and manage schedules for classes, assigning them to specific subjects and lecturers.

Attendance Marking: Lecturers can mark attendance for students on a per-class basis.

Attendance Reporting: Generate attendance reports filtered by student, subject, or a specific date range.

Technologies and Architecture
This project is built using a layered architecture to separate concerns and ensure maintainability.

Architecture: Layered Architecture (Presentation → Service → Data Access)

Language: Java

Database: MySQL

UI Framework: JavaFX or Java Swing (to be implemented)

Database Connectivity: JDBC

Setup Instructions
Follow these steps to get the project up and running on your local machine.

1. Prerequisites
Java Development Kit (JDK): Version 8 or higher.

MySQL Server: Version 8.0 or higher.

Java IDE: NetBeans, IntelliJ IDEA, or Eclipse.

2. Database Setup
Open your MySQL command-line tool or MySQL Workbench.

Run the sams_db.sql script to create the database, tables, and sample data.

3. Project Configuration
Open the project in your IDE.

Ensure you have the MySQL Connector/J JAR file added to your project's classpath.

Navigate to the edu.ijse.mvc.dao.DBConnection class.

Update the USER and PASSWORD variables with your MySQL credentials.

4. Run the Application
The application can be run from the edu.ijse.mvc.Main class. You can either run the main method directly from your IDE or build the project into a .jar file and execute it from the command line.
