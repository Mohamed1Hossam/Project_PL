

public class LeaveRequest extends Employee {
   private int LRid;
   private String LRtype,LRstartDate,LRendDate,LRstatus;
   private static final String LRfile="../files/LR.txt"; //same as in super but for L requests..don't know if it's necceasry or not
   
   public LeaveRequest(){
       
   }
   public LeaveRequest(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate){
      ///make sure the names match the ones in the employee classs
       setEmployeeId(employeeId);
       setName(name);
       setRole(role);
       this.LRendDate=LRendDate;
       this.LRid=LRid;
       this.LRtype=LRtype;
       this.LRstartDate=LRstartDate;
       this.LRstatus="Pending";
       ///remove if LR file not needed :b
       saveLR();
   }
   
   public int getLRId(){
       return LRid;
   }
   public void setLRId(int LRid){
       this.LRid=LRid;
   }
   public String getLRtype(){
       return LRtype;
   }
   public void setLRtype(String LRtype){
       this.LRtype=LRtype;
   }
   public String getLRstartDate(){
       return LRstartDate;
   }
   public void setLRstartDate(String LRstartDate){
       this.LRstartDate=LRstartDate;
   }
   public String getLRendDate(){
       return LRendDate;
   }
   public void setLRendDate(String LRendDate){
       this.LRendDate=LRendDate;
   }
   public String getLRstatus(){
       return LRstatus;
   }
   public void setLRstatus(String LRstatus){
       this.LRstatus=LRstatus;
   }   

   public void SumbitLR(){
       saveLR();
     
   }
   
   @Override
   public void create(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate){
         setEmployeeId(employeeId);
       setName(name);
       setRole(role);
       this.LRendDate=LRendDate;
       this.LRid=LRid;
       this.LRtype=LRtype;
       this.LRstartDate=LRstartDate;
       this.LRstatus="Pending";
           }
   
      @Override
      public void update(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate,String LRstatus){

    File inputFile = new File(LRfile);
    File tempFile = new File("temp_users.txt");

    boolean updated = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] data = currentLine.split("---");
            if (data[0].trim().equals("Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid)) {
                writer.write("Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid+" --- LRtype = "+LRtype+" --- LRstartDate = "+LRstartDate+" --- LRendDate = "+LRendDate+" --- LRstatus = "+LRstatus);
                updated = true;
            } else {
                writer.write(currentLine);
            }
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error while updating user: " + e.getMessage());
        super.writeLog("Error while updating user: " + e.getMessage());
        return;
    }

    if (updated) {
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("User updated successfully.");
            super.writeLog("User updated successfully.");
        } else {
            System.out.println("Error updating user file.");
            super.writeLog("Error updating user file.");
        }
    } else {
        System.out.println("User ID not found.");
        super.writeLog("User ID not found.");
        tempFile.delete();
    }


       setEmployeeId(employeeId);
       setName(name);
       setRole(role);
       this.LRendDate=LRendDate;
       this.LRid=LRid;
       this.LRtype=LRtype;
       this.LRstartDate=LRstartDate;
       this.LRstatus="Pending";
           }
      
   @Override      
   public  void delete(int LRid){
       this.LRid=-1;
       File inputFile = new File(LRfile);
    File tempFile = new File("temp_users.txt");

    boolean deleted = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] data = currentLine.split("---");
            if (!data[0].trim().equals("Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid)) {
                writer.write(currentLine);
                writer.newLine();
            } else {
                deleted = true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error while deleting user: " + e.getMessage());
        super.writeLog("Error while deleting user: " + e.getMessage());
        return;
    }

    if (deleted) {
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("User deleted successfully.");
            super.writeLog("User deleted successfully.");
        } else {
            System.out.println("Error deleting user file.");
            super.writeLog("Error deleting user file.");
        }
    } else {
        System.out.println("User ID not found.");
        super.writeLog("User ID not found.");
        tempFile.delete();
    }
}
   }
   
 



   public void saveUser() {
                   String p="Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid+" --- LRtype = "+LRtype+" --- LRstartDate = "+LRstartDate+" --- LRendDate = "+LRendDate+" --- LRstatus = "+LRstatus;

        super.writeLog(p + " was saved to " + LRfile);
        try {
            File file = new File(LRfile);
            boolean userExists = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.startsWith("Employee ID ="+getEmployeeId()+" --- Name = "+getName()+" --- Role = "+getRole()+" --- LRid = "+LRid)) {
                        userExists = true;
                        break;
                    }
                }
            }

            if (!userExists) {
                try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
                    writer.println(p);
                    System.out.println("User saved successfully.");
                    super.writeLog("User saved successfully.");
                }
            } else {
                System.out.println(p+ " already exists.");
                super.writeLog(p + " already exists.");
            }
        } catch (IOException ex) {
            System.out.println("Error in the saveUser function: " + ex.getMessage());
            super.writeLog("Error in the saveUser function: " + ex.getMessage());
        }
    
}


   public void approveLR(){

       this.LRstatus="Approved";
       saveLR();
       
   }
   public void rejectLR(){
       this.LRstatus="Rejected";
       saveLR();

   
   
   }
   @Override
   public void getDetails(){
       super.getDetails();
       System.out.println("Leave Request ID: "+LRid);
       System.out.println("Leave Request Type: "+LRtype);
       System.out.println("Leave Request Start Date: "+LRstartDate);
       System.out.println("Leave Request End Date: "+LRendDate);
       System.out.println("Leave Request Status: "+LRstatus);

   } 
    
}
