package ejercicio8;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners implements ISuiteListener {

    public void onStart(ISuite suite){ System.out.println("Inicia la suite de test: " + suite.getName()); }

    public void onFinish(ISuite suite){ System.out.println("Terminó la suite de test: " + suite.getName()); }
}
