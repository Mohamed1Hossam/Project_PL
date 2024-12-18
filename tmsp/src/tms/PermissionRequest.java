package tms;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PermissionRequest extends Employee {

    private int permissionRequestId;
    private int epid;
    private String requestType;
    private String date;
    private String status;
    
    private static final String LOG_FILE_NAME = "log.txt";
    private static final String FILE_NAME = "PermissionRequest.txt";
    
    public PermissionRequest(int permissionRequestId,String name, String role, int employeeId, String requestType, String date, String status) {
        super(employeeId, name, role);
        this.permissionRequestId = permissionRequestId;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
        writeLog("Created permission request object: " + permissionRequestId);
    }
    public PermissionRequest(int permissionRequestId,int employeeId, String requestType) {
        this.permissionRequestId = permissionRequestId;
        this.requestType = requestType;
        this.epid=employeeId;
        LocalDateTime x= LocalDateTime.now();
        this.date = x.toLocalDate().toString();
        this.status = "Pending";
        writeLog("Created permission request object: " + permissionRequestId);
    }
    
    
    public PermissionRequest(){
        
    }
    
    
        // Static method to write log messages
    
    
    public int getPermissionRequestId() {
        String logMessage = "Accessed permissionRequestId: " + permissionRequestId;
        writeLog(logMessage);
        return permissionRequestId;
    }

    public void setPermissionRequestId(int permissionRequestId) {
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
            writer.write(permissionRequestId + "," +getEmployeeId() + "," + requestType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Permission request submitted successfully.");
            writeLog("Submitted permission request: " + permissionRequestId);

        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
            writeLog("Error submitting permission request: " + e.getMessage());
        }
    }

    
    public void submitPermissionRequestG() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(permissionRequestId + "," +epid + "," + requestType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Permission request submitted successfully.");
            writeLog("Submitted permission request: " + permissionRequestId);

        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
            writeLog("Error submitting permission request: " + e.getMessage());
        }
    }
    
    
    public  void approvePermissionRequest(int permissionRequestId) {
        updateStatus(permissionRequestId, "Approved");
        writeLog("Approved permission request: " + permissionRequestId);
    }

    public  void rejectPermissionRequest(int permissionRequestId) {
        updateStatus(permissionRequestId, "Rejected");
        writeLog("Rejected permission request: " + permissionRequestId);
    }

    public List<PermissionRequest> readRequestsFromFile() {
        List<PermissionRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                 if (parts.length == 7) {
                    requests.add(new PermissionRequest(Integer.parseInt(parts[0]), parts[1], parts[2],Integer.parseInt(parts[3]), parts[4], parts[5], parts[6]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading permission requests: " + e.getMessage());
        }
        writeLog("Read all permission requests from file");
        return requests;
    }
    
    
    public String readFromFileG() {
         StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n\n"); // Append each line with a newline
            }
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage(); // Return the error message as the content
        }
        return content.toString();
    }

    public  void updateStatus(int permissionRequestId, String newStatus) {
        List<PermissionRequest> requests = readRequestsFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PermissionRequest request : requests) {
                if (request.getPermissionRequestId()==permissionRequestId) {
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
    
    
    
     public  String viewProjectDetails(String projectId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String data = currentLine;
                if (data.contains(projectId)) {
                    
                    writeLog("Viewed details for permission Request: " + projectId);
                    return data;
                }
            }

            System.out.println("permission Request ID not found.");
            writeLog("permission Request ID not found for view: " + projectId);

        } catch (IOException e) {
            System.out.println("Error while reading permission Request: " + e.getMessage());
            writeLog("Error reading Leave Request: " + projectId + " - " + e.getMessage());
        }
        String x="This Employee Doesn't Have permission Request";
                            return x;

    }
    
    
    
    
    
    
    
    public void updatea(String id) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (currentLine.contains(id)) {
                    String s=currentLine;
                    // Construct the new line with updated information
                    writer.println(s.replaceAll("Pending", "Approved"));
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
    
    
    public void updater(String id) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (currentLine.contains(id)) {
                    String s=currentLine;
                    // Construct the new line with updated information
                    writer.println(s.replaceAll("Pending", "Rejected"));
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
