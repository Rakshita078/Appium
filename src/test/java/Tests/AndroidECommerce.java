package Tests;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.example.pageObjects.android.CartPage;
import org.example.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AndroidECommerce extends AndroidBaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preSetup(){
        //screen to home page
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
        driver.startActivity(activity);
    }

    @Test(dataProvider = "getData", groups= {"Smoke"})
    public void verifyFillFormDetails(HashMap<String, String> input) throws InterruptedException {
        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
        ProductCatalogue productCatalogue = formPage.submitForm();
//        String productsPage = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
//        Assert.assertEquals(productsPage,"Products");
        productCatalogue.addItemToCart();
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.waitForCartPageToLoad();
        Assert.assertEquals(cartPage.sumOfProductPrices(),cartPage.totalPurchaseAmount());
        cartPage.termsAndConditions();
        cartPage.submitorder();
    }

    @Test
    public void verifyFillFormDetailsAndToastErrorMessageForWrongInputs() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        String text = "Iraq";
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ text + "\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name");
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>>  data = getJsonData(System.getProperty("user.dir")+"//src//test//java//testData//ecommerce.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
