package com.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Methods and arguments in the Iframe
 * @author alejandro.carpio
 */
public class LogInSignUpIframe extends BasePage{

    public LogInSignUpIframe(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[type='email']")
    private WebElement emailBox;

    @FindBy(css = "[type='password']")
    private WebElement passwordBox;

    @FindBy(css = "[did-translate*='SIGNIN']")
    private WebElement logInButton;

    @FindBy(css = "[did-translate*='CREATE_ACCOUNT']")
    private WebElement signUpButton;

    @FindBy(name = "firstName")
    private WebElement firstNameBox;

    @FindBy(name = "lastName")
    private WebElement lastNameBox;

    @FindBy(name = "email")
    private WebElement newEmailBox;

    @FindBy(name = "newPassword")
    private WebElement newPasswordBox;

    @FindBy(css = "[did-translate*='SIGN_UP']")
    private WebElement newSignUpButton;

    @FindBy(css = "h3[did-translate*='CANCEL']")
    private WebElement deleteAccountMessage;

    @FindBy(css = "[ng-click*='cancelAccount']")
    private WebElement deleteAccountButton;

    @FindBy(css = "h2[did-translate*='deactivate']")
    private WebElement deleteConfirmMessage;

    @FindBy(css = "[did-translate*='buttons.confirm']")
    private WebElement deleteConfirmButton;

    @FindBy(css = "[class*='state-loading']")
    private WebElement loadingIndicator;

    @FindBy(css = "[name*='gender']")
    private WebElement genderLocator;

    /**
     * Once we are in the login options, we can choose to create a New Account
     * for a New User, passing the Method the required New User's information
     * @param userName First name of New User
     * @param userLastName Last name of New User
     * @param email Email of New User
     * @param password Password of New User
     */
    public void registerNewAccountInput(String userName, String userLastName, String email, String password){
        waitElementVisibility(signUpButton);
        clickOnElement(signUpButton);
        waitElementVisibility(firstNameBox);
        sendKeysToElement(firstNameBox, userName);
        sendKeysToElement(lastNameBox, userLastName);
        sendKeysToElement(newEmailBox, email);
        sendKeysToElement(newPasswordBox, password);
        clickOnElement(newSignUpButton);
    }

    /**
     * Waits for the loading indicator to be invisible
     */
    public void waitForLoadingIndicatorToBeInvisible(){
        waitElementNonVisibility(loadingIndicator);
    }

    /**
     * Delivers the driver to the HomePage, once the Iframe reloades the webpage
     * @return HomePage driver
     */
    public HomePage returnToMainPage(){
        return new HomePage(getDriver());
    }

    /**
     * Once we are in the login options, we can choose to login with an Existing User,
     * passing the Method the required User's information
     * @param email Email from the Existing User
     * @param password Password from the Existing User
     */
    public void loginAccount(String email, String password){
        waitElementVisibility(emailBox);
        sendKeysToElement(emailBox, email);
        sendKeysToElement(passwordBox, password);
        clickOnElement(logInButton);
    }

    /**
     * Begins the Cancel Account procedure
     */
    public void cancelAccount(){
        waitForLoadingIndicatorToBeInvisible();
        waitElementVisibility(deleteAccountMessage);
        waitElementIsClickable(genderLocator);
        clickOnElement(deleteAccountButton);
    }

    /**
     * Confirms the Cancel Account procedure
     */
    public void cancelAccountConfirm(){
        waitElementVisibility(deleteConfirmMessage);
        clickOnElement(deleteConfirmButton);
    }

    /**
     * Gives us the state of the Password Box in the Menu
     * @return Boolean depending on the element passwordBox being displayed
     */
    public boolean passwordBoxIsDisplayed(){
        return passwordBox.isDisplayed();
    }

}
