package com.automation.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class for interact with the home page.
 * @author juan.montes
 *
 * Modified for interacting with ESPN HomePage for FinalExam
 * @author alejandro.carpio
 */
public class HomePage extends BasePage {

    @FindBy(id = "global-user-trigger")
    private WebElement userMenuDisplay;

    @FindBy(css = "[class*='tools'] a[tref*='login']")
    private WebElement menuLoginButton;

    @FindBy(css = "[class*='tools'] a[onclick*='logout']")
    private WebElement menuLogOutButton;

    @FindBy(css = "[class*='tools'] a[tref*='modify']")
    private WebElement menuModifyProfile;

    @FindBy(css = "[class*='tools'] [data-behavior*='tv']")
    private WebElement menuProvider;

    @FindBy(css = "[class*='tools'] [class='display-user']")
    private WebElement displayNoUserLoggedin;

    @FindBy(className = "central-textlogo__image")
    private WebElement title;

    @FindBy(id = "disneyid-iframe")
    private WebElement logInSignUpElement;

    /**
     * Constructor.
     * @param driver WebDriver
     * @param url String
     */
    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    /**
     * Constructor.
     * @param driver WebDriver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get Text title
     * @return String text title
     */
    public String getTitle() {
        log.info("Get Text title");
        waitElementVisibility(title);
        return title.getText();
    }

    /**
     * Waits for HomePage to load
     */
    public void waitHomePageToLoad(){
        waitElementVisibility(userMenuDisplay);
    }

    /**
     * Moves Mouse to the Menu Display Button
     */
    public void moveMouseToMenuDisplay(){
        mouseHover(userMenuDisplay);
    }

    /**
     * Clicks on login button from the Menu Display Button
     */
    public void clickMenuLoginButton(){
        waitElementVisibility(menuLoginButton);
        clickOnElement(menuLoginButton);
    }

    /**
     * Clicks on logout button from the Menu Display Button
     */
    public void clickMenuLogOutButton(){
        waitElementVisibility(menuLogOutButton);
        clickOnElement(menuLogOutButton);
    }

    /**
     * Waits for the Provider Button in the User Menu to be invisible
     */
    public void waitMenuProviderButtonInvisibility(){
        waitElementNonVisibility(menuProvider);
    }

    /**
     * Gives us the state of the Provider Button in the User Menu
     * @return Boolean depending on the element menuProvider being displayed
     */
    public boolean providerButtonIsDisplayed(){
        return menuProvider.isDisplayed();
    }

    /**
     * Gives us the state of the Modify Profile Button in the User Menu
     * @return Boolean depending on the element menuModifyProfile being displayed
     */
    public boolean profileButtonIsDisplayed(){
        return menuModifyProfile.isDisplayed();
    }

    /**
     * Allows us to cancel our Account.
     */
    public void cancelAccount(){
        waitElementVisibility(menuModifyProfile);
        clickOnElement(menuModifyProfile);
    }

    /**
     * Takes the steps necessary to display the User Menu
     */
    public void displayMenu(){
        waitHomePageToLoad();
        moveMouseToMenuDisplay();
        waitElementVisibility(menuProvider);
    }

    /**
     * Gets the text from the Menu Display
     * @return Welcome message from the system, in order to check if a user is logged in
     */
    public String menuDisplayUserMessage(){
        return displayNoUserLoggedin.getText();
    }

    /**
     * Switches Iframes to the one in charge of User Administration
     * @return Iframe class, passing the driver
     */
    public LogInSignUpIframe switchToIframeLogInSignUp(){
        getDriver().switchTo().frame(logInSignUpElement);
        return new LogInSignUpIframe(getDriver());
    }

}
