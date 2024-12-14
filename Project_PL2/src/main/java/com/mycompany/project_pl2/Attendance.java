package com.mycompany.project_pl2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Attendance {
    private int attendanceId;
    private int employeeId;
    private Date date;
    private String timeIn;
    private String timeOut;

    // Constructor
    public Attendance(int attendanceId, int employeeId, Date date, String timeIn, String timeOut) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // Save Attendance to file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = formatter.format(date);  // Format the date to string
            writer.write(attendanceId + "," + employeeId + "," + dateStr + "," + timeIn + "," + timeOut);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving to attendance file: " + e.getMessage());
        }
    }
    
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading employee file: " + e.getMessage());
        }
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

