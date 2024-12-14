package com.mycompany.project_pl2;


import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectManagement extends Employee {

    private String projectId;
    private String clientName;
    private Date startDate;
    private Date endDate;

    private static final String LOG_FILE_NAME = "files/log.txt";
    private static final String FILE_NAME = "files/projects.txt";

    public ProjectManagement(String projectId, String clientName, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        writeLog("Created new project: " + projectId);
    }

    public String getProjectId() {
        writeLog("Accessed projectId: " + projectId);
        return projectId;
    }

    public void setProjectId(String projectId) {
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

    public Date getStartDate() {
        writeLog("Accessed startDate: " + startDate);
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        writeLog("Set startDate: " + startDate);
    }

    public Date getEndDate() {
        writeLog("Accessed endDate: " + endDate);
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public static void updateProject(String projectId, ProjectManagement updatedProject) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("files/temp_projects.txt");
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

    public static void deleteProject(String projectId) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("files/temp_projects.txt");
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

    public static void viewProjectDetails(String projectId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(projectId)) {
                    System.out.println("Project ID: " + data[0]);
                    System.out.println("Client Name: " + data[1]);
                    System.out.println("Start Date: " + new Date(Long.parseLong(data[2])));
                    System.out.println("End Date: " + new Date(Long.parseLong(data[3])));
                    writeLog("Viewed details for project: " + projectId);
                    return;
                }
            }

            System.out.println("Project ID not found.");
            writeLog("Project ID not found for view: " + projectId);

        } catch (IOException e) {
            System.out.println("Error while reading project: " + e.getMessage());
            writeLog("Error reading project: " + projectId + " - " + e.getMessage());
        }
    }

    public static List<String> getAllProjects() {
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
        return projectId + "," + clientName + "," + startDate.getTime() + "," + endDate.getTime();
    }

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
}
