package Practico13.practico13_csv;

import com.opencsv.exceptions.CsvValidationException;
import Practico12.Persona;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CSV_Ejemplo2 {

    // Map < Identificador , Username >
    // Map < Username , Persona >

    public static HashMap<String, String> ID_USERNAME_MAP = new HashMap<>();
    public static HashMap<String, Persona> USERNAME_PERSON_MAP = new HashMap<String, Persona>();

    public static void loadDataFromCSV() throws IOException, CsvValidationException {

        List<String> datosPersonasEmpresa = CSV_Utils.leerArchivoCSV("dataloader/username-password-recovery-code.csv");

        for (String csv_row : datosPersonasEmpresa){
            System.out.println(csv_row);
            String [] datos = csv_row.split(";");

            String username = datos[0];
            String identifier = datos[1];
            String name =  datos[4].concat(datos[5]);
            String location = datos[7];
            String unEmail = username+"@test.com";

            ID_USERNAME_MAP.put(identifier, username);
            Persona person = new Persona(0, name, location, unEmail );

            USERNAME_PERSON_MAP.put(username, person);

        }

    }



}
