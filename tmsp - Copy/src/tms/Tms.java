
package tms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        FileManager employeeFileManager = new FileManager("employee.txt");
        FileManager taskFileManager = new FileManager("tasks.txt");
        FileManager userloginFileManager = new FileManager("userslogin.txt");
       

        // Admin instance
        Admin admin = new Admin(employeeFileManager, taskFileManager, userloginFileManager);

        // Test login functionality
        System.out.println("Testing login functionality...");
        boolean loginSuccess = admin.loginUser("mina", "mina123");
        if (!loginSuccess) {
            System.out.println("Login test failed. Ensure the 'userslogin.txt' file contains the correct credentials.");
        }

        // Test employee management
        System.out.println("\nTesting employee management...");
        Employee newEmployee = new Employee(1, "John Doe", "Developer");
        admin.addEmployee(newEmployee);
        admin.updateEmployeeID("1", "2");  // Change ID from 1 to 2
        admin.deleteEmployee("John Doe");

        // Test task management
        System.out.println("\nTesting task management...");
        Task newTask = new Task(1, "Employee", 101, "Code Review", "Review PR #45");
        admin.addTask(newTask);
        admin.updateTaskPhase("101", "Code Testing", "In Progress");
        admin.deleteTask("101");
        
        
        
        
        
        
        
        
        
        
        
        
        
System.out.println("\nTesting employee management...");
        Employee newwEmployee = new Employee(1, "John Doe", "Developer");
        

        // Test attendance functionality
        System.out.println("\nTesting attendance functionality...");
        try {
            Date now = new Date();
            Attendance attendance = new Attendance(1, 2, now, "09:00 AM", "05:00 PM");
            attendance.saveToFile();
            
        } catch (Exception e) {
            System.out.println("Attendance test failed: " + e.getMessage());
        }

        // Test leave requests
        System.out.println("\nTesting leave request functionality...");
        LeaveRequest leaveRequest = new LeaveRequest(2, "Jane Smith", "Designer", 101, "Sick Leave", "2024-12-20", "2024-12-22");
        leaveRequest.approveLR();
        leaveRequest.delete(101);

        // Test project management
        System.out.println("\nTesting project management...");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ProjectManagement project = new ProjectManagement("PRJ101", "Acme Corp", sdf.parse("2024-01-01"), sdf.parse("2024-12-31"));
            project.addProject();
            project.updateProject("PRJ101", new ProjectManagement("PRJ101", "Acme Corp Updated", sdf.parse("2024-01-01"), sdf.parse("2025-01-01")));
            project.deleteProject("PRJ101");
        } catch (Exception e) {
            System.out.println("Project management test failed: " + e.getMessage());
        }

        // Test task phase management
        System.out.println("\nTesting task phase management...");
        TaskPhase taskPhase = new TaskPhase("TSK101", "Initial Setup", "To Do");
        System.out.println(taskPhase);

        // Test permission requests
        System.out.println("\nTesting permission request functionality...");
        PermissionRequest permissionRequest = new PermissionRequest("PRM101", "Jane Smith", "Designer", 2, "Work From Home", "2024-12-21", "Pending");
        permissionRequest.submitPermissionRequest();
        permissionRequest.approvePermissionRequest("PRM101");    }
    
}
