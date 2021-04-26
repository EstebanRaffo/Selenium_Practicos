package clase7;

public class InformacionPersonal {

    public static void main(String args[]){

        String nombre = JavaUtils.ingresarPalabras("Ingrese su nombre, porfavor");
        int edad = JavaUtils.ingresarNumero("Ingrese su edad", 1, 100, "Ingrese una edad entre 0 y 100");
        int dni = JavaUtils.ingresarNumero("Ingrese su dni", 1000,1000000, "DNI invalido!! Reingrese por favor...");
        int salario = JavaUtils.ingresarNumero("Ingrese su salario");
        double ivaSalario = salario * Constantes.VALOR_IVA;
        System.out.println("El IVA de su salario es " + ivaSalario);

        System.out.println("Su nombre es " + nombre + ". Su DNI es " + dni + " y su edad es " + edad);

    }
}
