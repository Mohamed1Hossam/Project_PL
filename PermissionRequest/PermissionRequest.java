import java.io.*;
import java.util.*;

public class PermissionRequest extends Employee {

    private String permissionRequestId;
    private String employeeId;
    private String requestType;
    private String date;
    private String status;

    private static final String FILE_NAME = "../PermissionRequest/PermissionRequest.txt";
    private static final String LOG_FILE = "../files/Log.txt";

    public PermissionRequest(String permissionRequestId, String employeeId, String requestType, String date, String status) {
        this.permissionRequestId = permissionRequestId;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
        writeLog("Created permission request object: " + permissionRequestId);
    }

    public String getPermissionRequestId() {
        writeLog("Accessed permissionRequestId: " + permissionRequestId);
        return permissionRequestId;
    }

    public void setPermissionRequestId(String permissionRequestId) {
        this.permissionRequestId = permissionRequestId;
        writeLog("Set permissionRequestId: " + permissionRequestId);
    }

    public String getEmployeeId() {
        writeLog("Accessed employeeId: " + employeeId);
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        writeLog("Set employeeId: " + employeeId);
    }

    public String getRequestType() {
        writeLog("Accessed requestType: " + requestType);
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
        writeLog("Set requestType: " + requestType);
    }

    public String getDate() {
        writeLog("Accessed date: " + date);
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        writeLog("Set date: " + date);
    }

    public String getStatus() {
        writeLog("Accessed status: " + status);
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
            writeLog("Permission request submitted: " + permissionRequestId);
        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
            writeLog("Error submitting permission request: " + e.getMessage());
        }
    }

    public static void approvePermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Approved");
        writeLog("Permission request approved: " + permissionRequestId);
    }

    public static void rejectPermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Rejected");
        writeLog("Permission request rejected: " + permissionRequestId);
    }

    public static List<PermissionRequest> readFromFile() {
        List<PermissionRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    requests.add(new PermissionRequest(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
            writeLog("Read all permission requests from file");
        } catch (IOException e) {
            System.out.println("Error reading permission requests: " + e.getMessage());
            writeLog("Error reading permission requests: " + e.getMessage());
        }
        return requests;
    }

    public static void updateStatus(String permissionRequestId, String newStatus) {
        List<PermissionRequest> requests = readFromFile();
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
            writeLog("Permission request updated: " + permissionRequestId);
        } catch (IOException e) {
            System.out.println("Error updating permission request: " + e.getMessage());
            writeLog("Error updating permission request: " + e.getMessage());
        }
    }

    private static void writeLog(String log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(new Date() + " - " + log);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
