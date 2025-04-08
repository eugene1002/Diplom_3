package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static locators.LocatorsAuthPage.*;

public class AuthPage extends BasePage {

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        enterText($x(EMAIL_INPUT), "Поле ввода E-mail", email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        enterText($x(PASSWORD_INPUT), "Поле ввода пароля", password);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickOnAuthButton() {
        clickElement($x(SIGN_IN_BUTTON), "Кнопка 'Войти'");
    }

    @Step("Авторизация пользователя через форму входа")
    public void loginFromMainPage(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickOnAuthButton();
    }

    @Step("Переход по ссылке 'Зарегистрироваться'")
    public void goToRegistration() {
        clickElement($x(REGISTER_LINK), "Ссылка 'Зарегистрироваться'");
    }

    @Step("Переход по ссылке 'Восстановить пароль'")
    public void goToForgotPassword() {
        clickElement($x(FORGOT_PASSWORD), "Ссылка 'Забыли пароль?'");
    }
}
