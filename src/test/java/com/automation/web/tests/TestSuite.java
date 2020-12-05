package com.automation.web.tests;

import com.automation.web.data.CreateRandomUser;
import com.automation.web.data.Data;
import com.automation.web.pages.HomePage;
import com.automation.web.pages.LogInSignUpIframe;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Class for the wikipedia test.
 * @author juan.montes
 */
public class TestSuite extends BaseTest {

    @BeforeMethod(groups = {"Register_and_LogOut"}, description = "Registers a New User and LogsOut") //falta meterle dataprovider
    public void registerNewAccountAndLogout(Object[] data){

        CreateRandomUser newUser = (CreateRandomUser) data[0];

        log.info("Get Home Page");
        HomePage homePage = getHomePage();
        log.info("Moves cursor to the User Icon, and displays Menu");
        homePage.displayMenu();
        log.info("Clicks on the LogIn Button from the displayed Menu");
        homePage.clickMenuLoginButton();

        log.info("Changes Iframes");
        LogInSignUpIframe logInSignUpIframe = homePage.switchToIframeLogInSignUp();

        log.info("Begins the procedure to Register a NewUser");
        log.info("Clicks in the 'Sign up' option, and inputs the required data. Then, creates the NewUser");
        logInSignUpIframe.registerNewAccountInput(newUser.getFirstName(), newUser.getLastName(),
                newUser.getEmail(), newUser.getPassword());

        log.info("Returns a new HomePage variable, and hands it the Driver");
        HomePage returnedHomePage = logInSignUpIframe.returnToMainPage();

        log.info("Begins the Procedure for Logging Out");
        log.info("Moves cursor to the User Icon");
        returnedHomePage.displayMenu();
        log.info("Clicks on the LogOut Button");
        returnedHomePage.clickMenuLogOutButton();
        log.info("Waits for an element on the previously shown menu to disappear");
        returnedHomePage.waitMenuProviderButtonInvisibility();
        log.info("Waits for HomePage to load properly");
        returnedHomePage.displayMenu();

    }

    @Test(groups = {"Register_and_LogOut"}, dataProviderClass = Data.class, dataProvider = "userData")
    public void loginTest(CreateRandomUser userCreated ){
        HomePage homePage = getHomePage();
        homePage.displayMenu();
        log.info("Clicks on the LogIn Button from the displayed Menu");
        Assert.assertTrue(homePage.providerButtonIsDisplayed(), "THE MENU IS NOT DISPLAYED PROPERLY");
        homePage.clickMenuLoginButton();
        log.info("Changes Iframes");
        LogInSignUpIframe logInSignUpIframe = homePage.switchToIframeLogInSignUp();
        log.info("Begins the procedure to LogIn with a CreatedUser");
        Assert.assertTrue(logInSignUpIframe.passwordBoxIsDisplayed(), "THE INPUT FIELDS ARE NOT DISPLAYED PROPERLY");
        logInSignUpIframe.loginAccount(userCreated.getEmail(), userCreated.getPassword());
        log.info("Returns a new HomePage variable, and hands it the Driver");
        HomePage returnedTwoHomePage = logInSignUpIframe.returnToMainPage();
        log.info("Waits for HomePage To Load");
        returnedTwoHomePage.waitHomePageToLoad();
        log.info("We make sure we are logged in");
        returnedTwoHomePage.displayMenu();
        Assert.assertTrue(homePage.profileButtonIsDisplayed(), "WE ARE NOT LOGGED IN PROPERLY");
    }



    @BeforeMethod(groups = {"Register_and_Wait"},
            description = "Registers a New User and waits for further commands to be input") //falta meterle dataprovider
    public void registerNewAccountAndWaits(Object[] data){

        CreateRandomUser newUser = (CreateRandomUser) data[0];
        log.info(newUser.getFirstName());

        log.info("Get Home Page");
        HomePage homePage = getHomePage();
        log.info("Moves cursor to the User Icon, and displays Menu");
        homePage.displayMenu();
        log.info("Clicks on the LogIn Button from the displayed Menu");
        homePage.clickMenuLoginButton();

        log.info("Changes Iframes");
        LogInSignUpIframe logInSignUpIframe = homePage.switchToIframeLogInSignUp();

        log.info("Begins the procedure to Register a NewUser");
        log.info("Clicks in the 'Sign up' option, and inputs the required data. Then, creates the NewUser");
        logInSignUpIframe.registerNewAccountInput(newUser.getFirstName(), newUser.getLastName(),
                newUser.getEmail(), newUser.getPassword());

        log.info(newUser.getFirstName());

        log.info("Returns a new HomePage variable, and hands it the Driver");
        HomePage returnedHomePage = logInSignUpIframe.returnToMainPage();

    }

    @Test(groups = {"Register_and_Wait"}, dataProviderClass = Data.class, dataProvider = "userData",
            description = "Test for the Logging Out Functionality")
    public void logoutTest(CreateRandomUser userCreated){

        String notLoggedInMessage = "Welcome!";
        HomePage homePage = getHomePage();

        log.info("Waits for HomePage to load");
        homePage.waitHomePageToLoad();
        log.info("Begins the Procedure for Logging Out");
        log.info("Moves cursor to the User Icon");
        homePage.displayMenu();
        Assert.assertTrue(homePage.profileButtonIsDisplayed(), "WE ARE NOT LOGGED IN PROPERLY");
        log.info("Clicks on the LogOut Button");
        homePage.clickMenuLogOutButton();
        log.info("Waits for an element on the previously shown menu to disappear");
        homePage.waitMenuProviderButtonInvisibility();
        log.info("Waits for HomePage to load properly");
        homePage.displayMenu();
        Assert.assertEquals(homePage.menuDisplayUserMessage(), notLoggedInMessage, "USER STILL LOGGED IN");

    }

    @Test(groups = {"Register_and_Wait"}, dataProviderClass = Data.class, dataProvider = "userData")
    public void cancelUserTest(CreateRandomUser userCreated) throws InterruptedException {

        HomePage homePage = getHomePage();
        log.info("Waits for HomePage to load");
        homePage.waitHomePageToLoad();
        log.info("Moves mouse to display the Menu");
        homePage.moveMouseToMenuDisplay();
        Assert.assertTrue(homePage.profileButtonIsDisplayed(), "WE ARE NOT LOGGED IN PROPERLY");
        log.info("Follows the procedure to cancel the Account");
        homePage.cancelAccount();
        log.info("Changes Iframes");
        LogInSignUpIframe logInSignUpIframeForCancel = homePage.switchToIframeLogInSignUp();
        log.info("Begins the procedure to Cancel the account");
        logInSignUpIframeForCancel.cancelAccount();
        log.info("Appears Confirm Cancel Message");
        logInSignUpIframeForCancel.cancelAccountConfirm();

    }

    @AfterMethod
    @Parameters({"url"})
    public void launchHomePage(String url) {
        driver.getDriver().get(url);
    }

}
