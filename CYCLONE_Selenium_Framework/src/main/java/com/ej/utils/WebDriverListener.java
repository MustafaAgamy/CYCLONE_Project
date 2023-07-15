package com.ej.utils;

import com.ej.properties.setup.Timeout;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.FluentWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.ej.factories.FluentWaitFactory.ignoredExceptions;

public class WebDriverListener implements org.openqa.selenium.support.events.WebDriverListener {

    private static final Timeout timeOut = ConfigFactory.create(Timeout.class);
    private static final int WAIT_TIME_OUT = timeOut.elementWaitTimeOut();
    private static final int POLLING_DELAY = timeOut.fluentWaitPollingDelay();
    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyCall(target, method, args);
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyCall(target, method, args, result);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
//        LoggingManager.logThrowable(timeoutException.getMessage() + " || " + timeoutException.getCause().getMessage().substring(0, timeoutException.getCause().getMessage().indexOf("\n")));
//        e.getCause().getMessage().substring(0, e.getCause().getMessage().indexOf("\n"))
        if (e.getCause() instanceof TimeoutException) {
            //Do Nothing
        } else {
            String methodName = Thread.currentThread().getStackTrace()[6].getMethodName();
//            LoggingManager.logMethodError(methodName + "failed");
            LoggingManager.logThrowable(methodName + " ->" + Arrays.toString(args) + "\n" + " || ", e);
        }
    }

    @Override
    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyWebDriverCall(driver, method, args);
    }

    @Override
    public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyWebDriverCall(driver, method, args, result);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGet(driver, url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        LoggingManager.logNavigateUrl(url);
    }

    @Override
    public void beforeGetCurrentUrl(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetCurrentUrl(driver);
    }

    @Override
    public void afterGetCurrentUrl(String result, WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetCurrentUrl(result, driver);
    }

    @Override
    public void beforeGetTitle(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetTitle(driver);
    }

    @Override
    public void afterGetTitle(WebDriver driver, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetTitle(driver, result);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(WAIT_TIME_OUT))
                    .pollingEvery(Duration.ofMillis(POLLING_DELAY))
                    .ignoreAll(ignoredExceptions(false))
                    .until(webDriver -> webDriver.findElement(locator));
        }
        catch (TimeoutException e){
            //Do Nothing
        }

    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterFindElement(driver, locator, result);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeFindElements(driver, locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterFindElements(driver, locator, result);
    }

    @Override
    public void beforeGetPageSource(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetPageSource(driver);
    }

    @Override
    public void afterGetPageSource(WebDriver driver, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetPageSource(driver, result);
    }

    @Override
    public void beforeClose(WebDriver driver) {
    }

    @Override
    public void afterClose(WebDriver driver) {
        LoggingManager.logCloseDriver();
    }

    @Override
    public void beforeQuit(WebDriver driver) {
    }

    @Override
    public void afterQuit(WebDriver driver) {
        LoggingManager.logQuitDriver();
    }

    @Override
    public void beforeGetWindowHandles(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetWindowHandles(driver);
    }

    @Override
    public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetWindowHandles(driver, result);
    }

    @Override
    public void beforeGetWindowHandle(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetWindowHandle(driver);
    }

    @Override
    public void afterGetWindowHandle(WebDriver driver, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetWindowHandle(driver, result);
    }

    @Override
    public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeExecuteScript(driver, script, args);
    }

    @Override
    public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterExecuteScript(driver, script, args, result);
    }

    @Override
    public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeExecuteAsyncScript(driver, script, args);
    }

    @Override
    public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterExecuteAsyncScript(driver, script, args, result);
    }

    @Override
    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforePerform(driver, actions);
    }

    @Override
    public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterPerform(driver, actions);
    }

    @Override
    public void beforeResetInputState(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeResetInputState(driver);
    }

    @Override
    public void afterResetInputState(WebDriver driver) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterResetInputState(driver);
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyWebElementCall(element, method, args);
    }

    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyWebElementCall(element, method, args, result);
    }

    @Override
    public void beforeClick(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeClick(element);
    }

    @Override
    public void afterClick(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterClick(element);
    }

    @Override
    public void beforeSubmit(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSubmit(element);
    }

    @Override
    public void afterSubmit(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSubmit(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSendKeys(element, keysToSend);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSendKeys(element, keysToSend);
    }

    @Override
    public void beforeClear(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeClear(element);
    }

    @Override
    public void afterClear(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterClear(element);
    }

    @Override
    public void beforeGetTagName(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetTagName(element);
    }

    @Override
    public void afterGetTagName(WebElement element, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetTagName(element, result);
    }

    @Override
    public void beforeGetAttribute(WebElement element, String name) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetAttribute(element, name);
    }

    @Override
    public void afterGetAttribute(WebElement element, String name, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetAttribute(element, name, result);
    }

    @Override
    public void beforeIsSelected(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeIsSelected(element);
    }

    @Override
    public void afterIsSelected(WebElement element, boolean result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterIsSelected(element, result);
    }

    @Override
    public void beforeIsEnabled(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeIsEnabled(element);
    }

    @Override
    public void afterIsEnabled(WebElement element, boolean result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterIsEnabled(element, result);
    }

    @Override
    public void beforeGetText(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetText(element);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetText(element, result);
    }

    @Override
    public void beforeFindElement(WebElement element, By locator) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeFindElement(element, locator);
    }

    @Override
    public void afterFindElement(WebElement element, By locator, WebElement result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterFindElement(element, locator, result);
    }

    @Override
    public void beforeFindElements(WebElement element, By locator) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeFindElements(element, locator);
    }

    @Override
    public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterFindElements(element, locator, result);
    }

    @Override
    public void beforeIsDisplayed(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeIsDisplayed(element);
    }

    @Override
    public void afterIsDisplayed(WebElement element, boolean result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterIsDisplayed(element, result);
    }

    @Override
    public void beforeGetLocation(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetLocation(element);
    }

    @Override
    public void afterGetLocation(WebElement element, Point result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetLocation(element, result);
    }

    @Override
    public void beforeGetSize(WebElement element) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetSize(element);
    }

    @Override
    public void afterGetSize(WebElement element, Dimension result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetSize(element, result);
    }

    @Override
    public void beforeGetCssValue(WebElement element, String propertyName) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetCssValue(element, propertyName);
    }

    @Override
    public void afterGetCssValue(WebElement element, String propertyName, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetCssValue(element, propertyName, result);
    }

    @Override
    public void beforeAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyNavigationCall(navigation, method, args);
    }

    @Override
    public void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyNavigationCall(navigation, method, args, result);
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeTo(navigation, url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterTo(navigation, url);
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, URL url) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeTo(navigation, url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, URL url) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterTo(navigation, url);
    }

    @Override
    public void beforeBack(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeBack(navigation);
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterBack(navigation);
    }

    @Override
    public void beforeForward(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeForward(navigation);
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterForward(navigation);
    }

    @Override
    public void beforeRefresh(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeRefresh(navigation);
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterRefresh(navigation);
    }

    @Override
    public void beforeAnyAlertCall(Alert alert, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyAlertCall(alert, method, args);
    }

    @Override
    public void afterAnyAlertCall(Alert alert, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyAlertCall(alert, method, args, result);
    }

    @Override
    public void beforeAccept(Alert alert) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAccept(alert);
    }

    @Override
    public void afterAccept(Alert alert) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAccept(alert);
    }

    @Override
    public void beforeDismiss(Alert alert) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeDismiss(alert);
    }

    @Override
    public void afterDismiss(Alert alert) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterDismiss(alert);
    }

    @Override
    public void beforeGetText(Alert alert) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetText(alert);
    }

    @Override
    public void afterGetText(Alert alert, String result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetText(alert, result);
    }

    @Override
    public void beforeSendKeys(Alert alert, String text) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSendKeys(alert, text);
    }

    @Override
    public void afterSendKeys(Alert alert, String text) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSendKeys(alert, text);
    }

    @Override
    public void beforeAnyOptionsCall(WebDriver.Options options, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyOptionsCall(options, method, args);
    }

    @Override
    public void afterAnyOptionsCall(WebDriver.Options options, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyOptionsCall(options, method, args, result);
    }

    @Override
    public void beforeAddCookie(WebDriver.Options options, Cookie cookie) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAddCookie(options, cookie);
    }

    @Override
    public void afterAddCookie(WebDriver.Options options, Cookie cookie) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAddCookie(options, cookie);
    }

    @Override
    public void beforeDeleteCookieNamed(WebDriver.Options options, String name) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeDeleteCookieNamed(options, name);
    }

    @Override
    public void afterDeleteCookieNamed(WebDriver.Options options, String name) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterDeleteCookieNamed(options, name);
    }

    @Override
    public void beforeDeleteCookie(WebDriver.Options options, Cookie cookie) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeDeleteCookie(options, cookie);
    }

    @Override
    public void afterDeleteCookie(WebDriver.Options options, Cookie cookie) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterDeleteCookie(options, cookie);
    }

    @Override
    public void beforeDeleteAllCookies(WebDriver.Options options) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeDeleteAllCookies(options);
    }

    @Override
    public void afterDeleteAllCookies(WebDriver.Options options) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterDeleteAllCookies(options);
    }

    @Override
    public void beforeGetCookies(WebDriver.Options options) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetCookies(options);
    }

    @Override
    public void afterGetCookies(WebDriver.Options options, Set<Cookie> result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetCookies(options, result);
    }

    @Override
    public void beforeGetCookieNamed(WebDriver.Options options, String name) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetCookieNamed(options, name);
    }

    @Override
    public void afterGetCookieNamed(WebDriver.Options options, String name, Cookie result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetCookieNamed(options, name, result);
    }

    @Override
    public void beforeAnyTimeoutsCall(WebDriver.Timeouts timeouts, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyTimeoutsCall(timeouts, method, args);
    }

    @Override
    public void afterAnyTimeoutsCall(WebDriver.Timeouts timeouts, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyTimeoutsCall(timeouts, method, args, result);
    }

    @Override
    public void beforeImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeImplicitlyWait(timeouts, duration);
    }

    @Override
    public void afterImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterImplicitlyWait(timeouts, duration);
    }

    @Override
    public void beforeSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSetScriptTimeout(timeouts, duration);
    }

    @Override
    public void afterSetScriptTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSetScriptTimeout(timeouts, duration);
    }

    @Override
    public void beforePageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforePageLoadTimeout(timeouts, duration);
    }

    @Override
    public void afterPageLoadTimeout(WebDriver.Timeouts timeouts, Duration duration) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterPageLoadTimeout(timeouts, duration);
    }

    @Override
    public void beforeAnyWindowCall(WebDriver.Window window, Method method, Object[] args) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeAnyWindowCall(window, method, args);
    }

    @Override
    public void afterAnyWindowCall(WebDriver.Window window, Method method, Object[] args, Object result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterAnyWindowCall(window, method, args, result);
    }

    @Override
    public void beforeGetSize(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetSize(window);
    }

    @Override
    public void afterGetSize(WebDriver.Window window, Dimension result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetSize(window, result);
    }

    @Override
    public void beforeSetSize(WebDriver.Window window, Dimension size) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSetSize(window, size);
    }

    @Override
    public void afterSetSize(WebDriver.Window window, Dimension size) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSetSize(window, size);
    }

    @Override
    public void beforeGetPosition(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeGetPosition(window);
    }

    @Override
    public void afterGetPosition(WebDriver.Window window, Point result) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterGetPosition(window, result);
    }

    @Override
    public void beforeSetPosition(WebDriver.Window window, Point position) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeSetPosition(window, position);
    }

    @Override
    public void afterSetPosition(WebDriver.Window window, Point position) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterSetPosition(window, position);
    }

    @Override
    public void beforeMaximize(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeMaximize(window);
    }

    @Override
    public void afterMaximize(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterMaximize(window);
    }

    @Override
    public void beforeFullscreen(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.beforeFullscreen(window);
    }

    @Override
    public void afterFullscreen(WebDriver.Window window) {
        org.openqa.selenium.support.events.WebDriverListener.super.afterFullscreen(window);
    }
}
