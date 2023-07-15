package com.ej.factories;

import com.ej.drivers.DriverFactoryHelper;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.properties.setup.Timeout;
import com.ej.utils.LoggingManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;


public class FluentWaitFactory {
    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();
    private static final Timeout timeoutSetups = ConfigFactory.create(Timeout.class);
    private static final int WAIT_TIME_OUT = timeoutSetups.elementWaitTimeOut();
    private static final int POLLING_DELAY = timeoutSetups.fluentWaitPollingDelay();



    public static WebElement FluentWait(WaitStrategy waitStrategy, By by){

        var ignoredExceptions = ignoredExceptions(true);
        var wait = new FluentWait<>((WebDriver)DriverFactoryHelper.getDriver().get()).withTimeout(Duration.ofMillis(WAIT_TIME_OUT))
                .pollingEvery(Duration.ofMillis(POLLING_DELAY))
                .ignoreAll(ignoredExceptions);


//        wait.until((ExpectedCondition<Boolean>) webDriver -> {
//            JavascriptExecutor js = (JavascriptExecutor) ((WebDriver) DriverFactoryHelper.getDriver().get());
//            return (Boolean) js.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
//        });

        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) ((WebDriver) DriverFactoryHelper.getDriver().get());
            boolean jqueryComplete = (Boolean) js.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            boolean javascriptComplete = (Boolean) js.executeScript("return document.readyState === 'complete';");
            return jqueryComplete && javascriptComplete;
        });

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(e);
            }else {
                throw new RuntimeException(e);
            }
        }

        switch (waitStrategy){
            case PRESENCE ->
            {
                new WebDriverWait((WebDriver) DriverFactoryHelper.getDriver().get(), Duration.ofMillis(WAIT_TIME_OUT))
                        .until(ExpectedConditions.presenceOfElementLocated(by));
                return wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.presenceOfElementLocated(by)));

            }
            case CLICKABLE ->
            {

                ignoredExceptions.add(ElementClickInterceptedException.class);
                new WebDriverWait((WebDriver)DriverFactoryHelper.getDriver().get(), Duration.ofMillis(WAIT_TIME_OUT))
                        .until(ExpectedConditions.presenceOfElementLocated(by));
                return  wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(by)));
            }
            case VISIBLE ->
            {
                 new WebDriverWait((WebDriver)DriverFactoryHelper.getDriver().get(), Duration.ofMillis(WAIT_TIME_OUT))
                        .until(ExpectedConditions.visibilityOfElementLocated(by));
                return wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(by)));
            }
            default ->
                 throw new RuntimeException("No Valid Wait Condition Provided");
        }
    }
    public static ArrayList<Class<? extends Exception>> ignoredExceptions(boolean checkForVisiblity) {
        ArrayList<Class<? extends Exception>> ignoredExceptions = new ArrayList<>();
        ignoredExceptions.add(NoSuchElementException.class);
        ignoredExceptions.add(StaleElementReferenceException.class);
        ignoredExceptions.add(ElementNotInteractableException.class);
        ignoredExceptions.add(JavascriptException.class);
        if (checkForVisiblity) {
            ignoredExceptions.add(InvalidElementStateException.class);
            ignoredExceptions.add(org.openqa.selenium.interactions.MoveTargetOutOfBoundsException.class);
        }
        return ignoredExceptions;
    }


    public enum WaitStrategy {

        CLICKABLE,
        PRESENCE,
        VISIBLE,
        NONE;
    }
}
