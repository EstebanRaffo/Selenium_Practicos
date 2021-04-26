package ecommerce.utilities;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    public static Faker faker = new Faker();

    public static String getRandomEmail() { return "emiliano+" + Math.random() + "@gmail.com"; }
    public static String getFakeFirstName(){ return faker.name().firstName(); }
    public static String getFakeLastName(){ return faker.name().lastName(); }
    public static String getFakeCompany(){ return faker.company().name(); }
}
