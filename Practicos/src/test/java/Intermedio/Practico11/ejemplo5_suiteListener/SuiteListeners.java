package Intermedio.Practico11.ejemplo5_suiteListener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners implements ISuiteListener {

    public void onStart(ISuite suite) {
        System.out.println("Inicia la suite " + suite.getName());
    }

    public void onFinish(ISuite suite) {
        System.out.println("Termino la suite  " + suite.getName());


    }

}
