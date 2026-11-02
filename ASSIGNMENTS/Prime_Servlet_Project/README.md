# Java Class Assignment — JDBC, Apache Tomcat & Java Servlets

## Student Information

| Field | Details |
|---|---|
| *Name* | Protham Sarker|
| *Student ID* | IT23027 |
| *Course* | Software Engineering and Project Management |
| *Assignment* | Java Class Assignment |
| *Date* | November 1, 2026 |
| *Programming Language* | Java |

---

## Assignment Overview

This assignment demonstrates Java programming, JDBC database connectivity, Apache Tomcat configuration, and Java Servlet development using IntelliJ IDEA Ultimate.

### Assigned Tasks

1. Install IntelliJ IDEA Ultimate.
2. JDBC Test — Calculate and insert the nth prime number starting from 2.
3. Configure Apache Tomcat and run a Hello World Servlet.
4. Check primality from a web browser using a Java Servlet.

---

## Development Environment

| Technology | Version |
|---|---|
| IDE | IntelliJ IDEA Ultimate |
| Java JDK | 25.0.2 |
| Build Tool | Maven |
| Database | MySQL Community Server 26.7.0 |
| Database Tool | MySQL Workbench |
| JDBC Driver | MySQL Connector/J 9.5.0 |
| Web Server | Apache Tomcat 11.0.24 |
| Servlet API | Jakarta Servlet 6.1.0 |
| Operating System | Windows 11 |

---

# Task 1 — Install IntelliJ IDEA Ultimate

## Objective

Install and configure IntelliJ IDEA Ultimate for Java application development.

## Steps

1. Download IntelliJ IDEA from the official JetBrains website.
2. Install and launch IntelliJ IDEA.
3. Select *New Project*.
4. Choose *Java* as the programming language.
5. Select *Maven* as the build system.
6. Configure Java JDK 25.
7. Create the project.

### Project Configuration

| Setting | Value |
|---|---|
| Project Name | Class Assignment |
| Group ID | com.assignment |
| Artifact ID | class-assignment |
| JDK | 25 |
| Build System | Maven |

## Result

IntelliJ IDEA was successfully configured with Java JDK 25 and Maven.

### Screenshot

![IntelliJ IDEA Setup](screenshots/task1-intellij.png)

---

# Task 2 — JDBC Test: nth Prime Number

## Objective

Create a Java program that accepts a number n, calculates the nth prime number starting from 2, and inserts the result into a MySQL database using JDBC.

## Prime Number Concept

A prime number is a natural number greater than 1 that is divisible only by 1 and itself.

The first ten prime numbers are:

2, 3, 5, 7, 11, 13, 17, 19, 23, 29

### Examples

| Input 👎 | nth Prime |
|---|---|
| 1 | 2 |
| 2 | 3 |
| 3 | 5 |
| 4 | 7 |
| 5 | 11 |
| 10 | 29 |

## Step 1 — Create the Database

Open MySQL Workbench and execute:

CREATE DATABASE IF NOT EXISTS java_assignment;

USE java_assignment;

CREATE TABLE IF NOT EXISTS prime_numbers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    n INT NOT NULL,
    prime_number INT NOT NULL
);

## Step 2 — Java JDBC Implementation

*File:* src/main/java/Main.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int findNthPrime(int n) {
        int count = 0;
        int number = 1;

        while (count < n) {
            number++;

            if (isPrime(number)) {
                count++;
            }
        }

        return number;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter n: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            return;
        }

        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive number.");
            return;
        }

        int prime = findNthPrime(n);

        System.out.println(
            "The " + n + "th prime number is: " + prime
        );

        String url = "jdbc:mysql://localhost:3306/java_assignment";
        String username = "root";

        System.out.print("Enter MySQL password: ");
        String password = scanner.next();

        String sql =
            "INSERT INTO prime_numbers (n, prime_number) VALUES (?, ?)";

        try (
            Connection connection =
                DriverManager.getConnection(url, username, password);

            PreparedStatement statement =
                connection.prepareStatement(sql)
        ) {

            statement.setInt(1, n);
            statement.setInt(2, prime);

            statement.executeUpdate();

            System.out.println("Connected to MySQL successfully!");
            System.out.println("Prime number inserted into database.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        scanner.close();
    }
}

