package com.mycompany.project_pl2;


import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PermissionRequest extends Employee {

    private String permissionRequestId;
    private String requestType;
    private String date;
    private String status;
    
    private static final String FILE_NAME = "D:/Work/Projects/Java/PL/Project_PL/Project_PL2/filesPermissionRequest.txt";
    
    public PermissionRequest(String permissionRequestId,String name, String role, int employeeId, String requestType, String date, String status) {
        super(employeeId, name, role);
        this.permissionRequestId = permissionRequestId;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
        writeLog("Created permission request object: " + permissionRequestId);
    }
    
        // Static method to write log messages
    public String getPermissionRequestId() {
        String logMessage = "Accessed permissionRequestId: " + permissionRequestId;
        writeLog(logMessage);
        return permissionRequestId;
    }

    public void setPermissionRequestId(String permissionRequestId) {
        this.permissionRequestId = permissionRequestId;
        writeLog("Set permissionRequestId: " + permissionRequestId);
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
            writer.write(permissionRequestId + "," + super.getEmployeeId() + "," + requestType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Permission request submitted successfully.");
        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
        }
        writeLog("Submitted permission request: " + permissionRequestId);
    }

    public  void approvePermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Approved");
       writeLog("Approved permission request: " + permissionRequestId);
    }

    public  void rejectPermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Rejected");
        writeLog("Rejected permission request: " + permissionRequestId);
    }

    public  List<PermissionRequest> readAllFromFile() {
        List<PermissionRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    requests.add(new PermissionRequest(parts[0], parts[1], parts[2],Integer.parseInt(parts[3]), parts[4], parts[5], parts[6]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading permission requests: " + e.getMessage());
        }
        writeLog("Read all permission requests from file");
        return requests;
    }

    public  void updateStatus(String permissionRequestId, String newStatus) {
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
