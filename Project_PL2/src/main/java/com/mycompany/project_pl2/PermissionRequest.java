package com.mycompany.project_pl2;


import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PermissionRequest extends Employee {

    private String permissionRequestId;
    private String employeeId;
    private String requestType;
    private String date;
    private String status;
    
    private static final String LOG_FILE_NAME = "../files/log.txt";
    private static final String FILE_NAME = "../files/PermissionRequest.txt";
    
    public PermissionRequest(String permissionRequestId, String employeeId, String requestType, String date, String status) {
        super(0, "Default Name", "Default Role"); // Provide appropriate values for Employee
        this.permissionRequestId = permissionRequestId;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
        writeLog("Created permission request object: " + permissionRequestId);
    }
    
        // Static method to write log messages
    private static void writeLog(String message) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE_NAME, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);
            logWriter.write(timestamp + " - " + message);
            logWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    
    public String getPermissionRequestId() {
        String logMessage = "Accessed permissionRequestId: " + permissionRequestId;
        writeLog(logMessage);
        return permissionRequestId;
    }

    public void setPermissionRequestId(String permissionRequestId) {
        this.permissionRequestId = permissionRequestId;
        writeLog("Set permissionRequestId: " + permissionRequestId);
    }

    public String getEmployeeIdAsString() {
    return String.valueOf(this.employeeId);
}


    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        writeLog("Set employeeId: " + employeeId);
    }

    public String getRequestType() {
        String logMessage = "Accessed requestType: " + requestType;
        writeLog(logMessage);
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
        writeLog("Set requestType: " + requestType);
    }

    public String getDate() {
        String logMessage = "Accessed date: " + date;
        writeLog(logMessage);
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        writeLog("Set date: " + date);
    }

    public String getStatus() {
        String logMessage = "Accessed status: " + status;
        writeLog(logMessage);
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        writeLog("Set status: " + status);
    }

    public void submitPermissionRequest() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(permissionRequestId + "," + employeeId + "," + requestType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Permission request submitted successfully.");
        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
        }
        writeLog("Submitted permission request: " + permissionRequestId);
    }

    public static void approvePermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Approved");
        writeLog("Approved permission request: " + permissionRequestId);
    }

    public static void rejectPermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Rejected");
        writeLog("Rejected permission request: " + permissionRequestId);
    }

    public static List<PermissionRequest> readAllFromFile() {
        List<PermissionRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    requests.add(new PermissionRequest(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading permission requests: " + e.getMessage());
        }
        writeLog("Read all permission requests from file");
        return requests;
    }

    public static void updateStatus(String permissionRequestId, String newStatus) {
        List<PermissionRequest> requests = readAllFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PermissionRequest request : requests) {
                if (request.getPermissionRequestId().equals(permissionRequestId)) {
                    request.setStatus(newStatus);
                }
                writer.write(request.getPermissionRequestId() + "," + request.getEmployeeId() + "," +
                        request.getRequestType() + "," + request.getDate() + "," + request.getStatus());
                writer.newLine();
            }
            System.out.println("Permission request updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating permission request: " + e.getMessage());
        }
        writeLog("Updated status for permission request: " + permissionRequestId);
    }

}
