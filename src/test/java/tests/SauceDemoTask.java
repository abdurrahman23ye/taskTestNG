package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.CompletePage;
import pages.OverviewPage;
import pages.SauceDemoMainPage;
import utulities.*;

import java.io.IOException;

import static utulities.ItemAssertDatas.*;

public class SauceDemoTask extends TestBaseReport {

    SauceDemoMainPage sauceDemoMainPage=new SauceDemoMainPage();
    ItemAssertDatas itemAssertDatas=new ItemAssertDatas();
    CheckoutPage checkoutPage=new CheckoutPage();
    OverviewPage overviewPage= new OverviewPage();
    CompletePage completePage= new CompletePage();

    @Test(groups = "task")
    public void sauceDemoTask() throws IOException {

        extentTest=extentReports.
                createTest("Complete an order");


        Driver.getDriver().get(ConfigReader.getProperty("sauceDemo"));

        extentTest.info("User opens sauceDemo webpage");


        sauceDemoMainPage.usernameTextBox.sendKeys(ConfigReader.getProperty("username"));

        extentTest.info("User writes 'username' to usernames textbox");



        sauceDemoMainPage.passWordTextBox.sendKeys(ConfigReader.getProperty("password"));

        extentTest.info("User writes 'password' to passwords textbox");


        sauceDemoMainPage.loginButton.click();

        extentTest.info("User clicks 'login' button");


        itemAssertDatas.itemAssertDatas();

        sauceDemoMainPage.firstProductAddToCartButton.click();
        sauceDemoMainPage.secondProductAddToCartButton.click();

        extentTest.info("User chooses random two product and adds them to cart");


        sauceDemoMainPage.cartButton.click();
        extentTest.info("User clicks 'cart' button");


        ((JavascriptExecutor) Driver.getDriver()).
                executeScript("arguments[0].scrollIntoView(true);", sauceDemoMainPage.checkoutButton);
        sauceDemoMainPage.checkoutButton.click();

        extentTest.info("User clicks 'checkout' button");



        ((JavascriptExecutor) Driver.getDriver()).
                executeScript("arguments[0].scrollIntoView(true);", checkoutPage.firstNameTextbox);

        checkoutPage.firstNameTextbox.sendKeys(ConfigReader.getProperty("name"));
        checkoutPage.lastNameTextbox.sendKeys(ConfigReader.getProperty("lastName"));
        checkoutPage.postalCodeTextbox.sendKeys(ConfigReader.getProperty("zip"));


        extentTest.info("User fills name,last name and zip code infos to the textboxs");

        checkoutPage.continueButton.click();

        extentTest.info("User clicks 'continue' button");


        double firstItemPrice=Double.valueOf(overviewPage.
                firstAddedItemPrice.getText().replace("$",""));

        double secondItemPrice=Double.valueOf(overviewPage.
                secondAddedItemPrice.getText().replace("$",""));

        double actualResult=firstItemPrice+secondItemPrice;


        Assert.assertEquals(item1text,overviewPage.firstAddedItem.getText());
        Assert.assertEquals(item2text,overviewPage.secondAddedItem.getText());
        Assert.assertEquals(totalPrice, actualResult, 0.0);


        extentTest.info("User verifies added products and total price");


        overviewPage.finishButton.click();
        extentTest.info("User clicks 'finish' button");


        Assert.assertTrue(completePage.completeMessageLabel.getText().contains("Thank you"));

        ReusableMethods.getScreenshot("transactionCompleteText");

        extentTest.info("User verifies complete transaction by the see the text that include 'Thank you'");

        Driver.closeDriver();
        extentTest.pass("User close web page");

























    }
}
