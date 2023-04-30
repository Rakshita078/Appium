package org.example.pageObjects.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.example.utils.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewsPage extends IOSActions {
    IOSDriver driver;
    public AlertViewsPage(IOSDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Text Entry\"`]")
    private WebElement textEntry;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement enterText;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'OK'")
    private WebElement okButton;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Confirm / Cancel\"")
    private WebElement confirmOrCancel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'A message'")
    private WebElement textMessage;

    @iOSXCUITFindBy(accessibility = "Confirm")
    private WebElement confirmButton;

    public void fillTextLabel(String textName){
        textEntry.click();
        enterText.sendKeys(textName);
        okButton.click();
    }

    public String getConfirmMessage(){
        confirmOrCancel.click();
        return textMessage.getText();
    }

    public void clickOnConfirmButton(){
        confirmButton.click();
    }






}
