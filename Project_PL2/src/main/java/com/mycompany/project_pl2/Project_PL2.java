package com.mycompany.project_pl2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Project_PL2 {
    public static void main(String[] args) {
        // Dynamically resolve file paths relative to the project root
        String basePath = new File("src/main/java/com/mycompany/project_pl2/files").getAbsolutePath();

        // List of file names for different functionalities
        List<String> fileNames = Arrays.asList("Log.txt", "employee.txt", "tasks.txt", "userslogin.txt", 
                                               "users.txt", "attendance.txt", "LR.txt", 
                                               "PermissionRequest.txt", "projects.txt");

        // Create FileManager instances for each file
        FileManager logFileManager = new FileManager(basePath + "/Log.txt");
        FileManager employeeFileManager = new FileManager(basePath + "/employee.txt");
        FileManager taskFileManager = new FileManager(basePath + "/tasks.txt");
        FileManager userloginFileManager = new FileManager(basePath + "/userslogin.txt");
        FileManager userFileManager = new FileManager(basePath + "/users.txt");
        FileManager attendanceFileManager = new FileManager(basePath + "/attendance.txt");
        FileManager LRFileManager = new FileManager(basePath + "/LR.txt");
        FileManager permissionRequestFileManager = new FileManager(basePath + "/PermissionRequest.txt");
        FileManager projectsFileManager = new FileManager(basePath + "/projects.txt");

        // Admin instance
        Admin admin = new Admin(employeeFileManager, taskFileManager, userloginFileManager);

        // Test login functionality
        System.out.println("Testing login functionality...");
        boolean loginSuccess = admin.loginUser("mina", "mina123");
        if (!loginSuccess) {
            System.out.println("Login test failed. Ensure the 'users.txt' file contains the correct credentials.");
        }

        // Test employee management
        System.out.println("\nTesting employee management...");
        Employee newEmployee = new Employee(1, "John Doe", "Developer");
        admin.addEmployee(newEmployee);
        admin.updateEmployeeID("1", "2");  // Change ID from 1 to 2
        admin.deleteEmployee("John Doe");

        // Test task management
        System.out.println("\nTesting task management...");
        Task newTask = new Task(1, "Employee", 101, "Code Review", "Review PR #45");
        admin.addTask(newTask);
        admin.updateTaskPhase("101", "Code Testing", "In Progress");
        admin.deleteTask("101");

        // Test attendance functionality
        System.out.println("\nTesting attendance functionality...");
        try {
            Date now = new Date();
            Attendance attendance = new Attendance(1, 2, now, "09:00 AM", "05:00 PM");
            attendance.saveToFile();
            attendance.readFromFile();
        } catch (Exception e) {
            System.out.println("Attendance test failed: " + e.getMessage());
        }

        // Test leave requests
        System.out.println("\nTesting leave request functionality...");
        LeaveRequest leaveRequest = new LeaveRequest(2, "Jane Smith", "Designer", 101, "Sick Leave", "2024-12-20", "2024-12-22");
        leaveRequest.approveLR();
        leaveRequest.deleteLR(101);

        // Test project management
        System.out.println("\nTesting project management...");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ProjectManagement project = new ProjectManagement("PRJ101", "Acme Corp", sdf.parse("2024-01-01"), sdf.parse("2024-12-31"));
            project.addProject();
            project.updateProject("PRJ101", new ProjectManagement("PRJ101", "Acme Corp Updated", sdf.parse("2024-01-01"), sdf.parse("2025-01-01")));
            project.deleteProject("PRJ101");
        } catch (Exception e) {
            System.out.println("Project management test failed: " + e.getMessage());
        }

        // Test task phase management
        System.out.println("\nTesting task phase management...");
        TaskPhaseManagement taskPhase = new TaskPhaseManagement("TSK101", "Initial Setup", "To Do");
        System.out.println(taskPhase);

        // Test permission requests
        System.out.println("\nTesting permission request functionality...");
        PermissionRequest permissionRequest = new PermissionRequest("PRM101", "Jane Smith", "Designer", 2, "Work From Home", "2024-12-21", "Pending");
        permissionRequest.submitPermissionRequest();
        permissionRequest.approvePermissionRequest("PRM101");
    }
}
