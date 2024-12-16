package tms;

import java.util.List;

public class Admin extends TaskManagementSystem implements LoginManager{
    private FileManager employeeFileManager;
    private FileManager taskFileManager;
    private FileManager userFileManager;
    private String loggedInUser = null;

    public Admin(FileManager employeeFileManager, FileManager taskFileManager, FileManager userFileManager) {
        this.employeeFileManager = employeeFileManager;
        this.taskFileManager = taskFileManager;
        this.userFileManager = userFileManager;
    }
     @Override
    public int loginUser(String username, String password) {
        List<String> users = userFileManager.readFromFile(); // Read users from file
        int loggedIn = -1;

        for (String user : users) {
            String[] credentials = user.split(",");
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                if (credentials.length == 3 && credentials[2].equals("Admin")) {
                loggedIn = 1; // Correct username, password, and role "Admin"
            } else {
                loggedIn = 2; // Correct username and password but role is not "Admin"
            }
                loggedInUser = username; // Set logged-in user
                System.out.println("Login successful for user: " + username);
                break;
            }
            
        }

        if (loggedIn==-1) {
            System.out.println("Invalid username or password.");
        }

        return loggedIn;
    }
    
    
       @Override
    public void logoutUser() {
        if (loggedInUser != null) {
            System.out.println(loggedInUser + " logged out.");
            loggedInUser = null; // Clear logged-in user
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

   
    @Override
    public boolean checkLogin() {
        if (loggedInUser == null) {
            System.out.println("You must log in first.");
            return false;
        }
        return true;
    }

    public boolean updateEmployeeID(String id, String id2) {
        List<String> employees = employeeFileManager.readFromFile();
        boolean updated = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).contains(id)) {  
                employees.set(i, employees.get(i).replace(id, id2));
                updated = true;
                break;
            }
        }

        if (updated) {
            employeeFileManager.writeToFile(employees);
            System.out.println("Employee name updated successfully.");
        } else {
            System.out.println("Employee name not found.");
        }
        return updated;
    }

    public boolean deleteEmployee(String nameToDelete) {
        List<String> employees = employeeFileManager.readFromFile();
        boolean deleted = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).contains(nameToDelete)) {  
                employees.remove(i);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            employeeFileManager.writeToFile(employees);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
        return deleted;
    }

    public void addEmployee(Employee employee) {
        List<String> employees = employeeFileManager.readFromFile();
        employees.add(employee.toString());
        employeeFileManager.writeToFile(employees);
        System.out.println("Employee added successfully.");
    }

    
    public void addTask(Task task) {
        List<String> tasks = taskFileManager.readFromFile();
        tasks.add(task.toString());
        taskFileManager.writeToFile(tasks);
        System.out.println("Task added successfully.");
    }

    
    public boolean deleteTask(String taskId) {
        List<String> tasks = taskFileManager.readFromFile();
        boolean deleted = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(taskId)) {  
                tasks.remove(i);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            taskFileManager.writeToFile(tasks);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
        return deleted;
    }

   
   public boolean updateTaskPhase(String taskId, String newTaskName, String newStatus) {
        List<String> tasks = taskFileManager.readFromFile();
        boolean updated = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(taskId)) {  
            String updatedTask = tasks.get(i).replaceFirst("^" + taskId + ":.*", taskId + ": " + newTaskName + " - " + newStatus);
                tasks.set(i, updatedTask);
                updated = true;
                break;
            }
        }

        if (updated) {
            taskFileManager.writeToFile(tasks);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Task not found.");
        }
        return updated;
    }
}
