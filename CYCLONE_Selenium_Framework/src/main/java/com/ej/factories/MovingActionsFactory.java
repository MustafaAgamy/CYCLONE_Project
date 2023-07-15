package com.ej.factories;

import com.ej.drivers.DriverFactoryHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


public class MovingActionsFactory {

    private MovingActionsFactory(){}
    public static WebElement moveToElementAndHighLight(FluentWaitFactory.WaitStrategy waitStrategy, By by) {
        WebElement webElement = FluentWaitFactory.FluentWait(waitStrategy, by);
        try {
            ScriptsActionsFactory.moveToElementScript (webElement);

        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            hoverToElement(by);
        }

        ScriptsActionsFactory.highLightElementScript(webElement);
            ScriptsActionsFactory.removeHighLightScript(webElement);
            return webElement;
    }

    public static void hoverToElement(By by){
        actions().moveToElement(moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by)).perform();
    }

    public static void moveAndClickUsingJavaScript(FluentWaitFactory.WaitStrategy waitStrategy, By locator) {
        ScriptsActionsFactory.clickUsingJavaScript(moveToElementAndHighLight(waitStrategy, locator));
    }

    public static void moveAndSendKeysUsingJavaScript(FluentWaitFactory.WaitStrategy waitStrategy, By locator, String value) {
        ScriptsActionsFactory.sendKeysUsingJavaScript(moveToElementAndHighLight(waitStrategy, locator),value);
    }

    //TODO -> Test this Method for better use.
    public static Actions actions (){
        return new Actions((WebDriver) DriverFactoryHelper.getDriver().get());
    }


    //TODO -> Need to implement a property key for using this Method.
    @Deprecated
    public static WebElement moveToElement(WebDriver driver, FluentWaitFactory.WaitStrategy waitStrategy, By locator) {
        WebElement webElement = FluentWaitFactory.FluentWait(waitStrategy, locator);
        ScriptsActionsFactory.moveToElementScript(webElement);
        return webElement;
    }
}
