package com.mycompany.project_pl2;


import java.io.*;
import java.util.*;

public class Task extends TaskManagementSystem {
    public int taskId;
    public String title;
    public String description;
    public String assignedEmployee;
    public String phase;
    public String project;
    public String priority;
    public Date startDate;
    public Date endDate;
    public int estimatedHours;

    public Task(int id, String userType, int taskId, String title, String description) {
        super(id, userType);
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

    public void createTask() {
        writeLog("Task created: " + taskId + ", Title: " + title);
        saveToFile();
    }

    public void updateTask(String newTitle, String newDescription) {
        this.title = newTitle;
        this.description = newDescription;
        writeLog("Task updated: " + taskId);
        updateInFile();
    }

    public void deleteTask() {
        writeLog("Task deleted: " + taskId);
        deleteFromFile();
    }

    public void reassignTask(String newEmployee) {
        this.assignedEmployee = newEmployee;
        writeLog("Task reassigned: " + taskId + " to Employee: " + newEmployee);
        updateInFile();
    }

    public void changeTaskPhase(String newPhase) {
        this.phase = newPhase;
        writeLog("Task phase changed: " + taskId + " to Phase: " + newPhase);
        updateInFile();
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("../files/tasks.txt", true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            System.out.println("Error saving task: " + e.getMessage());
            writeLog("Error saving task: " + e.getMessage());
        }
    }

    private void updateInFile() {
        File inputFile = new File("../files/tasks.txt");
        File tempFile = new File("../files/temp_tasks.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("TaskID: " + taskId + ",")) {
                    writer.println(this.toString());
                } else {
                    writer.println(currentLine);
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error updating task file.");
                writeLog("Error updating task file.");
            }

        } catch (IOException e) {
            System.out.println("Error updating task: " + e.getMessage());
            writeLog("Error updating task: " + e.getMessage());
        }
    }

    private void deleteFromFile() {
        File inputFile = new File("../files/tasks.txt");
        File tempFile = new File("../files/temp_tasks.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.startsWith("TaskID: " + taskId + ",")) {
                    writer.println(currentLine);
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error deleting task file.");
                writeLog("Error deleting task file.");
            }

        } catch (IOException e) {
            System.out.println("Error deleting task: " + e.getMessage());
            writeLog("Error deleting task: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "TaskID: " + taskId + ", Title: " + title + ", Description: " + description + ", AssignedEmployee: " + assignedEmployee + ", Phase: " + phase;
    }
}

