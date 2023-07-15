package pages.demoNoCommerce;

import com.ej.drivers.CYCLONE;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage {


    private CYCLONE.WebDriver driver;

    public RegisterPage(CYCLONE.WebDriver driver) {
        this.driver = driver;
    }

    private final By genderMale = By.id("gender-male");
    private final By genderMale1 = By.id("gender-malesa");

    private final By genderFemale = By.id("gender-female");
    private final By firstNameField = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By birthDayField = By.name("DateOfBirthDay");
    private final By birthMonthField = By.name("DateOfBirthMonth");
    private final By birthYearField = By.name("DateOfBirthYear");
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By confPasswordField = By.id("ConfirmPassword");
    private final By confRegisterBtn = By.id("register-button");

    @Step("fillRegisterInfoMale")
    public RegisterPage fillRegisterInfoMale(String firstName, String lastName, String birthDay, String birthMonth,
                                             String birthYear, String email, String password, String confPassword) {

        driver.elementAction().click(genderMale);
        driver.elementAction().sendKeys(firstNameField, firstName);
        driver.elementAction().sendKeys(lastNameField, lastName);
        driver.elementAction().selectByValue(birthDayField, birthDay);
        driver.elementAction().selectByValue(birthMonthField, birthMonth);
        driver.elementAction().selectByValue(birthYearField, birthYear);
        driver.elementAction().sendKeys(emailField, email);
        driver.elementAction().sendKeys(passwordField, password);
        driver.elementAction().sendKeys(confPasswordField, confPassword);

        return this;
    }


    @Step("clickOnConfRegBtn")
    public ConfirmRegisterPage clickOnConfRegBtn() {
        driver.elementAction().click(confRegisterBtn);
        return new ConfirmRegisterPage(driver);
    }

}


