import java.io.*;
import java.util.*;

public class ProjectManagement extends Employee {

    private String projectId;
    private String clientName;
    private Date startDate;
    private Date endDate;

    private static final String FILE_NAME = "../files/projects.txt";

    public ProjectManagement(String projectId, String clientName, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        writeLog("Created Project: " + projectId);
    }

    public String getProjectId() {
        writeLog("Accessed Project ID: " + projectId);
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
        writeLog("Updated Project ID to: " + projectId);
    }

    public String getClientName() {
        writeLog("Accessed Client Name for Project: " + projectId);
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        writeLog("Updated Client Name to: " + clientName);
    }

    public Date getStartDate() {
        writeLog("Accessed Start Date for Project: " + projectId);
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        writeLog("Updated Start Date for Project: " + projectId);
    }

    public Date getEndDate() {
        writeLog("Accessed End Date for Project: " + projectId);
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        writeLog("Updated End Date for Project: " + projectId);
    }

    public void addProject() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(toDataString());
            writer.newLine();
            System.out.println("Project added successfully.");
            writeLog("Added Project: " + projectId);
        } catch (IOException e) {
            System.out.println("Error adding project: " + e.getMessage());
            writeLog("Error adding Project: " + projectId + ", Error: " + e.getMessage());
        }
    }

    public static void updateProject(String projectId, ProjectManagement updatedProject) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("../files/temp_projects.txt");
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(projectId)) {
                    writer.write(updatedProject.toDataString());
                    updated = true;
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }

            if (updated) {
                System.out.println("Project updated successfully.");
                writeLog("Updated Project: " + projectId);
            } else {
                System.out.println("Project ID not found.");
                writeLog("Project ID not found for update: " + projectId);
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error updating project file.");
                writeLog("Error renaming project file during update.");
            }

        } catch (IOException e) {
            System.out.println("Error while updating project: " + e.getMessage());
            writeLog("Error updating project: " + e.getMessage());
        }
    }

    public static void deleteProject(String projectId) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("../files/temp_projects.txt");
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (!data[0].equals(projectId)) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                System.out.println("Project deleted successfully.");
                writeLog("Deleted Project: " + projectId);
            } else {
                System.out.println("Project ID not found.");
                writeLog("Project ID not found for deletion: " + projectId);
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error deleting project file.");
                writeLog("Error renaming project file during deletion.");
            }

        } catch (IOException e) {
            System.out.println("Error while deleting project: " + e.getMessage());
            writeLog("Error deleting project: " + e.getMessage());
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
                    writeLog("Viewed Project Details: " + projectId);
                    return;
                }
            }

            System.out.println("Project ID not found.");
            writeLog("Project ID not found for view: " + projectId);
        } catch (IOException e) {
            System.out.println("Error while reading project: " + e.getMessage());
            writeLog("Error reading project: " + e.getMessage());
        }
    }

    public static List<String> getAllProjects() {
        List<String> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                projects.add(currentLine);
            }
            writeLog("Accessed all projects.");
        } catch (IOException e) {
            System.out.println("Error reading all projects: " + e.getMessage());
            writeLog("Error reading all projects: " + e.getMessage());
        }
        return projects;
    }

    private String toDataString() {
        return projectId + "," + clientName + "," + startDate.getTime() + "," + endDate.getTime();
    }

}
