package ejercicio1;

public class Demo implements MyInterface{

    public void firstMethod(){
        System.out.println("firstMethod");
    }

    public void secondMethod(){
        System.out.println("secondMethod");
    }

    public int addNum(int aNumber){
        return num + aNumber;
    }

    public static void main(String args[]){
        MyInterface demoTest = new Demo();

        demoTest.firstMethod();
        demoTest.secondMethod();
        System.out.println("la suma es: " + demoTest.addNum(5));
    }
}
