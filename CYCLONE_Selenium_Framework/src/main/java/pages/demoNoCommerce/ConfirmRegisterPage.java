package pages.demoNoCommerce;

import com.ej.drivers.CYCLONE;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ConfirmRegisterPage {

    private CYCLONE.WebDriver driver;

    public ConfirmRegisterPage(CYCLONE.WebDriver driver){
        this.driver = driver;
    }

    private final By registerMsg = By.className("result");

    @Step("getSuccessMsg")
    public String getSuccessMsg(){
         return driver.elementAction().getText(registerMsg);
    }
}

