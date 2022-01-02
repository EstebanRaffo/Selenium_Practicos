package Practico10;

public class Lead {

    private String LastName;
    private String Company;
    private String Course__c;
    private String Email;

    public Lead(String lastName){
        this.LastName = lastName;
    }

    public Lead(String aName, String aCompany){
        this.LastName = aName;
        this.Company = aCompany;
//        this.Course__c = "Unknown";
    }

    public Lead(String aName, String aCompany, String aCourse){
//      Required Fields
        this.LastName = aName;
        this.Company = aCompany;
        this.Course__c = aCourse;
    }

    public Lead(String aName, String aCompany, String aCourse, String anEmail){
//      Required Fields
        this.LastName = aName;
        this.Company = aCompany;
        this.Course__c = aCourse;

//        Not required fields
        this.Email = anEmail;
    }

    public void setCourse(String course){
        this.Course__c = course;
    }
}
