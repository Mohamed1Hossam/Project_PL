package tms;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class LeaveRequest extends Employee {
    
    private int LRid,employeeID;
    private String LRtype, LRstartDate, LRendDate, LRstatus;
    private static final String LRfile = "LR.txt"; //same as in super but for L requests..don't know if it's necceasry or not

    public LeaveRequest() {
        this.LRstatus = "Pending";

    }

    public LeaveRequest(int employeeId, String name, String role, int LRid, String LRtype, String LRstartDate, String LRendDate) {
        ///make sure the names match the ones in the employee classs
        super(employeeId, name, role);
        this.LRendDate = LRendDate;
        this.LRid = LRid;
        this.LRtype = LRtype;
        this.LRstartDate = LRstartDate;
        this.LRstatus = "Pending";
        ///remove if LR file not needed :b
        saveLR();
    }
    
        public LeaveRequest(int employeeId, int LRid, String LRtype) {
        this.LRid = LRid;
        this.employeeID = employeeId;
        this.LRtype = LRtype;
        LocalDateTime x= LocalDateTime.now();
        this.LRstartDate = x.toLocalDate().toString();
        this.LRstatus = "Pending";
        ///remove if LR file not needed :b
        saveLRG();
    }

    public int getLRId() {
        return LRid;
    }

    public void setLRId(int LRid) {
        this.LRid = LRid;
    }

    public String getLRtype() {
        return LRtype;
    }

    public void setLRtype(String LRtype) {
        this.LRtype = LRtype;
        update(getEmployeeId(), getName(), getRole(), LRid);

    }

    public String getLRstartDate() {
        return LRstartDate;
    }

    public void setLRstartDate(String LRstartDate) {
        this.LRstartDate = LRstartDate;
        update(getEmployeeId(), getName(), getRole(), LRid);

    }

    public String getLRendDate() {
        return LRendDate;
    }

    public void setLRendDate(String LRendDate) {
        this.LRendDate = LRendDate;
        update(getEmployeeId(), getName(), getRole(), LRid);

    }

    public String getLRstatus() {
        return LRstatus;
    }

    public void setLRstatus(String LRstatus) {
        this.LRstatus = LRstatus;
        update(getEmployeeId(), getName(), getRole(), LRid);

    }

    public void SumbitLR() {
        saveLR();

    }

    public void create(int employeeId, String name, String role, int LRid, String LRtype, String LRstartDate, String LRendDate) {
        setEmployeeId(employeeId);
        setName(name);
        setRole(role);
        this.LRendDate = LRendDate;
        this.LRid = LRid;
        this.LRtype = LRtype;
        this.LRstartDate = LRstartDate;
        this.LRstatus = "Pending";
    }

    public void update(int employeeId, String name, String role, int LRid) {
        File inputFile = new File(LRfile);
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(" --- ");
                if (currentLine.contains(" --- LRid = " + LRid)) {

                    // Construct the new line with updated information
                    writer.println("Employee ID =" + getEmployeeId() + " --- Name = " + getName() + " --- Role = " + getRole() + " --- LRid = " + LRid + " --- LRtype = " + LRtype + " --- LRstartDate = " + LRstartDate + " --- LRendDate = " + LRendDate + " --- LRstatus = " + LRstatus);
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

//   @Override
//   public  void delete(int LRid){
//       this.LRid=-1;
//       File inputFile = new File(LRfile);
//    File tempFile = new File("temp_users.txt");
//
//    boolean deleted = false;
//
//    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
//
//        String currentLine;
//
//        while ((currentLine = reader.readLine()) != null) {
//            String[] data = currentLine.split("---");
//            if (data[0].trim().equals("Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid)) {
//                writer.write(currentLine);
//                writer.newLine();
//            } else {
//                deleted = true;
//            }
//        }
//    } catch (IOException e) {
//        System.out.println("Error while deleting user: " + e.getMessage());
//        super.writeLog("Error while deleting user: " + e.getMessage());
//        return;
//    }
//
//    if (deleted) {
//        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
//            System.out.println("User deleted successfully.");
//            super.writeLog("User deleted successfully.");
//        } else {
//            System.out.println("Error deleting user file.");
//            super.writeLog("Error deleting user file.");
//        }
//    } else {
//        System.out.println("User ID not found.");
//        super.writeLog("User ID not found.");
//        tempFile.delete();
//    }
//}
    public void saveLR() {
        String p = "Employee ID =" + getEmployeeId() + " --- Name = " + getName() + " --- Role = " + getRole() + " --- LRid = " + LRid + " --- LRtype = " + LRtype + " --- LRstartDate = " + LRstartDate + " --- LRendDate = " + LRendDate + " --- LRstatus = " + LRstatus;

        writeLog(p + " was saved to " + LRfile);
        try {
            File file = new File(LRfile);
            boolean userExists = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Employee ID =" + getEmployeeId() + " --- Name = " + getName() + " --- Role = " + getRole() + " --- LRid = " + LRid)) {
                        userExists = true;
                        break;
                    }
                }
            }

            if (!userExists) {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println(p);
                    System.out.println("User saved successfully.");
                    writeLog("User saved successfully.");
                }
            } else {
                System.out.println(p + " already exists.");
                writeLog(p + " already exists.");
            }
        } catch (IOException ex) {
            System.out.println(" wtf? Error in the saveUser function: " + ex.getMessage());
            writeLog("Error in the saveUser function: " + ex.getMessage());
        }

    }
    
    
    
    public void saveLRG() {
        String p = "Employee ID =" + employeeID + " --- LRid = " + LRid + " --- LRtype = " + LRtype + " --- LRstartDate = " + LRstartDate + " --- LRendDate = " + "Unknown" + " --- LRstatus = " + LRstatus;

        writeLog(p + " was saved to " + LRfile);
        try {
            File file = new File(LRfile);
            boolean userExists = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Employee ID =" + employeeID+ " --- LRid = " + LRid)) {
                        userExists = true;
                        break;
                    }
                }
            }

            if (!userExists) {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println(p);
                    System.out.println("User saved successfully.");
                    writeLog("User saved successfully.");
                }
            } else {
                System.out.println(p + " already exists.");
                writeLog(p + " already exists.");
            }
        } catch (IOException ex) {
            System.out.println(" wtf? Error in the saveUser function: " + ex.getMessage());
            writeLog("Error in the saveUser function: " + ex.getMessage());
        }

    }
    

    public void approveLR() {

        this.LRstatus = "Approved";
        update(getEmployeeId(), getName(), getRole(), LRid);

    }
    
    
    
     public  String viewProjectDetails(String projectId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LRfile))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String data = currentLine;
                if (data.contains("LRid = " + projectId)) {
                    
                    writeLog("Viewed details for Leave Request: " + projectId);
                    return data;
                }
            }

            System.out.println("Leave Request ID not found.");
            writeLog("Leave Request ID not found for view: " + projectId);

        } catch (IOException e) {
            System.out.println("Error while reading Leave Request: " + e.getMessage());
            writeLog("Error reading Leave Request: " + projectId + " - " + e.getMessage());
        }
        String x="This Employee Doesn't Have Leave Request";
                            return x;

    }
    
    
    
    

    public void rejectLR() {
        this.LRstatus = "Rejected";
        update(getEmployeeId(), getName(), getRole(), LRid);

    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Leave Request ID: " + LRid);
        System.out.println("Leave Request Type: " + LRtype);
        System.out.println("Leave Request Start Date: " + LRstartDate);
        System.out.println("Leave Request End Date: " + LRendDate);
        System.out.println("Leave Request Status: " + LRstatus);

    }

    public String readFromFileG() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(LRfile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n\n\n"); // Append each line with a newline
            }
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage(); // Return the error message as the content
        }
        return content.toString();
    }

