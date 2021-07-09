package Intermedio.Practico13.practico13_csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import Intermedio.Practico12.Persona;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV_Ejemplo1 {

    public static String CSV_EMAIL = "dataloader/email.csv";
    public static List<String> LISTA_DATOS_PERSONALES = new ArrayList<>();


    public static void main(String args[]) throws IOException, CsvValidationException {
        System.out.println("**********");
        leerArchivoCSV();
        procesarDatosPersonales();

        System.out.println("**********");
    }

    public static void leerArchivoCSV() throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(CSV_EMAIL));
        String [] celda;

        while((celda = reader.readNext()) != null){
            System.out.println("celda: " + celda);
            for (int i = 0; i < celda.length; i++){
                String datos = celda[i];
                LISTA_DATOS_PERSONALES.add(datos);
            }
        }
    }

    public static List<Persona> procesarDatosPersonales(){
        LISTA_DATOS_PERSONALES.remove(0);
        List<Persona> listaPersonas = new ArrayList<>();

        for (String dato : LISTA_DATOS_PERSONALES){
            String [] datoPersona = dato.split(";");
            //System.out.println("Email: " + datoPersona[0] + " Id " + datoPersona[1] + " Nombre: " + datoPersona[2]);
            String nombre = datoPersona[2].concat(" ").concat(datoPersona[3]);
            String email = datoPersona[0];
            String dni = datoPersona[1];

            Persona pers = new Persona(Integer.parseInt(dni), nombre, "Argentina", email);

            System.out.println(pers);
            listaPersonas.add(pers);
        }

        return listaPersonas;

    }


}
