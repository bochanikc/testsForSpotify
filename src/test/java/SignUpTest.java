import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");
    }

    @Test
    public void typeInvalidYear(){
        page = new SignUpPage(driver);
        page.setMonth("March")
            .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertFalse(page.isErrorVisible("Please enter a valid day of the month."));
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("Please enter your birth month."));
    }

    @Test
    public void typeInvalidEmail(){
        page = new SignUpPage(driver);
        page.typeEmail("2314выа")
            .typeConfirmEmail("2314выа");
        Assert.assertTrue(page.isErrorVisible("The email address you supplied is invalid."));
        Assert.assertFalse(page.isErrorVisible("Please enter your email."));
    }

    @Test
    public void signUpWithEmptyPassword(){
        page = new SignUpPage(driver);
        page.typeEmail("test4747@test.test")
                .typeConfirmEmail("test4747@test.test")
                .typeName("Testname")
                .setMonth("March")
                .typeDay("20")
                .typeYear("2000")
                .setSex("Male")
                .setShare(true)
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void clickSignUpEmptyLabels(){
        page = new SignUpPage(driver);
        page.typeName("Name")
            .setSex("Female")
            .clickSignUpButton();
        Assert.assertEquals("Please choose a password.", page.getErrorByNumber(3));
        Assert.assertEquals(7, page.getErrors().size());
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
