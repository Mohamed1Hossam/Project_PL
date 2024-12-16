package tms;

import java.io.*;
import java.util.*;

public class Task extends TaskManagementSystem {

    private int taskId;
    private String title;
    private String description;
    private String assignedEmployee;
    private String phase;
    private String project;
    private String priority;
    private Date startDate;
    private Date endDate;
    private int estimatedHours;

    public Task() {

    }

    public Task(int taskId) {
        this.taskId = taskId;

    }

    public Task(int taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }
    
    public Task(int taskId, String title, String description,String Assigned,String Phase) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.assignedEmployee=Assigned;
        this.phase=Phase;
    }
    

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPhase() {
        return phase;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
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
        deleteFromFile(taskId);
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
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("tasks.txt", true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            System.out.println("Error saving task: " + e.getMessage());
            writeLog("Error saving task: " + e.getMessage());
        }
    }

    private void updateInFile() {
        File inputFile = new File("tasks.txt");
        File tempFile = new File("temp_tasks.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

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
    
    
    public void update() {
        File inputFile = new File("tasks.txt");
        File tempFile = new File("temp_tasks.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("TaskID: " + taskId)) {
                    // Construct the new line with updated information
                    writer.println(this.toString());
                    updated = true;
                } else {
                    writer.write(currentLine);
                    writer.println();

                }
            }
        } catch (IOException e) {
            System.out.println("Error while updating Task: " + e.getMessage());
            writeLog("Error while updating Task: " + e.getMessage());
            return;
        }

        if (updated) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Task updated successfully.");
                writeLog("Task updated successfully.");
            } else {
                System.out.println("Error updating Task file.");
                writeLog("Error updating Task file.");
            }
        } else {
            System.out.println("Task ID not found.");
            writeLog("Task ID not found.");
            tempFile.delete();
        }
    }
    
    
    

   private void deleteFromFile(int taskId) { // Ensure taskId is passed or available as a parameter
    File inputFile = new File("tasks.txt");
    File tempFile = new File("temptasks.txt");
    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.contains("TaskID: " + taskId)) {
                                    String s="";
                    writer.println(s);

                System.out.println("Task found.");
                found = true;
            } else {
                writer.println(currentLine); // Ensures proper line formatting
            }
        }

        if (found) {
            if (inputFile.delete()) {
                if (tempFile.renameTo(inputFile)) {
                    System.out.println("Task deleted successfully.");
                    writeLog("Task deleted successfully.");
                } else {
                    System.out.println("Error renaming temporary file.");
                    writeLog("Error renaming temporary file.");
                }
            } else {
                System.out.println("Error deleting original file.");
                writeLog("Error deleting original file.");
            }
        } else {
            System.out.println("Task not found.");
            writeLog("Task not found.");
            tempFile.delete(); // Clean up the temporary file
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
