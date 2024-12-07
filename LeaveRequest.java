

public class LeaveRequest extends Employee {
   private int LRid;
   private String LRtype,LRstartDate,LRendDate,LRstatus;
   private static final String LRfile="LR.txt"; //same as in super but for L requests..don't know if it's necceasry or not
   
   public LeaveRequest(){
       
   }
   public LeaveRequest(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate){
      ///make sure the names match the ones in the employee classs
       super.employeeId=employeeId;
       super.name=name;
       super.role=role;
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
        if(LRid!=-1){
       saveLR();}
        else{super.fail();
        
        }
   }
   
   @Override
   public void create(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate){
         super.employeeId=employeeId;
       super.name=name;
       super.role=role;
       this.LRendDate=LRendDate;
       this.LRid=LRid;
       this.LRtype=LRtype;
       this.LRstartDate=LRstartDate;
       this.LRstatus="Pending";
           }
   
      @Override
      public void update(int employeeId,String name,String role,int LRid,String LRtype,String LRstartDate,String LRendDate){
       super.employeeId=employeeId;
       super.name=name;
       super.role=role;
       this.LRendDate=LRendDate;
       this.LRid=LRid;
       this.LRtype=LRtype;
       this.LRstartDate=LRstartDate;
       this.LRstatus="Pending";
           }
      
   @Override      
   public  void delete(){
       this.LRid=-1;
   }
   
   private void saveLR(){
       try{
           File x=new File(LRfile);
           PrintWriter y=new PrintWriter(new FileOutputStream(x,x.exists()));
           String p=getEmployeeId() + "---" + getName() + "---" + getRole() + "---" + LRid + "---" + LRtype + "---" + LRstartDate + "---" + LRendDate + "---" + LRstatus;
           writeLog(p);
           y.println(p);
         y.close();
       }
       catch(IOException ex){
                      System.out.println("Error in the saveLR func in LeaveRequest Class");
       }
       
       
   }
   public void approveLR(){
              if(LRid!=-1){

       this.LRstatus="Approved";
       saveLR();
       }else{
           super.fail();
       }
   }
   public void rejectLR(){
       if(LRid!=-1){
       this.LRstatus="Rejected";
       saveLR();
   }else{
           super.fail();
       }
   
   
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
