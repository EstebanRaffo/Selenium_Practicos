

public class DateObject {
    private String day;
    private String month;
    private String year;

    public DateObject(String day_selected, String month_selected, String year_selected){
        day = day_selected;
        month = month_selected;
        year = year_selected;
    }

    public String getFormatDay(){
        if(day.contains("0")){
            return day.replace("0", "");
        }
        return day;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getMonthWord(){
        switch(month){
            case "01":
                return "ene";
            case "02":
                return "feb";
            case "03":
                return "mar";
            case "04":
                return "abr";
            case "05":
                return "may";
            case "06":
                return "jun";
            case "07":
                return "jul";
            case "08":
                return "ago";
            case "09":
                return "sep";
            case "10":
                return "oct";
            case "11":
                return "nov";
            case "12":
                return "dic";
            default:
                return " ";
        }
    }
}
