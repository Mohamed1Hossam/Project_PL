package com.mycompany.project_pl2;


import java.io.*;
import java.util.*;
class TaskLog extends TaskManagementSystem {
    private int logId;
    private int taskId;
    private int employeeId;
    private Date fromTime;
    private Date toTime;
    private int actualTimeSpent;

    public TaskLog(int id, String userType, int logId, int taskId, int employeeId) {
        super(id, userType);
        this.logId = logId;
        this.taskId = taskId;
        this.employeeId = employeeId;
    }

    public void addTaskLog() {
        writeLog("TaskLog added: " + logId + " for Task: " + taskId);
        saveToFile();
    }

    public void updateTaskLog(Date newFromTime, Date newToTime) {
        this.fromTime = newFromTime;
        this.toTime = newToTime;
        this.actualTimeSpent = (int) ((toTime.getTime() - fromTime.getTime()) / (1000 * 60 * 60));
        writeLog("TaskLog updated: " + logId);
        updateInFile();
    }

    public void viewTaskLog() {
        File inputFile = new File("files/tasklogs.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("LogID: " + logId + ",")) {
                    System.out.println(currentLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Error viewing task log: " + e.getMessage());
            writeLog("Error viewing task log: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("files/tasklogs.txt", true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            System.out.println("Error saving task log: " + e.getMessage());
            writeLog("Error saving task log: " + e.getMessage());
        }
    }

    private void updateInFile() {
        File inputFile = new File("files/tasklogs.txt");
        File tempFile = new File("files/temp_tasklogs.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("LogID: " + logId + ",")) {
                    writer.println(this.toString());
                } else {
                    writer.println(currentLine);
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error updating task log file.");
                writeLog("Error updating task log file.");
            }

        } catch (IOException e) {
            System.out.println("Error updating task log: " + e.getMessage());
            writeLog("Error updating task log: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "LogID: " + logId + ", TaskID: " + taskId + ", EmployeeID: " + employeeId + ", FromTime: " + fromTime + ", ToTime: " + toTime + ", TimeSpent: " + actualTimeSpent;
    }
}

