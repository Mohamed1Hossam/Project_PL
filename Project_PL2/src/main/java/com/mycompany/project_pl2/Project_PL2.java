// Project_PL2.java
package com.mycompany.project_pl2;

import java.util.Date;
import java.util.List;

public class Project_PL2 {

    private static Iterable<Attendance> attendanceRecords;
    public static void main(String[] args) {
        // Example of creating an Attendance object
        Attendance attendance = new Attendance(1, 101, new Date(), "09:00", "17:00");
        attendance.saveToFile();

        // Load attendance records
        List<Attendance> attendanceList = readFromFile(); // Now it works if readFromFile is static
        for (Attendance record : attendanceRecords) {
            record.viewAttendanceRecord();
        }

        // Example of creating a LeaveRequest
        LeaveRequest leaveRequest = new LeaveRequest(101, "John Doe", "Developer", 1, "Sick Leave", "2024-12-01", "2024-12-05");
        leaveRequest.submitLR();

        // Example of creating a PermissionRequest
        PermissionRequest permissionRequest = new PermissionRequest("PR-001", "101", "Leave", "2024-12-01", "Pending");
        permissionRequest.submitPermissionRequest();
    }

    private static List<Attendance> readFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}