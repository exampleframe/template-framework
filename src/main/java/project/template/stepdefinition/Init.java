package project.template.stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

final public class  Init {
    private static WebDriver driver;

    @Before
    public void start(){
        setProperty();

        driver = getWebDriver();

        Long timeout = Long.parseLong(System.getProperty("timeout"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static void setProperty() {
        Properties props = new Properties();
        try {
            props.load(new FileReader("./src/test/config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        props.stringPropertyNames().stream().forEach(key -> System.setProperty(key, props.getProperty(key)));
    }

}
