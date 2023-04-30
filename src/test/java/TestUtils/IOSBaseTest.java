package TestUtils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.example.pageObjects.ios.HomePage;
import org.example.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest extends AppiumUtils {
    public IOSDriver driver;
    protected HomePage homePage;

        @BeforeClass
        public void configureAppium() throws IOException {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//data.properties");
            properties.load(fis);
            String ipAddress = properties.getProperty("ipAddress");
            String port = properties.getProperty("port");
            //code to start the appium server
            service = startAppiumServer(ipAddress,Integer.parseInt(port));
            //AndroidDriver, IOSDriver
            //Appium code -- > Appium server --> Mobile
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName("iPhone 11");
            options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//UIKitCatalog.app");
            options.setPlatformVersion("13.7");
            //Appium --> Webdriver Agent --> IOS Apps
            options.setWdaLaunchTimeout(Duration.ofSeconds(20));
            driver = new IOSDriver(service.getUrl(),options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(600));
            homePage = new HomePage(driver);
        }

        @AfterClass
        public void tearDown(){
            driver.quit();
            service.stop();
        }
    }


