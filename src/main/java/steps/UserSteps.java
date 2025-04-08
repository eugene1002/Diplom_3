package steps;

import controllers.UserController;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CreateUser;
import models.LoginUser;

public class UserSteps {

    @Step("Создать нового пользователя и вернуть response")
    public Response registerUser(CreateUser user) {
        return UserController.createUser(user);
    }

    @Step("Авторизовать пользователя и вернуть response")
    public Response authorizeUser(LoginUser user) {
        return UserController.loginUser(user);
    }

    @Step("Получить access token по логину")
    public String getAccessToken(LoginUser user) {
        return UserController.getAccessToken(user);
    }

    @Step("Удалить пользователя по модели")
    public Response deleteUser(LoginUser user) {
        return UserController.deleteUser(user);
    }

    @Step("Удалить пользователя по access token")
    public Response deleteUser(String token) {
        return UserController.deleteUser(token);
    }
}
