package clase6;

public class EmpresaSoftware {

    public static void main(String args[]){
        Tester t1 = new Tester();
        t1.setEsManual(false);
        t1.setEsAutomatizador(true);

        System.out.println(t1);
    }
}
