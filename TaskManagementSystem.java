import java.io.*;
import java.util.*;

        
public abstract class TaskManagementSystem {
    private int id; //idk if it should be string or int.. :b
    private String userType; //that's the user type-- is it admin, emploee or what
    protected static final String logFile="Log.txt"; //that's the path of the file where the log will be stored...probably
    private static final String usersFile="users.txt"; //same but for user..don't know if it's necceasry or not
    
    //don't know if password is here or not

    public TaskManagementSystem(){
        
    }
   public TaskManagementSystem(int id,String usertype){
       this.id=id;
       this.userType=userType;
   }
   
   public int getId(){
       return id;
   }
   public void setId(int id){
       this.id=id;
   }
   public String getType(){
       return userType;
   }
   public void setType(String userType){
       this.userType=userType;
   }
   
   public void create(int id,String usertype){
         this.id=id;
         this.userType=userType;
           }
   
   public void update(int id,String usertype){
         this.id=id;
         this.userType=userType;
           }
   public abstract void read();
   public  void delete(){
       this.id=-1;
   }
   public abstract void search();
   public abstract void getList();
   
   public void getDetails(){
       System.out.println("ID: "+id);
       System.out.println("User Type: "+userType);

   }


   public void saveUser(){//save in the file
       if(id!=-1){
       try{
         File x=new File(usersFile);
         PrintWriter y=new PrintWriter(new FileOutputStream(x,x.exists()));
         y.println(id+"---"+userType);
         y.close();
       }
       catch(IOException ex){
           System.out.println("Error in the saveUser func in super");
       }}else{
             fail();
         }
   }
   public static ArrayList<String> getAllUsers(){//print users in the file
       ArrayList<String> a= new ArrayList<>();
       try{
           File x=new File(usersFile);
           Scanner s=new Scanner(x);
           
           while(s.hasNextLine()){
             a.add(s.nextLine());
             
           }
           s.close();
       }
       catch(IOException ex){
                      System.out.println("Error in the getAllUsers func in super");
       }
       return a;
   }
   
   protected void writeLog(String log){ //used to write in the log file
         if(id!=-1){
       try{
           File x=new File(logFile);
           PrintWriter y=new PrintWriter(new FileOutputStream(x,x.exists()));
         y.println(log);
         y.close();
       }
       catch(IOException ex){
                      System.out.println("Error in the writeLog func in super");
       }
         }
         else{
             fail();
         }
   }
   public static void readLog(){//used to read the log file
       
       try{
           File x=new File(usersFile);
           Scanner s=new Scanner(x);
           
           while(s.hasNextLine()){
            System.out.println(s.nextLine());
           }
           s.close();
       }
       catch(IOException ex){
                      System.out.println("Error in the readLog func in super");
       }
   }
    
    
   public void fail(){
       System.out.println("Please Enter Info");
   }
    
}
