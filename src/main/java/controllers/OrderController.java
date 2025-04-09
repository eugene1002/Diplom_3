package controllers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CreateOrder;
import models.LoginUser;

import static controllers.UserController.getAccessToken;
import static io.restassured.RestAssured.given;

public class OrderController {

    private static final String apiOrders = "/api/orders";
    private static final String apiAllOrders = "/api/orders/all";
    private static final String apiIngredients = "/api/ingredients";

    @Step("Создание заказа без авторизации")
    public static Response createOrder(CreateOrder order) {
        return given()
                .header("Content-Type", "application/json")
                .body(order)
                .when()
                .post(apiOrders);
    }

    @Step("Создание заказа с авторизацией")
    public static Response createOrder(CreateOrder order, String token) {
        return given()
                .header("Content-Type", "application/json")
                .auth().oauth2(token)
                .body(order)
                .when()
                .post(apiOrders);
    }

    @Step("Получение заказов пользователя по login/password")
    public static Response getOrders(LoginUser user) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(getAccessToken(user))
                .when()
                .get(apiOrders);
    }

    @Step("Получение заказов с токеном: авторизация {useAuth}")
    public static Response getOrders(String token, boolean useAuth) {
        if (useAuth) {
            return given()
                    .header("Content-type", "application/json")
                    .auth().oauth2(token)
                    .when()
                    .get(apiOrders);
        } else {
            return given()
                    .header("Content-type", "application/json")
                    .when()
                    .get(apiOrders);
        }
    }
    @Step("Получение ингредиентов")
    public static Response getIngredients() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(apiIngredients);
    }

    @Step("Получение всех заказов")
    public static Response getAllOrders() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(apiAllOrders);
    }
}
