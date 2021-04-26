package Basico.practico13.factories;

import org.testng.annotations.Factory;
import Basico.practico13.refactoring.FacebookTest;

public class TestFactory {

    @Factory
    public Object[] facebookFactoryTest(){
        return new Object[]{
                new FacebookTest("Emiliano", "Gnocchi", "seleniumcurso@gmail.com"),
                new FacebookTest("Automation", "Curso", "cursoautomation@gmail.com"),
                new CalculadoraTest(3, 4),
                new CalculadoraTest(6, 2)
        };
    }
}
