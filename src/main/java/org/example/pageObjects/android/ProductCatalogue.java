package org.example.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AndroidActions {
    AndroidDriver driver;
    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private WebElement product1;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PG 3']/following-sibling::*//android.widget.TextView[@text='ADD TO CART']")
    private WebElement product2;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    public void addItemToCart(){
        product1.click();
        scrollToText("PG 3");
        product2.click();
    }

    public CartPage goToCartPage(){
        cart.click();
       return new CartPage(driver);
    }
}
