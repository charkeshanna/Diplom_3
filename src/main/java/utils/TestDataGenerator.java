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

    public static String generateShortPassword() {
        return faker.internet().password(2,5);
    }

}
