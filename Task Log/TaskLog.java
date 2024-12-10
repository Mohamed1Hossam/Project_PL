/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8;

/**
 *
 * @author Mahmoud
 */
import java.io.*;

public class TaskLog extends TaskManagementSystem {

    private String taskId;
    private String logMessage;
    private Date timestamp;

    private static final String FILE_NAME = "../files/task_logs.txt";

    public TaskLog(int id, String userType, String taskId, String logMessage) {
        super(id, userType); // Calls the constructor of TaskManagementSystem
        this.taskId = taskId;
        this.logMessage = logMessage;
        this.timestamp = new Date();
        writeLog("Created Task Log for Task: " + taskId);
    }

    public void logTaskUpdate() {
        String logData = taskId + "," + logMessage + "," + timestamp.getTime();
        writeToFile(FILE_NAME, logData);
        System.out.println("Task log recorded.");
        writeLog("Logged Task Update for Task: " + taskId);
    }
}

