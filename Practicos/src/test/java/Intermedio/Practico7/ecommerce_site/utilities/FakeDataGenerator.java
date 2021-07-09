package Intermedio.ecommerce_site.utilities;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    public static Faker faker = new Faker();

    public static String getRandomEmail(){
        return "ecommerce+" + Math.random() + "@gmail.com";
    }

    public static String getFakeFirstName(){
        return faker.name().firstName();
    }

    public static String getFakeLastName(){
        return faker.name().lastName();
    }

    public static String getFakeCompanyName(){
        return faker.company().name();
    }

}
