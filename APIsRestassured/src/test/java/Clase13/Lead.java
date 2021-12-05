package Clase13;

public class Lead {
    String LastName;
    String Company;

    public Lead(String lastName, String aCompany){
        this.LastName = lastName;
        this.Company = aCompany;

    }

    public String toString(){
        return "Lead: " + this.LastName + " Company: " + this.Company;
    }
}

