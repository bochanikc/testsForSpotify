import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

public class SignUpPage {
    WebDriver driver;


    public SignUpPage(WebDriver driver) {
        this.driver = driver;

    }

    By emailField = By.cssSelector("input#register-email");
    By confirmEmailField = By.cssSelector("input#register-confirm-email");
    By passwordField = By.cssSelector("input#register-password");
    By nameField = By.cssSelector("input#register-displayname");
    By monthDropDown = By.cssSelector("select#register-dob-month");
    String monthDropDownOption = "//select[@id = 'register-dob-month']/option[text() = '%s']";
    By dayField = By.cssSelector("input#register-dob-day");
    By yearField = By.cssSelector("input#register-dob-year");
    String sexRadioButton = "//li[@id='li-gender']/label[normalize-space()='%s']/input";
    By shareCheckbox = By.cssSelector("input#register-thirdparty");
    By registerEmail = By.cssSelector("a#register-button-email-submit");
    By errorLabel = By.xpath("//label[@class='has-error' and string-length(text())>0]");
    String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";

    public SignUpPage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email){
        driver.findElement(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name){
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month){
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 2).until(visibilityOfAllElementsLocatedBy(By.xpath(format(monthDropDownOption, month))));
        driver.findElement(By.xpath(format(monthDropDownOption, month))).click();
        return this;
    }
    public SignUpPage typeDay(String day){
        driver.findElement(dayField).sendKeys(day);
        return this;
    }
    public SignUpPage typeYear(String year) {
        driver.findElement(yearField).sendKeys(year);
        return this;
    }
    public SignUpPage setSex(String value) {
        driver.findElement(By.xpath(format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        WebElement checkbox = driver.findElement(shareCheckbox);
        if (!checkbox.isSelected() == value){
            checkbox.click();
        }
        return this;
    }

    public void clickSignUpButton(){
        driver.findElement(registerEmail).click();
    }

    public List<WebElement> getErrors(){
        return driver.findElements(errorLabel);
    }
    public String getErrorByNumber(int number){
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible(String message){
        return driver.findElements(By.xpath(format(errorByText, message))).size() > 0
                && driver.findElements(By.xpath(format(errorByText, message))).get(0).isDisplayed();
    }
}
