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
import java.util.*;

public class TaskCalendar extends TaskManagementSystem {

    private static final String FILE_NAME = "../files/tasks.txt";

    public TaskCalendar(int id, String userType) {
        super(id, userType); // Calls the constructor of TaskManagementSystem
    }

    public void viewTasksByDate(Date startDate, Date endDate) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                Date taskDueDate = new Date(Long.parseLong(data[4]));
                if (taskDueDate.after(startDate) && taskDueDate.before(endDate)) {
                    System.out.println("Task ID: " + data[0]);
                    System.out.println("Project ID: " + data[1]);
                    System.out.println("Assigned Employee ID: " + data[2]);
                    System.out.println("Task Description: " + data[3]);
                    System.out.println("Due Date: " + taskDueDate);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }
    }

    public void viewTaskDueToday() {
        Date today = new Date();
        viewTasksByDate(today, today);
    }

    public void viewAllUpcomingTasks() {
        Date today = new Date();
        viewTasksByDate(today, new Date(Long.MAX_VALUE));
    }
}

