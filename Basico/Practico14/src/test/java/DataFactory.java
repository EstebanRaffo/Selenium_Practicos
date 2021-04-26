import com.github.javafaker.Faker;

import java.util.Random;

public class DataFactory {

    public static Faker faker_data = new Faker();

    public String getFirstName(){
        String name = faker_data.name().firstName();
        return name;
    }

    public String getLastName(){
        String lastname = faker_data.name().lastName();
        return lastname;
    }

    public String getEmail(){
        String email = faker_data.internet().emailAddress();
        return email;
    }

    public String getPhone(){
        Random random_phone = new Random();
        return String.valueOf(random_phone.nextInt(1000000) + 1000000000);
        /*String phone = faker_data.phoneNumber().phoneNumber();
        return phone;*/
    }

    public String getJob(){
        String job = faker_data.job().position();
        return job;
    }

    public String getCompany(){
        String company = faker_data.company().name();
        return company;
    }
}
