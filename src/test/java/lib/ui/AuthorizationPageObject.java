package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "css:a.mw-ui-button",
    LOGIN_INPUT = "css:#wpName1",
    PASSWORD_INPUT = "css:#wpPassword1",
    SUBMIT_BUTTON = "css:#wpLoginAttempt";
    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    //Клик по кнопке авторизации
    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 10);
    }

    //Ввод логина и пароля
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 10);
    }
    //Клик на вход
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "cannot find and click submit auth button", 10);
    }
}
