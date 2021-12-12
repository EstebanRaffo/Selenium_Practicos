package Practico14;

public class Case {
    private final String Status;
    private final String Reason;
    private final String Origin;
    private final String Description;

    public Case(String status, String case_origin, String case_reason, String description){
        this.Status = status;
        this.Reason = case_reason;
        this.Origin = case_origin;
        this.Description = description;
    }

}
