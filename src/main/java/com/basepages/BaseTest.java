package com.basepages;

import com.driver.DriverManager;
import com.utility.ScreenRecorderUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public class BaseTest {
    //buradan yönetebiliriz ekran videosu almayı. 
    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        DriverManager.setDriver(new ChromeDriver(getChromeOptions()));
        ScreenRecorderUtil.startRecord("setUp");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        DriverManager.quit();
        ScreenRecorderUtil.stopRecord();
    }

    public ChromeOptions getChromeOptions(){
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);

        ChromeOptions chromeUp = new ChromeOptions();
        chromeUp.setExperimentalOption("prefs", chromePrefs);
        chromeUp.addArguments( "--start-fullscreen");
        return chromeUp;
    }
}
