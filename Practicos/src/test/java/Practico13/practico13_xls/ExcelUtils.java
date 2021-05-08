package Practico13.practico13_xls;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static void main(String args[]) throws IOException {
        XSSFWorkbook samplexlsx = new XSSFWorkbook("dataloader/sample.xlsx");
        XSSFSheet sheet1 = samplexlsx.getSheet("Sheet1");
        List<String> listaPersonas = new ArrayList<>();

        for (int fila = 0; fila < 9; fila++){
            String datosPersonales = "";
            for (int columna = 0; columna < 8; columna++){
                try {
                   // System.out.print(sheet1.getRow(fila).getCell(columna).getStringCellValue() + " ");
                    datosPersonales += sheet1.getRow(fila).getCell(columna).getStringCellValue() + ";";
                } catch (IllegalStateException ex) {
                    int num = (int) sheet1.getRow(fila).getCell(columna).getNumericCellValue();
                    //System.out.print(num + " ");
                    String numeroString = String.valueOf(num);
                    datosPersonales += numeroString + ";";
                }
            }
            System.out.println(datosPersonales);
            listaPersonas.add(datosPersonales);
        }
    }

    public static void getPersonalInfo(List<String> listaDatos){
        for (String datosPersonales: listaDatos){
            String [] datos = datosPersonales.split(";");
            System.out.println(datos[1]);
            System.out.println(datos[1]);
        }



    }


}
