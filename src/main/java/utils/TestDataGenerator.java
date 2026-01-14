package utils;
import com.github.javafaker.Faker;


public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String generateUsersEmail() {
        return faker.internet().emailAddress();
    }
    public static String generateUsersPassword() {
        return faker.internet().password(8, 12);
    }

}