## Step 3 — Execute the Program

Run Main.java in IntelliJ IDEA.

### Sample Output

text
Enter n: 5
The 5th prime number is: 11
Enter MySQL password:
Connected to MySQL successfully!
Prime number inserted into database.

## Step 4 — Verify Database Insertion

Execute:

USE java_assignment;

SELECT * FROM prime_numbers;

### Example Database Result

| id | n | prime_number |
|---|---|---|
| 1 | 5 | 11 |

## Result

The Java application calculates the nth prime number and successfully inserts it into MySQL using JDBC.

### Screenshots

![JDBC Program Output](screenshots/task2-jdbc-output.png)

![MySQL Database Result](screenshots/task2-mysql-result.png)

---

# Task 3 — Apache Tomcat and Hello World Servlet

## Objective

Configure Apache Tomcat in IntelliJ IDEA and create a Java Servlet that displays a Hello World message in a web browser.

## Step 1 — Configure Apache Tomcat

1. Download and extract Apache Tomcat.
2. Open IntelliJ IDEA.
3. Navigate to *Run → Edit Configurations*.
4. Select *Tomcat Server → Local*.
5. Configure Apache Tomcat 11.0.24.
6. Set HTTP Port to 8080.
7. Select JDK 25.
8. Configure the WAR exploded artifact.

## Step 2 — Maven Configuration

*File:* pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.assignment</groupId>
    <artifactId>class-assignment</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>war</packaging>

    <properties>
        <maven.compiler.release>25</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.5.0</version>
        </dependency>

        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.1.0</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>

    <build>
        <finalName>class-assignment</finalName>

        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.14.0</version>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.4.0</version>
            </plugin>
        </plugins>
    </build>

</project>

## Step 3 — Hello World Servlet

*File:* src/main/java/com/assignment/HelloServlet.java

package com.assignment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("<p>My first Java Servlet is running!</p>");
        out.println("</body>");
        out.println("</html>");
    }
}

## Step 4 — Deploy the Servlet

1. Open *File → Project Structure → Artifacts*.
2. Select Class-Assignment:war exploded.
3. Open Tomcat Run Configuration.
4. Select the *Deployment* tab.
5. Add the WAR exploded artifact.
6. Set the application context to /class-assignment.
7. Start Apache Tomcat.

## Step 5 — Browser Execution

Open:

http://localhost:8080/class-assignment/hello

### Expected Output

text
Hello World!

My first Java Servlet is running!

## Result

Apache Tomcat was configured successfully, and the Hello World Servlet executed in the browser.

### Screenshots

![Apache Tomcat Configuration](screenshots/task3-tomcat-config.png)

![Hello World Servlet](screenshots/task3-hello-world.png)

---

# Task 4 — Browser-Based Primality Checker

## Objective

Create a Java Servlet that accepts a number from a web browser and determines whether it is prime.

## Step 1 — Create PrimeServlet.java

*File:* src/main/java/com/assignment/PrimeServlet.java

package com.assignment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/prime")
public class PrimeServlet extends HttpServlet {

    private boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }

        for (long i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Prime Number Checker</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Prime Number Checker</h1>");

        out.println("<form method='get' action='prime'>");
        out.println("<label>Enter a number: </label>");
        out.println("<input type='number' name='number' min='0' required>");
        out.println("<button type='submit'>Check Prime</button>");
        out.println("</form>");

        String input = request.getParameter("number");

        if (input != null) {
            try {
                long number = Long.parseLong(input);

                if (isPrime(number)) {
                    out.println("<h2>" + number +
                            " is a Prime Number!</h2>");
                } else {
                    out.println("<h2>" + number +
                            " is NOT a Prime Number.</h2>");
                }

            } catch (NumberFormatException e) {
                out.println("<p>Please enter a valid integer.</p>");
            }
        }

        out.println("</body>");
        out.println("</html>");
    }
}

## Step 2 — Run the Servlet

