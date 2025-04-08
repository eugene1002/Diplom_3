package controllers;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import models.CreateUser;
import models.LoginUser;

import static io.restassured.RestAssured.given;

public class UserController {

    private static final String CREATE_USER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_USER_ENDPOINT  = "/api/auth/login";
    private static final String DELETE_USER_ENDPOINT = "/api/auth/user";

    private static RequestSpecification spec() {
        return given()
                .header("Content-Type", "application/json");
    }

    @Step("Создание пользователя через API: {createUser}")
    public static Response createUser(CreateUser createUser) {
        return spec()
                .body(createUser)
                .when()
                .post(CREATE_USER_ENDPOINT);
    }

    @Step("Авторизация пользователя через API: {loginUser}")
    public static Response loginUser(LoginUser loginUser) {
        return spec()
                .body(loginUser)
                .when()
                .post(LOGIN_USER_ENDPOINT);
    }

    @Step("Получение access токена для пользователя: {loginUser}")
    public static String getAccessToken(LoginUser loginUser) {
        Response response = loginUser(loginUser);
        String accessToken = response.jsonPath().getString("accessToken");
        return accessToken.split(" ")[1]; // убираем 'Bearer'
    }

    @Step("Удаление пользователя через API (по модели): {loginUser}")
    public static Response deleteUser(LoginUser loginUser) {
        return deleteUser(getAccessToken(loginUser));
    }

    @Step("Удаление пользователя через API (по токену)")
    public static Response deleteUser(String token) {
        return spec()
                .auth().oauth2(token)
                .when()
                .delete(DELETE_USER_ENDPOINT);
    }
}