//    public void approveLR(String id) {
//        File file = new File(LRfile);
//        File tempFile = new File(file.getAbsolutePath() + ".tmp");
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file)); PrintWriter writer = new PrintWriter(new FileOutputStream(tempFile))) {
//            String currentLine;
//            boolean found = false;
//
//            while ((currentLine = reader.readLine()) != null) {
//                String s = currentLine;
//                if (s.contains("LRid = " + id)) {
//                    found = true;
//                    // Modify the status to "approved" for the found record
//                    s = s.replaceAll("LRstatus = Pending", "LRstatus = Approved");
//                }
//                writer.println(s);
//            }
//
//            if (found) {
//                if (file.delete() && tempFile.renameTo(file)) {
//                    System.out.println("User updated successfully.");
//                    writeLog("User updated successfully.");
//                } else {
//                    System.out.println("Error updating user file.");
//                    writeLog("Error updating user file.");
//                }
//            } else {
//                System.out.println("Leave record with ID " + id + " not found.");
//                writeLog("Leave record with ID " + id + " not found.");
//            }
//
//        } catch (IOException ex) {
//            System.out.println("Error in the approveLR function: " + ex.getMessage());
//            writeLog("Error in the approveLR function: " + ex.getMessage());
//        }
//    }

    
    
    
    
    
    
    
    
     public void updatea(String LRid) {
        File inputFile = new File(LRfile);
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(" --- ");
                if (currentLine.contains(" --- LRid = " + LRid)) {
                    String s=currentLine;
                    // Construct the new line with updated information
                    writer.println(s.replaceAll("LRstatus = Pending", "LRstatus = Approved"));
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
    
    
    public void updater(String LRid) {
        File inputFile = new File(LRfile);
        File tempFile = new File("temp_users.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(" --- ");
                if (currentLine.contains(" --- LRid = " + LRid)) {
                    String s=currentLine;
                    // Construct the new line with updated information
                    writer.println(s.replaceAll("LRstatus = Pending", "LRstatus = Rejected"));
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