1. Save PrimeServlet.java.
2. Start Apache Tomcat.
3. Deploy the application.
4. Open the browser.

### Servlet URL

http://localhost:8080/class-assignment/prime

## Step 3 — Test Cases

| Input | Expected Result |
|---|---|
| 2 | Prime |
| 3 | Prime |
| 7 | Prime |
| 11 | Prime |
| 17 | Prime |
| 1 | Not Prime |
| 4 | Not Prime |
| 20 | Not Prime |
| 100 | Not Prime |

### Example 1

*Input:*

text
17

*Output:*

text
17 is a Prime Number!

### Example 2

*Input:*

text
20

*Output:*

text
20 is NOT a Prime Number.

## Result

The Java Servlet successfully accepts user input from a browser, checks primality, and displays the result.

### Screenshots

![Prime Number Checker](screenshots/task4-prime-checker.png)

![Primality Result](screenshots/task4-prime-result.png)

---

# Project Structure

text
Class Assignment/
│
├── pom.xml
├── README.md
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java
│   │   │   └── com/
│   │   │       └── assignment/
│   │   │           ├── HelloServlet.java
│   │   │           └── PrimeServlet.java
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
│
└── screenshots/
    ├── task1-intellij.png
    ├── task2-jdbc-output.png
    ├── task2-mysql-result.png
    ├── task3-tomcat-config.png
    ├── task3-hello-world.png
    ├── task4-prime-checker.png
    └── task4-prime-result.png

---

# How to Run the Project

## Prerequisites

- IntelliJ IDEA Ultimate
- Java JDK 25
- Maven
- MySQL Server
- MySQL Workbench
- Apache Tomcat 11

## Task 2 — JDBC

1. Start MySQL Server.
2. Create the java_assignment database.
3. Create the prime_numbers table.
4. Open the Maven project.
5. Run Main.java.
6. Enter the required input and MySQL password.
7. Verify the inserted record in MySQL Workbench.

## Task 3 — Hello World

1. Configure Apache Tomcat.
2. Deploy the WAR exploded artifact.
3. Start the Tomcat server.
4. Visit:

   http://localhost:8080/class-assignment/hello

## Task 4 — Prime Number Checker

1. Start Apache Tomcat.
2. Deploy the web application.
3. Visit:

   http://localhost:8080/class-assignment/prime

4. Enter a number and click *Check Prime*.

*Note:* These localhost URLs work only when the application is running on the local computer.

---

# Learning Outcomes

Through this assignment, I learned how to:

1. Install and configure IntelliJ IDEA Ultimate.
2. Develop Java applications using Maven.
3. Implement prime number algorithms.
4. Connect Java applications to MySQL using JDBC.
5. Execute SQL queries using PreparedStatement.
6. Configure Apache Tomcat.
7. Develop Java Servlets using Jakarta Servlet API.
8. Deploy WAR applications.
9. Handle HTTP GET requests.
10. Process user input through browser-based Java applications.

---

# Conclusion

This assignment demonstrates the integration of Java programming, JDBC, MySQL, Apache Tomcat, and Java Servlets.

Task 1 involved configuring IntelliJ IDEA Ultimate and the Java Development Kit.

Task 2 implemented a Java application that calculates the nth prime number and stores the result in MySQL using JDBC.

Task 3 demonstrated Apache Tomcat configuration and successful execution of a Hello World Servlet.

Task 4 implemented a browser-based primality checker using Java Servlets.

All four tasks were completed using Java and IntelliJ IDEA Ultimate.

---

# References

1. [Java Documentation](https://docs.oracle.com/en/java/)
2. [IntelliJ IDEA Documentation](https://www.jetbrains.com/help/idea/)
3. [Apache Maven Documentation](https://maven.apache.org/)
4. [MySQL Documentation](https://dev.mysql.com/doc/)
5. [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/en/)
6. [Apache Tomcat Documentation](https://tomcat.apache.org/)
7. [Jakarta Servlet Documentation](https://jakarta.ee/specifications/servlet/)

---

*Prepared By:* Protham Sarker 
*Student ID:* IT23027  
*Course:* Software Engineering and Project Management  
*Date:* November 1, 2026
