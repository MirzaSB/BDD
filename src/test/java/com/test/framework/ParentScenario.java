package com.test.framework;

import com.test.framework.page_objects.YearlyBoxOfficePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * The Parent Scenario.
 * </p>
 */
public class ParentScenario {

    /**
     * Framework variables.
     */
    public WebDriver driver;
    public YearlyBoxOfficePage ybo;

    /**
     * <p>
     * Closes the browser.
     * </p>
     */
    protected void closeBrowser() {
        driver.quit();
    }

    /**
     * <p>
     * Navigates to the Box Office page based on the year.
     * </p>
     *
     * @param year {@link Integer} - The year.
     */
    protected void navigateToBoxOfficePage(int year) {
        driver.get("http://www.boxofficemojo.com/yearly/chart/?view2=worldwide&yr=" + year);
    }

    /**
     * <p>
     * Sets the Chrome driver.
     * </p>
     */
    protected void setChromeDriver() {
        // Initialize a string to store the driver path.
        String driverPath;
        // Get the current path.
        String currentPath = System.getProperty("user.dir");
        driverPath = currentPath + File.separator + "drivers" + File.separator;
        // Get the current OS.
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")) {
            driverPath = driverPath + "chromedriver.exe";
        }
        else if (os.contains("mac")) {
            driverPath = driverPath + "chromedriver";
        }
        else if (os.contains("linux")) {
            driverPath = driverPath + "chromedriver_linux";
        }
        else {
            Assert.fail("No driver found.");
        }
        // Set the driver property.
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    /**
     * <p>
     * Starts the browser.
     * </p>
     */
    protected void startBrowser() {
        setChromeDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ybo = new YearlyBoxOfficePage(driver);
    }

}