/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home.take;

/**
 *
 * @author wku cslab1
 */
public class Table1 {

    private int SID;
    private String STUDID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String SECTION;
    private String DEPARTMENT;
    
    
    public Table1(){
    
         this.SID = 0;  
         this.STUDID = "";
         this.FIRSTNAME = "";
         this.LASTNAME = "";
         this.SECTION = "";
         this.DEPARTMENT = "";
         
    
    }
      public Table1(int SID, String STUDID, String FIRSTNAME, String LASTNAME, String SECTION, String DEPARTMENT ){
    
         this.SID = SID;  
         this.STUDID = STUDID;
         this.FIRSTNAME = FIRSTNAME;
         this.LASTNAME = LASTNAME;
         this.SECTION = SECTION;
         this.DEPARTMENT = DEPARTMENT;
    
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public void setSTUDID(String STUDID) {
        this.STUDID = STUDID;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public int getSID() {
        return SID;
    }

    public String getSTUDID() {
        return STUDID;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public String getSECTION() {
        return SECTION;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }
    
  
    
}
