package TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.pageObjects.android.FormPage;
import org.example.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;


public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    protected FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException {
        //code to start the appium server
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//data.properties");
        properties.load(fis);
        String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"): properties.getProperty("ipAddress");
        String port = properties.getProperty("port");
        service = startAppiumServer(ipAddress,Integer.parseInt(port));
        //AndroidDriver, IOSDriver
        //Appium code -- > Appium server --> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel");
        options.setPlatformName(properties.getProperty("androidDeviceName"));
        options.setCapability("adbExecTimeout", 30000);
//        options.setChromedriverExecutable("/Users/rakshita/Downloads/chromedriver");
        options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//General-Store.apk");
        driver = new AndroidDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(600));
        formPage = new FormPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
