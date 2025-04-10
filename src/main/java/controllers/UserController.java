package controllers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.ChangeUser;
import models.CreateUser;
import models.LoginUser;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserController {

    private static final String CREATE_USER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_USER_ENDPOINT = "/api/auth/login";
    private static final String REFRESH_TOKEN_USER_ENDPOINT = "/api/auth/token";
    private static final String RESET_PASSWORD_USER_ENDPOINT = "/api/password-reset";
    private static final String LOGOUT_USER_ENDPOINT = "/api/auth/logout";
    private static final String DELETE_USER_ENDPOINT = "/api/auth/user";
    private static final String GET_OR_CHANGE_USER_ENDPOINT = "/api/auth/user";

    private static RequestSpecification spec() {
        return given()
                .header("Content-Type", "application/json");
    }

    @Step("Создание пользователя")
    public static Response createUser(CreateUser createUser) {
        return spec()
                .body(createUser)
                .when()
                .post(CREATE_USER_ENDPOINT);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(LoginUser loginUser) {
        return spec()
                .body(loginUser)
                .when()
                .post(LOGIN_USER_ENDPOINT);
    }

    @Step("Получение access токена для пользователя")
    public static String getAccessToken(LoginUser loginUser) {
        Response response = loginUser(loginUser);
        String accessToken = response.jsonPath().getString("accessToken");
        return accessToken.split(" ")[1]; // убираем 'Bearer'
    }

    @Step("Получение refresh токена для пользователя")
    public static String getRefreshToken(LoginUser loginUser) {
        Response response = loginUser(loginUser);
        return response.jsonPath().getString("refreshToken");
    }

    @Step("Обновление токена пользователя (email: {loginUser.email})")
    public static Response refreshToken(LoginUser loginUser) {
        String refreshToken = getRefreshToken(loginUser);

        Map<String, String> body = new HashMap<>();
        body.put("token", refreshToken);

        return spec()
                .auth().oauth2(getAccessToken(loginUser))
                .body(body)
                .when()
                .post(REFRESH_TOKEN_USER_ENDPOINT);
    }

    @Step("Сброс пароля пользователя (email: {loginUser.email})")
    public static Response resetPassword(LoginUser loginUser) {

        Map<String, String> body = new HashMap<>();
        body.put("email", loginUser.getEmail());

        return spec()
                .body(body)
                .when()
                .post(RESET_PASSWORD_USER_ENDPOINT);
    }

    @Step("Выход из системы пользователя (email: {loginUser.email})")
    public static Response logoutUser(LoginUser loginUser) {
        String refreshToken = getRefreshToken(loginUser);

        Map<String, String> body = new HashMap<>();
        body.put("token", refreshToken);

        return spec()
                .auth().oauth2(getAccessToken(loginUser))
                .body(body)
                .when()
                .post(LOGOUT_USER_ENDPOINT);
    }

    @Step("Удаление пользователя по логину")
    public static Response deleteUser(LoginUser loginUser) {
        return deleteUser(getAccessToken(loginUser));
    }

    @Step("Удаление пользователя по токену")
    public static Response deleteUser(String token) {
        return spec()
                .auth().oauth2(token)
                .when()
                .delete(DELETE_USER_ENDPOINT);
    }

    @Step("Получение данных пользователя. Авторизация: {useAuth}")
    public static Response getUserData(LoginUser loginUser, boolean useAuth) {
        if (useAuth) {
            return spec()
                    .auth().oauth2(getAccessToken(loginUser))
                    .when()
                    .get(GET_OR_CHANGE_USER_ENDPOINT);
        } else {
            return spec()
                    .when()
                    .get(GET_OR_CHANGE_USER_ENDPOINT);
        }
    }

    @Step("Изменение данных пользователя. Авторизация: {useAuth}")
    public static Response changeUserData(LoginUser userBefore, ChangeUser newData, boolean useAuth) {
        if (useAuth) {
            return spec()
                    .auth().oauth2(getAccessToken(userBefore))
                    .body(newData)
                    .when()
                    .patch(GET_OR_CHANGE_USER_ENDPOINT);
        } else {
            return spec()
                    .body(newData)
                    .when()
                    .patch(GET_OR_CHANGE_USER_ENDPOINT);
        }
    }
}
