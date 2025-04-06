import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserController {

    private final static String apiCreateUser = "/api/auth/register";
    private final static String apiDeleteUser = "/api/auth/user";
    private final static String apiLoginUser = "/api/auth/login";

    @Step("Создание пользователя через API")
    public static Response executeCreate(CreateUser createUser) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(createUser)
                        .when()
                        .post(apiCreateUser);
        return response;
    }

    @Step("Получение токена пользователя через API")
    public static String getUserToken(LoginUser loginUser) {
        Response response = executeLogin(loginUser);
        String accessToken = response.jsonPath().get("accessToken");
        return accessToken.split(" ")[1]; // разбили строку на 2 значения, разделитель Пробел. Выбрали второе значение (токен)
    }

    @Step("Авторизация пользователя в системе через API")
    public static Response executeLogin(LoginUser loginUser) {
        return
                given()
                        .header("Content-type", "application/json")
                        .body(loginUser)
                        .when()
                        .post(apiLoginUser);
    }

    @Step("Удаление пользователя через API")
    public static Response executeDelete(LoginUser loginUser) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(getUserToken(loginUser))
                        .when()
                        .delete(apiDeleteUser);
        return response;
    }
    @Step("Удаление пользователя через API")
    public static Response executeDelete(String token) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(token)
                        .when()
                        .delete(apiDeleteUser);
        return response;
    }
}
