# 📚 StudentJDBC – Java & SQL Database Project

Welcome to the **StudentJDBC** project!  
This repository demonstrates how to integrate **Java** with a **SQL database** for managing student records.

---

## 📖 1. Project Overview
- This is a **Java + Database (SQL)** project.  
- It showcases how to connect Java applications to a relational database, execute queries, and manage student data.

---

## 📂 2. Repository Structure
- **`src/`**  
  Contains the **Java source code**. Expect classes for:
  - Database connection (likely via JDBC).
  - Student entity and data access logic.
  - Utility functions for handling queries.

- **`SQLQuery_1.sql`**  
  A SQL script with sample queries (e.g., SELECT, INSERT, UPDATE) for testing the database.

- **`Script_StudentDB.sql`**  
  Script to **create and populate the Student database**. Includes table definitions and initial data.

- **`project.rar` / `project.zip`**  
  Archived versions of the project, useful for distribution or backup.

- **`.DS_Store`**  
  macOS system file, not relevant to the project.

---

## 🛠️ 3. Technologies Used
- **Java** → Core application logic.  
- **SQL (T-SQL)** → Database schema and queries.  
- **JDBC** → Java Database Connectivity for linking Java code with SQL.

---

## ⚙️ 4. How to Run
1. Import the SQL schema:  
   ```bash
   mysql -u root -p < Script_StudentDB.sql
