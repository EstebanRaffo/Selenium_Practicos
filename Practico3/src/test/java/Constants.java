public class Constants {
    public static String DESTINY = "Budapest";
    // Fecha desde DATE_FROM_VALUE siempre debe ser mayor a la fecha del día de la consulta.
    public static DateObject DATE_FROM_VALUE = new DateObject("05", "04", "2021");
    public static DateObject DATE_TO_VALUE = new DateObject("16", "04", "2021");
    public static int ADULTS = 2;
    public static int CHILDREN = 1;
    public static int INFANTS = 1;
    public static int hosts = ADULTS + CHILDREN;
}
