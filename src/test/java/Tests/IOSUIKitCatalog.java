package Tests;

import TestUtils.IOSBaseTest;
import org.example.pageObjects.ios.AlertViewsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IOSUIKitCatalog extends IOSBaseTest {
    @Test
    public void iOSBasicTest(){
        //xpath, classname, Ios, iosClassChain, IOSPredicateString, accessibility id, id
        AlertViewsPage alertViewsPage = homePage.selectAlertViewsOption();
        alertViewsPage.fillTextLabel("Hello!");
        String actualMessage = alertViewsPage.getConfirmMessage();
        Assert.assertEquals(actualMessage,"A message should be a short, complete sentence.");
        alertViewsPage.clickOnConfirmButton();
    }
}
