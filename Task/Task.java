/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8;


import java.io.*;
import java.util.*;

public class Task extends TaskManagementSystem {
    
    private String taskId;
    private String projectId;
    private String assignedEmployeeId;
    private String taskDescription;
    private Date dueDate;
    private String status;

    private static final String FILE_NAME = "../files/tasks.txt";
    
    public Task(int id, String userType, String taskId, String projectId, String assignedEmployeeId, String taskDescription, Date dueDate, String status) {
        super(id, userType); // Calls the constructor of TaskManagementSystem
        this.taskId = taskId;
        this.projectId = projectId;
        this.assignedEmployeeId = assignedEmployeeId;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.status = status;
        writeLog("Created Task: " + taskId);
    }

    public String getTaskId() {
        writeLog("Accessed Task ID: " + taskId);
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
        writeLog("Updated Task ID to: " + taskId);
    }

    public String getProjectId() {
        writeLog("Accessed Project ID for Task: " + taskId);
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
        writeLog("Updated Project ID to: " + projectId);
    }

    public String getAssignedEmployeeId() {
        writeLog("Accessed Assigned Employee ID for Task: " + taskId);
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
        writeLog("Updated Assigned Employee ID to: " + assignedEmployeeId);
    }

    public String getTaskDescription() {
        writeLog("Accessed Task Description for Task: " + taskId);
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        writeLog("Updated Task Description for Task: " + taskId);
    }

    public Date getDueDate() {
        writeLog("Accessed Due Date for Task: " + taskId);
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        writeLog("Updated Due Date for Task: " + taskId);
    }

    public String getStatus() {
        writeLog("Accessed Status for Task: " + taskId);
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        writeLog("Updated Status for Task: " + taskId);
    }

    public void addTask() {
        String taskData = toDataString();
        writeToFile(FILE_NAME, taskData);
        System.out.println("Task added successfully.");
        writeLog("Added Task: " + taskId);
    }

    public static List<String> getAllTasks() {
        return readFile(FILE_NAME);
    }

    private String toDataString() {
        return taskId + "," + projectId + "," + assignedEmployeeId + "," + taskDescription + "," + dueDate.getTime() + "," + status;
    }
}

