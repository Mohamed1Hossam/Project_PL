import java.io.*;
import java.util.*;

public class ProjectManagement extends ManagementSystem {
    
    private String projectId;
    private String clientName;
    private Date startDate;
    private Date endDate;

    private static final String FILE_NAME = "../ProjectManagment/projects.txt";

    public ProjectManagement(String id, String name, Date createdDate, Date modifiedDate,
                             String projectId, String clientName, Date startDate, Date endDate) {
        super(id, name, createdDate, modifiedDate);
        this.projectId = projectId;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addProject() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../ProjectManagment/projects.txt", true))) {
            writer.write(toDataString());
            writer.newLine();
            System.out.println("Project saved to file.");
        } catch (IOException e) {
            System.out.println("Error while saving project: " + e.getMessage());
        }
    }

    public void updateProject(String projectId) {
        File inputFile = new File("../ProjectManagment/projects.txt");
        File tempFile = new File("../ProjectManagment/temp_projects.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean updated = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(projectId)) {
                    writer.write(toDataString());
                    updated = true;
                } else {
                    writer.write(currentLine);
                }
                writer.newLine();
            }

            if (updated) {
                System.out.println("Project updated successfully.");
            } else {
                System.out.println("Project ID not found.");
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error updating project file.");
            }

        } catch (IOException e) {
            System.out.println("Error while updating project: " + e.getMessage());
        }
    }

    public void deleteProject(String projectId) {
        File inputFile = new File("../ProjectManagment/projects.txt");
        File tempFile = new File("../ProjectManagment/temp_projects.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean deleted = false;

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
            } else {
                System.out.println("Project ID not found.");
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Error deleting project file.");
            }

        } catch (IOException e) {
            System.out.println("Error while deleting project: " + e.getMessage());
        }
    }

    public void viewProjectDetails(String projectId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("../ProjectManagment/projects.txt"))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(projectId)) {
                    System.out.println("Project ID: " + data[0]);
                    System.out.println("Client Name: " + data[1]);
                    System.out.println("Start Date: " + new Date(Long.parseLong(data[2])));
                    System.out.println("End Date: " + new Date(Long.parseLong(data[3])));
                    return;
                }
            }

            System.out.println("Project ID not found.");
        } catch (IOException e) {
            System.out.println("Error while reading project: " + e.getMessage());
        }
    }

    private String toDataString() {
        return projectId + "," + clientName + "," + startDate.getTime() + "," + endDate.getTime();
    }
}
