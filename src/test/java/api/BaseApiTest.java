package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.Before;

import static utils.ConfigReader.get;

public class BaseApiTest {

    @Before
    @Step("Настройка RestAssured")
    public void setupApi() {
        String baseUrl = get("baseUrl");
        RestAssured.baseURI = baseUrl;
    }
}
