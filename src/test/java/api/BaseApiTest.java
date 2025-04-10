package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.Before;
import utils.ConfigReader;

public class BaseApiTest {

    protected static final Faker faker = new Faker();
    protected static final String userName = faker.name().firstName();
    protected static final String userEmail = faker.internet().emailAddress();
    protected static final String userPassword = faker.internet().password();

    @Before
    @Step("Настройка RestAssured")
    public void setupBaseUri() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }
}
