package Practico12;

import java.util.HashMap;

public class ManejoStrings {

    public static String paisesLATAM = "Chile,Santiago,40000,Argentina,Buenos Aires,3500,Colombia,Bogota,3800,Uruguay,Montevideo,2500,España,Madrid,4000";
                                        // pais, capi, $ , pais, capi
    public static void main(String args[]){
        // pos 0 --> chile
        // pos 1 --> santiago
        // pos 2 --> 4000
        // pos 3 --> Argentina
        // pos 4 --> Bs As
        String [] arregloPaisesConCapitales = paisesLATAM.split(",");

        HashMap<String, String> capitalesMap = new HashMap<>();
        // chile  - santiago
        // argetina - bs as
        // uruguay - montevideo


        for (int i = 0; i < arregloPaisesConCapitales.length; i = i + 3){
            System.out.println("--> " + arregloPaisesConCapitales[i] + " ** " + arregloPaisesConCapitales[i+1]);
            capitalesMap.put(arregloPaisesConCapitales[i], arregloPaisesConCapitales[i+1]);
        }

        System.out.println(capitalesMap.get("Chile"));
        System.out.println(capitalesMap.get("Argentina"));
        System.out.println(capitalesMap.get("Colombia"));
        System.out.println(capitalesMap.get("Uruguay"));
        System.out.println(capitalesMap.get("España"));

    }
}
