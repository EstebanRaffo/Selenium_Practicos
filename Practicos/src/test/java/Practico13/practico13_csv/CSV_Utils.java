package Practico13.practico13_csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV_Utils {

    public static List<String> leerArchivoCSV(String csvPath) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(csvPath));
        String [] celda;
        List<String> csvDataList = new ArrayList<>();
        while((celda = reader.readNext()) != null){
            for (int i = 0; i < celda.length; i++){
                String datos = celda[i];
                csvDataList.add(datos);
            }
        }

        return csvDataList;
    }
}
