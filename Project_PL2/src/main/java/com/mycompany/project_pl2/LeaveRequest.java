package com.mycompany.project_pl2;

import java.io.*;

public class LeaveRequest extends Employee {
    private int LRid;
    private String LRtype, LRstartDate, LRendDate, LRstatus;
    private static final String LRfile = "D:/Work/Projects/Java/PL/Project_PL/Project_PL2/files/LR.txt"; // Configurable path

    public LeaveRequest() {
        super(0, "Default Name", "Default Role");
    }

    public LeaveRequest(int employeeId, String name, String role, int LRid, String LRtype, String LRstartDate, String LRendDate) {
        super(employeeId, name, role); // Call the parent Employee constructor
        this.LRid = LRid;
        this.LRtype = LRtype;
        this.LRstartDate = LRstartDate;
        this.LRendDate = LRendDate;
        this.LRstatus = "Pending";
        saveLR();
    }

    // Getters and Setters
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
    }

    public String getLRstartDate() {
        return LRstartDate;
    }

    public void setLRstartDate(String LRstartDate) {
        this.LRstartDate = LRstartDate;
    }

    public String getLRendDate() {
        return LRendDate;
    }

    public void setLRendDate(String LRendDate) {
        this.LRendDate = LRendDate;
    }

    public String getLRstatus() {
        return LRstatus;
    }

    public void setLRstatus(String LRstatus) {
        this.LRstatus = LRstatus;
    }

    // Save leave request to file
    private void saveLR() {
        String record = String.format("EmployeeID=%d---Name=%s---Role=%s---LRid=%d---LRtype=%s---LRstartDate=%s---LRendDate=%s---LRstatus=%s",
                getEmployeeId(), getName(), getRole(), LRid, LRtype, LRstartDate, LRendDate, LRstatus);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LRfile, true))) {
            writer.write(record);
            writer.newLine();
            System.out.println("Leave request saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving leave request: " + e.getMessage());
        }
    }

    // Update leave request
    public void updateLR(int LRid, String newStatus) {
        File inputFile = new File(LRfile);
        File tempFile = new File("D:/Work/Projects/Java/PL/Project_PL/Project_PL2/files/temp_LR.txt");

        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("LRid=" + LRid)) {
                    currentLine = currentLine.replaceFirst("LRstatus=\\w+", "LRstatus=" + newStatus);
                    updated = true;
                }
                writer.write(currentLine);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error updating leave request: " + e.getMessage());
            return;
        }

        if (updated) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Leave request updated successfully.");
            } else {
                System.out.println("Error renaming file after update.");
            }
        } else {
            System.out.println("Leave request ID not found.");
            tempFile.delete();
        }
    }

    // Delete leave request
    public void deleteLR(int LRid) {
        File inputFile = new File(LRfile);
        File tempFile = new File("D:/Work/Projects/Java/PL/Project_PL/Project_PL2/files/temp_LR.txt");

        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains("LRid=" + LRid)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting leave request: " + e.getMessage());
            return;
        }

        if (deleted) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                System.out.println("Leave request deleted successfully.");
            } else {
                System.out.println("Error renaming file after deletion.");
            }
        } else {
            System.out.println("Leave request ID not found.");
            tempFile.delete();
        }
    }

    // Approve leave request
    public void approveLR() {
        this.LRstatus = "Approved";
        updateLR(this.LRid, "Approved");
    }

    // Reject leave request
    public void rejectLR() {
        this.LRstatus = "Rejected";
        updateLR(this.LRid, "Rejected");
    }

    // Display leave request details
    public void getDetails() {
        System.out.println("Leave Request ID: " + LRid);
        System.out.println("Leave Request Type: " + LRtype);
        System.out.println("Leave Request Start Date: " + LRstartDate);
        System.out.println("Leave Request End Date: " + LRendDate);
        System.out.println("Leave Request Status: " + LRstatus);
    }

    void submitLR() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
