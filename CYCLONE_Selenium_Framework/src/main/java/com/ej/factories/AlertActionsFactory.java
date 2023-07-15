package com.ej.factories;


import com.ej.drivers.DriverFactoryHelper;
import com.ej.factories.elements.ElementActionsFactory;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.utils.LoggingManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
@SuppressWarnings("unused")
public class AlertActionsFactory {

    public AlertActionsFactory(WebDriver driver) {
        new AlertActionsFactory();
    }

    public AlertActionsFactory() {
    }

    public BrowserActionsFactory browser() {
        return BrowserActionsFactory.getInstance();
    }

    public static AlertActionsFactory getInstance() {
        return new AlertActionsFactory();
    }

    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();

    public AlertActionsFactory and() {
        return this;
    }
    public AlertActionsFactory then() {
        return this;
    }
    private static void waitForAlertPresence() {
        try {
            (new WebDriverWait((WebDriver) DriverFactoryHelper.getDriver().get(), Duration.ofSeconds(20))).until(ExpectedConditions.alertIsPresent());
            ((WebDriver)DriverFactoryHelper.getDriver().get()).switchTo().alert();
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
    }

    public ElementActionsFactory performElementAction() {
        return ElementActionsFactory.getInstance();
    }

    public AlertActionsFactory acceptAlert() {
        waitForAlertPresence();

        try {
            ((WebDriver)DriverFactoryHelper.getDriver().get()).switchTo().alert().accept();
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logAcceptAlert();
        return this;
    }
    public AlertActionsFactory dismissAlert() {
        waitForAlertPresence();

        try {
            ((WebDriver)DriverFactoryHelper.getDriver().get()).switchTo().alert().dismiss();
        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logDismissAlert();
        return this;
    }
    public String getAlertText() {
        waitForAlertPresence();
        String alertText = "";
        try {
          alertText = ((WebDriver)DriverFactoryHelper.getDriver().get()).switchTo().alert().getText();
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logTextAlert();
        return alertText;
    }
    public AlertActionsFactory typeIntoAlert(String text) {
        waitForAlertPresence();
        try {
            ((WebDriver)DriverFactoryHelper.getDriver().get()).switchTo().alert().sendKeys(text);

        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logTypeAlert(text);
        return this;
    }

}