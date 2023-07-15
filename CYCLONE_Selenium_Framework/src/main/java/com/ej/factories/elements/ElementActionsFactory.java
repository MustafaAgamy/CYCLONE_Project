package com.ej.factories.elements;


import com.ej.drivers.DriverFactoryHelper;
import com.ej.factories.FluentWaitFactory;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.utils.LoggingManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import static com.ej.factories.MovingActionsFactory.*;

@SuppressWarnings("unused")
public class ElementActionsFactory {

    private static final ThreadLocal<ElementActionsFactory> elementActionsFactory = new ThreadLocal<>();

    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();
    private ElementActionsFactory() {
    }
    public static synchronized ElementActionsFactory getInstance() {
        if (elementActionsFactory.get() == null) {
            elementActionsFactory.set(new ElementActionsFactory());
        }
        return elementActionsFactory.get();
    }

    public ElementActionsFactory and() {
        return this;
    }
    public ElementActionsFactory then() {
        return this;
    }

    public ElementActionsFactory click(By by) {
        try {
            moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.CLICKABLE, by).click();
        }catch (StaleElementReferenceException | NoSuchElementException e){
        //Click using Actions in case Click fails.
            clickUsingActions(by);
        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logClick(by);
        return this;
    }
    public  ElementActionsFactory sendKeys(By by, String value) {

        WebElement webElement = ((WebDriver)DriverFactoryHelper.getDriver().get()).findElement(by);
        try {

        if(!(webElement.getAttribute("value").isBlank())){
            webElement.clear();

        }
            moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).sendKeys(value);
        } catch (Exception e){
            //SendKeys using Actions in case sendKeys fails.
            sendKeysUsingActions(by, value);
        }
        catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logSendKeys(by, value);
        return this;
    }
    public String getText(By by) {

        String elementText = "";
        try {
             elementText = moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).getText();
        }
        catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logGetText(by);
        return elementText;
    }
    public ElementActionsFactory clear(By by) {
        try {
            moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).clear();
        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logClear(by);
        return this;
    }


    private ElementActionsFactory clickUsingJavaScript(By by){
        moveAndClickUsingJavaScript(FluentWaitFactory.WaitStrategy.CLICKABLE,by);
        return this;
    }

    private ElementActionsFactory sendKeysUsingJavaScript(By by, String value){
        moveAndSendKeysUsingJavaScript(FluentWaitFactory.WaitStrategy.PRESENCE, by, value);
        return this;
    }

    public ElementActionsFactory clickUsingActions(By by){
        WebElement webElement = FluentWaitFactory.FluentWait(FluentWaitFactory.WaitStrategy.PRESENCE, by);
        actions().moveToElement(moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by)).click(webElement).perform();
        return this;
    }

    public ElementActionsFactory sendKeysUsingActions(By by,String value){
        WebElement webElement = FluentWaitFactory.FluentWait(FluentWaitFactory.WaitStrategy.PRESENCE, by);
        actions().moveToElement(moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by)).sendKeys(value).perform();
        return this;
    }

    public  ElementActionsFactory selectByText(By by, String text) {
        try {
            selectAction(by).selectByVisibleText(text);
        }
        catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logSelectText(by, text);
        return this;
    }

    public  ElementActionsFactory selectByValue(By by, String value) {
        try {
            selectAction(by).selectByValue(value);
        }
        catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logSelectValue(by, value);
        return this;
    }

    public  ElementActionsFactory selectByIndex(By by, int index) {
        try {
            selectAction(by).selectByIndex(index);
        }
        catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logSelectIndex(by, index);
        return this;
    }

    public boolean isDisplayed(By by){
        boolean isDisplayed = false;
        try {
            isDisplayed = moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).isDisplayed();
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logIsDisplayed(by);
        return isDisplayed;
    }

    public boolean isEnabled(By by){
        boolean isEnabled = false;
        try {
            isEnabled = moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).isEnabled();
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logIsEnabled(by);
        return isEnabled;
    }

    public boolean isSelected(By by){
        boolean isSelected = false;
        try {
            isSelected = moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by).isSelected();
        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logIsSelected(by);
        return isSelected;
    }

    public ElementActionsFactory hoverTo(By by){
        try {
            hoverToElement(by);

        }catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(by,throwable);
            }else {
                throw throwable;
            }
        }
        LoggingManager.logHover(by);
        return this;
    }

    private Select  selectAction(By by) {
        WebElement webElement = moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, by);
        return new Select(webElement);
    }

    @Deprecated
    public static String PerformAction(WebDriver driver, By locator, ElementActions action, Object parameter){
        switch (action){
            case CLICK -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.CLICKABLE, locator).click();
            case CLEAR -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, locator).clear();
            case SEND_KEYS -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, locator).sendKeys(new CharSequence[]{(CharSequence)parameter});
            case GET_TEXT -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, locator).getText();
            case GET_VALUE -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, locator).getAttribute(TextDetections.VALUE.value);
            case GET_CONTENT -> moveToElementAndHighLight(FluentWaitFactory.WaitStrategy.PRESENCE, locator).getAttribute(TextDetections.CONTENT.value);
        }
        return "";
    }

    @Deprecated
    public  ElementActionsFactory select(By by, FluentWaitFactory.WaitStrategy waitStrategy , SelectActions selectActions, String selection) {
        switch (selectActions){
            case VALUE -> selectByValue(by, selection);
            case INDEX -> selectByIndex(by, Integer.parseInt(selection));
            case TEXT -> selectByText(by, selection);
        }
        return this;
    }

    public enum TextDetections{
        VALUE("value"),
        TEXT("text"),
        CONTENT("textContent");
        private final String value;

        private TextDetections(String detectionStrategy) {
            this.value = detectionStrategy;
        }

        public String getValue() {
            return this.value;
        }

    }
    public enum ElementActions {

        CLICK,
        SEND_KEYS,
        GET_TEXT,
        CLEAR,
        GET_VALUE,
        GET_CONTENT;
    }

    public enum SelectActions{

        VALUE,
        INDEX,
        TEXT;
    }
}
