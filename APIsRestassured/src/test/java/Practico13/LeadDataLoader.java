package Practico13;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeadDataLoader {

    public static List<String> LISTA_DATOS = new ArrayList<>();
    public static List<Lead> LISTA_LEADS = new ArrayList<>();

    public static void leerCSV() throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader("/Users/Usuario/Documents/SeleniumAcademy/IdeaProjects/APIsRestassured/src/test/java/Practico13/leads.csv"));

        String [] celda;

        while( (celda = reader.readNext()) != null){
            for(int i = 0; i < celda.length; i++){
                String info = celda[i];
                System.out.println(info);
                LISTA_DATOS.add(info);
            }
        }

        procesarLeads();
    }


    public static void procesarLeads(){

        for (String dato : LISTA_DATOS) {
            String [] datosLead = dato.split(";");
            String name = datosLead[0];
            String company = datosLead[1];
            String course = datosLead[2];
            Lead newLead = new Lead(name, company, course);
            LISTA_LEADS.add(newLead);
        }

        System.out.println("********************* ");
        for (Lead l : LISTA_LEADS){
            System.out.println("---> " + l);
        }
    }
}
