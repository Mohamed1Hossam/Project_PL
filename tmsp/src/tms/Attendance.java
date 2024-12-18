package tms;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Attendance extends Employee {
    private int attendanceId;
    private int employeeId;
    private String date;
    private String timeIn;
    private String timeOut;

    // Constructor
    public Attendance(){
        
    }
        
    public Attendance(int attendanceId, int employeeId, String date, String timeIn, String timeOut) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // Save Attendance to file
    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            writer.write("Attentance ID: "+attendanceId + ", Employee ID: " + employeeId + ", Start Date: " + date + ", Time in: " + timeIn + ", Time out:" + timeOut);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving to attendance file: " + e.getMessage());
        }
    }

    public String readFromFileAttentance() {
         StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Append each line with a newline
            }
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage(); // Return the error message as the content
        }
        return content.toString();
    }
    
    // Method to view attendance record
    public void viewAttendanceRecord() {
        System.out.println("Attendance ID: " + attendanceId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Date: " + date);
        System.out.println("Time In: " + timeIn);
        System.out.println("Time Out: " + timeOut);
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}

