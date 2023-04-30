package org.example.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement displaySum;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsOfConditions;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement closeButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement visitWebsite;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement cartPage;


    public double sumOfProductPrices() {
        int count = productPrices.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productPrices.get(i).getText();
            double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public Double totalPurchaseAmount() {
        Double expectedTotalAmount = getFormattedAmount(displaySum.getText());
        return expectedTotalAmount;
    }


    public void termsAndConditions() {
        longPressAction(termsOfConditions);
        closeButton.click();
    }

    public void submitorder() {
        checkBox.click();
        visitWebsite.click();
    }
    public void waitForCartPageToLoad(){
        waitForElementToAppear(cartPage, driver);
    }
}
