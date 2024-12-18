package tms;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectManagement extends Employee {

    private int projectId;
    private String clientName;
    private String startDate;
    private String endDate;

    private static final String FILE_NAME = "projects.txt";

    public ProjectManagement(int projectId, String clientName, String startDate, String endDate) {
        this.projectId = projectId;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        writeLog("Created new project: " + projectId);
    }
    
    public ProjectManagement(){
        
    }

    public int getProjectId() {
        writeLog("Accessed projectId: " + projectId);
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        writeLog("Set projectId: " + projectId);
    }

    public String getClientName() {
        writeLog("Accessed clientName: " + clientName);
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        writeLog("Set clientName: " + clientName);
    }

    public String getStartDate() {
        writeLog("Accessed startDate: " + startDate);
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        writeLog("Set startDate: " + startDate);
    }

    public String getEndDate() {
        writeLog("Accessed endDate: " + endDate);
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        writeLog("Set endDate: " + endDate);
    }

    public void addProject() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(toDataString());
            writer.newLine();
            System.out.println("Project added successfully.");
            writeLog("Added project: " + projectId);
        } catch (IOException e) {
            System.out.println("Error adding project: " + e.getMessage());
            writeLog("Error adding project: " + projectId + " - " + e.getMessage());
        }
    }

    public  void updateProject(String projectId, ProjectManagement updatedProject) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_projects.txt");
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].trim().equals(projectId.trim())) {
                    writer.write(updatedProject.toDataString());
                    updated = true;
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }

            if (updated) {
                System.out.println("Project updated successfully.");
                writeLog("Updated project: " + projectId);
            } else {
                System.out.println("Project ID not found for update.");
                writeLog("Project ID not found for update: " + projectId);
            }

        } catch (IOException e) {
            System.out.println("Error while updating project: " + e.getMessage());
            writeLog("Error updating project: " + projectId + " - " + e.getMessage());
        }

        if (inputFile.exists() && !inputFile.delete()) {
            System.out.println("Failed to delete original file. Check file permissions.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Failed to rename temp file. Check file permissions.");
        }
    }

    public  void deleteProject(String projectId) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_projects.txt");
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!data[0].trim().equals(projectId.trim())) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                System.out.println("Project deleted successfully.");
                writeLog("Deleted project: " + projectId);
            } else {
                System.out.println("Project ID not found for deletion.");
                writeLog("Project ID not found for deletion: " + projectId);
            }

        } catch (IOException e) {
            System.out.println("Error while deleting project: " + e.getMessage());
            writeLog("Error deleting project: " + projectId + " - " + e.getMessage());
        }

        if (inputFile.exists() && !inputFile.delete()) {
            System.out.println("Failed to delete original file. Check file permissions.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Failed to rename temp file. Check file permissions.");
        }
    }

    public  String[] viewProjectDetails(String projectId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(projectId)) {
                    System.out.println("Project ID: " + data[0]);
                    System.out.println("Client Name: " + data[1]);
                    System.out.println("Start Date: " + data[2]);
                    System.out.println("End Date: " + data[3]);
                    writeLog("Viewed details for project: " + projectId);
                    return data;
                }
            }

            System.out.println("Project ID not found.");
            writeLog("Project ID not found for view: " + projectId);

        } catch (IOException e) {
            System.out.println("Error while reading project: " + e.getMessage());
            writeLog("Error reading project: " + projectId + " - " + e.getMessage());
        }
        String [] x="This Project Doesn't Exist".split(" ");
                            return x;

    }

    public  List<String> getAllProjects() {
        List<String> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                projects.add(currentLine);
            }

        } catch (IOException e) {
            System.out.println("Error reading all projects: " + e.getMessage());
            writeLog("Error reading all projects: " + e.getMessage());
        }
        writeLog("Retrieved all projects.");
        return projects;
    }

    private String toDataString() {
        return projectId + "," + clientName + "," + startDate + "," + endDate;
    }

   
}
