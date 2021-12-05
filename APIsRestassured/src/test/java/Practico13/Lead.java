package Practico13;

public class Lead {
    String LastName;
    String Company;
    private String Course__c;
    private String Email;

    public Lead(String lastName, String aCompany, String aCourse){
        this.LastName = lastName;
        this.Company = aCompany;
        this.Course__c = aCourse;
    }

    public String toString(){
        return "Lead: " + this.LastName + " Company: " + this.Company;
    }
}

