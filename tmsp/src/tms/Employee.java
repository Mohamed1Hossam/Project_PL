package tms;

import java.io.*;
import java.util.*;

public class Employee extends TaskManagementSystem {
    private int employeeId;
    private String name;
    private String role;
    private List<Attendance> attendanceRecord;
    private List<String> taskList;

    // Constructor
    
    public Employee(){
        
    }
    public Employee(int id,String type,int employeeId, String name, String role) {
        super.setId(id);
        super.setType(type);
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.attendanceRecord = new ArrayList<>();
        this.taskList = new ArrayList<>();
        saveToFile();
    }
    public Employee(int id,String type) {
        super.setId(id);
        super.setType(type);
    }
    
    public Employee(int employeeId, String name, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.attendanceRecord = new ArrayList<>();
        this.taskList = new ArrayList<>();
        saveToFile();
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
    public  void readFromFile() {
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





   public  void deleteProject(String projectId) {
        File inputFile = new File("employee.txt");
        File tempFile = new File("temp_projects.txt");
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!data[0].trim().equals(projectId.trim())) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                System.out.println("employee deleted successfully.");
                writeLog("Deleted employee: " + projectId);
            } else {
                System.out.println("employee ID not found for deletion.");
                writeLog("employee ID not found for deletion: " + projectId);
            }

        } catch (IOException e) {
            System.out.println("Error while deleting employee: " + e.getMessage());
            writeLog("Error deleting employee: " + projectId + " - " + e.getMessage());
        }

        if (inputFile.exists() && !inputFile.delete()) {
            System.out.println("Failed to delete original file. Check file permissions.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Failed to rename temp file. Check file permissions.");
        }
    }


   
    public void update(int employeeId, String name, String role) {
        File inputFile = new File("employee.txt");
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (currentLine.contains(""+employeeId)) {

                    // Construct the new line with updated information
                    writer.println(employeeId + "," + name + "," + role);
                    updated = true;
                } else {
                    writer.write(currentLine);
                    writer.println();

                }
            }
        } catch (IOException e) {
            System.out.println("Error while updating user: " + e.getMessage());
            writeLog("Error while updating user: " + e.getMessage());
            return;
        }

        if (updated) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("User updated successfully.");
                writeLog("User updated successfully.");
            } else {
                System.out.println("Error updating user file.");
                writeLog("Error updating user file.");
            }
        } else {
            System.out.println("User ID not found.");
            writeLog("User ID not found.");
            tempFile.delete();
        }
    }
   




}



