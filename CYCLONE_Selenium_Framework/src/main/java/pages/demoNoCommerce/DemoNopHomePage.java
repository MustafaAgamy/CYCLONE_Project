package pages.demoNoCommerce;

import com.ej.drivers.CYCLONE;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class DemoNopHomePage {

   private CYCLONE.WebDriver driver;

   public DemoNopHomePage(CYCLONE.WebDriver driver){
       this.driver = driver;
   }

    private final By registerBtn = By.className("ico-register");



    @Step("clickRegisterBtn")
    public RegisterPage clickRegisterBtn(){
        driver.elementAction().click(registerBtn);
        return new RegisterPage(driver);
    }
}
