import java.io.*;
import java.util.*;

public class PermissionRequest {

    private String permissionRequestId;
    private String employeeId;
    private String requestType;
    private String date;
    private String status;

    private static final String FILE_NAME = "../PermissionRequest/PermissionRequest.txt";

    public PermissionRequest(String permissionRequestId, String employeeId, String requestType, String date, String status) {
        this.permissionRequestId = permissionRequestId;
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.date = date;
        this.status = status;
    }

    public String getPermissionRequestId() {
        return permissionRequestId;
    }

    public void setPermissionRequestId(String permissionRequestId) {
        this.permissionRequestId = permissionRequestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Submit Permission Request (saves the request to file)
    public void submitPermissionRequest() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../PermissionRequest/PermissionRequest.txt", true))) {
            writer.write(permissionRequestId + "," + employeeId + "," + requestType + "," + date + "," + status);
            writer.newLine();
            System.out.println("Permission request submitted successfully.");
        } catch (IOException e) {
            System.out.println("Error submitting permission request: " + e.getMessage());
        }
    }

    // Approve Permission Request (updates the status to 'Approved')
    public static void approvePermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Approved");
    }

    // Reject Permission Request (updates the status to 'Rejected')
    public static void rejectPermissionRequest(String permissionRequestId) {
        updateStatus(permissionRequestId, "Rejected");
    }

    // View All Permission Requests
    public static List<PermissionRequest> readFromFile() {
        List<PermissionRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("../PermissionRequest/PermissionRequest.txt"))) {
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
        return requests;
    }

    // Update a Permission Request status
    public static void updateStatus(String permissionRequestId, String newStatus) {
        List<PermissionRequest> requests = readFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../PermissionRequest/PermissionRequest.txt"))) {
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
    }
}
