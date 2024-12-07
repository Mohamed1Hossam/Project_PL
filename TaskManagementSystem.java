
import java.io.*;
import java.util.*;

public abstract class TaskManagementSystem {

    private int id; //idk if it should be string or int.. :b
    private String userType; //that's the user type-- is it admin, emploee or what
    protected static final String logFile = "../files/Log.txt"; //that's the path of the file where the log will be stored...probably
    private static final String usersFile = "../files/users.txt"; //same but for user..don't know if it's necceasry or not

    //don't know if password is here or not
    public TaskManagementSystem() {

    }

    public TaskManagementSystem(int id, String userType) {
        this.id = id;
        this.userType = userType;
        writeLog("Create ID: " + id + " with Type: " + userType);
    }

    public int getId() {
        writeLog("ID: " + id + " was called");

        return id;
    }

    public void setId(int id) {
        writeLog("ID: " + id + " was set");

        this.id = id;
    }

    public String getType() {
        writeLog("Type of ID: " + id + " was called");

        return userType;
    }

    public void setType(String userType) {
        writeLog("Type for ID: " + id + " was set");

        this.userType = userType;
    }

    public void create(int id, String userType) {
        this.id = id;
        this.userType = userType;
        writeLog("Create ID: " + id + " with Type: " + userType);

    }

    public void update(int id, String usertype) {
    File inputFile = new File(usersFile);
    File tempFile = new File("temp_users.txt");

    boolean updated = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] data = currentLine.split("---");
            if (data[0].trim().equals("User: " + id)) {
                writer.write("User: " + id + " --- Type: " + usertype);
                updated = true;
            } else {
                writer.write(currentLine);
            }
            writer.newLine();
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



   // public abstract void read();

   public void delete(int id) {
    File inputFile = new File(usersFile);
    File tempFile = new File("temp_users.txt");

    boolean deleted = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] data = currentLine.split("---");
            if (!data[0].trim().equals("User: " + id)) {
                writer.write(currentLine);
                writer.newLine();
            } else {
                deleted = true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error while deleting user: " + e.getMessage());
        writeLog("Error while deleting user: " + e.getMessage());
        return;
    }

    if (deleted) {
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("User deleted successfully.");
            writeLog("User deleted successfully.");
        } else {
            System.out.println("Error deleting user file.");
            writeLog("Error deleting user file.");
        }
    } else {
        System.out.println("User ID not found.");
        writeLog("User ID not found.");
        tempFile.delete();
    }
}


 //   public abstract void search();
//    public abstract void getList();

    public void getDetails() {
        writeLog("Got details of ID: " + id + " with Type: " + userType + " was called");

        System.out.println("ID: " + id);
        System.out.println("User Type: " + this.userType);

    }

    public void saveUser() {
        System.out.println("Saving user: ID = " + id + ", Type = " + this.userType);
        
        writeLog("ID: " + id + " with Type: " + userType + " was saved to " + usersFile);
        try {
            File file = new File(usersFile);
            boolean userExists = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("User: " + id + "---")) {
                        userExists = true;
                        break;
                    }
                }
            }

            if (!userExists) {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println("User: " + id + "--- Type: " + this.userType);
                    System.out.println("User saved successfully.");
                    writeLog("User saved successfully.");
                }
            } else {
                System.out.println("User with ID: " + id + " already exists.");
                writeLog("User with ID: " + id + " already exists.");
            }
        } catch (IOException ex) {
            System.out.println("Error in the saveUser function: " + ex.getMessage());
            writeLog("Error in the saveUser function: " + ex.getMessage());
        }
    
}



    public static ArrayList<String> getAllUsers() {//print users in the file
        ArrayList<String> a = new ArrayList<>();
        try {
            File x = new File(usersFile);
            Scanner s = new Scanner(x);

            while (s.hasNextLine()) {
                a.add(s.nextLine());

            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error in the getAllUsers func in super");
        }
        return a;
    }

    protected void writeLog(String log) { //used to write in the log file
            try {
                File x = new File(logFile);
                PrintWriter y = new PrintWriter(new FileOutputStream(x, x.exists()));
                y.println(log);
                y.close();
            } catch (IOException ex) {
                System.out.println("Error in the writeLog func in super");
            }}
        
    

    public static void readLog() {//used to read the log file

        try {
            File x = new File(logFile);
            Scanner s = new Scanner(x);

            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error in the readLog func in super");
        }
        System.out.println(new File(usersFile).getAbsolutePath());

    }

    
    

}
