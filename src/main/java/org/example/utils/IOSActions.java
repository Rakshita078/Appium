package org.example.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class IOSActions extends AppiumUtils {

    IOSDriver driver;
    public IOSActions(IOSDriver driver) {
        this.driver = driver;
    }

    public void longPressAction(WebElement ele){
        Map<String, Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement)ele).getId());
        params.put("duration",5);
        driver.executeScript("mobile:touchAndHold",params);
    }
    public void scrollAction(WebElement ele, String dir){
        Map<String, Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)ele).getId());
        params.put("direction",dir);
        driver.executeScript("mobile:scroll", params);
    }

    public void swipeAction(String dir){
        Map<String, Object> params = new HashMap<>();
        //params.put("element",((RemoteWebElement)ele).getId());
        params.put("direction",dir);
        driver.executeScript("mobile:swipe", params);
    }
}
