package com.sheets.Sheets;

import java.io.*;
import java.util.*;

public class Employee {
    private int employeeId;
    private String name;
    private String role;
    private List<Attendance> attendanceRecord;
    private List<String> taskList;

    // Constructor
    public Employee(int employeeId, String name, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.attendanceRecord = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    // Methods
    public void viewAssignedTasks() {
        System.out.println("Tasks for employee: " + name);
        for (String task : taskList) {
            System.out.println(task);
        }
    }

    public void viewAttendance() {
        System.out.println("Attendance Record for employee: " + name);
        for (Attendance attendance : attendanceRecord) {
            attendance.viewAttendanceRecord();
        }
    }

    public void markAttendance(Attendance attendance) {
        attendanceRecord.add(attendance);
    }

    // Save employee details to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt", true))) {
            writer.write(employeeId + "," + name + "," + role);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to employee file: " + e.getMessage());
        }
    }

    // Read employee details from a file
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading employee file: " + e.getMessage());
        }
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Attendance> getAttendanceRecord() {
        return attendanceRecord;
    }

    public void setAttendanceRecord(List<Attendance> attendanceRecord) {
        this.attendanceRecord = attendanceRecord;
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }
}
