# JSLT Library

JSLT Library is a library management application developed by Group 7 (Jaisy, Salsa, Liza, and Tri) for the completion of the UAS Assignment in Pemrograman I (04TPLE005).

## Features

- Manage books, book copies, students, and borrowing records
- View available book copies with a dedicated SQL view
- Student login and authentication
- Update user profile (email and password)
- Search and borrow books
- Track and return borrowed books
- User-friendly dashboards for students

## Requirements

- Windows Operating System
- MySQL Database Server
- phpMyAdmin

## Setup Instructions

### 1. Create the Database

1. Open **phpMyAdmin**.
2. Create a new database named **jslt_library**.

### 2. Import the Database Structure and Sample Data

1. Select the **jslt_library** database in phpMyAdmin.
2. Go to the **Import** tab.
3. [Choose the provided sql file from the release page](https://github.com/JaisySymuri/jslt-library/releases/tag/v1.0.1)
4. Click **Go** to import the structure and sample data.

## Installation and Usage

1. [Download the JSLT Library Installer (.msi)](https://github.com/JaisySymuri/jslt-library/releases/tag/v1.0.1)
2. Launch the JSLT Library application.
3. Since user registration is not available, log in using one of the pre-registered student accounts.  
   Example credentials:  
   - **NIM**: 231011400253  
   - **Password**: 1q2w3e4r
4. After logging in, the main dashboard displays a summary of your borrowed book status and navigation options.
5. In the **Student Dashboard**, you can update your email and password.
6. In the **Book Dashboard**, you can search for and borrow books.
7. In the **Borrowed Books Dashboard**, you can view the status of your borrowed books and return them.

## Authors

- Jaisy Muhammad Algifari
- Salsabila
- Liza Cahyati Rami
- Tri Wulan Setiyowati

_Group 7, Pemrograman I (04TPLE005)_

---