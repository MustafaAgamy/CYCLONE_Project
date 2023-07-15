package com.ej.factories;

import com.ej.drivers.DriverFactoryHelper;
import com.ej.factories.elements.ElementActionsFactory;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.properties.setup.Test;
import com.ej.utils.LoggingManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class BrowserActionsFactory {

    private static final ThreadLocal<BrowserActionsFactory> BROWSER_ACTIONS_INSTANCE = new ThreadLocal<>();
    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();
    private static final Test testSetups = ConfigFactory.create(Test.class);
    private static final String URL_NAME = testSetups.baseURL();
    private BrowserActionsFactory() {
    }

    public static synchronized BrowserActionsFactory getInstance() {
        if (BROWSER_ACTIONS_INSTANCE.get() == null) {
            BROWSER_ACTIONS_INSTANCE.set(new BrowserActionsFactory());
        }
        return (BrowserActionsFactory) BROWSER_ACTIONS_INSTANCE.get();
    }
    public BrowserActionsFactory and() {
        return this;
    }
    public BrowserActionsFactory then() {
        return this;
    }
    public AlertActionsFactory performAlertAction() {
        return new AlertActionsFactory();
    }
    public ElementActionsFactory performElementAction() {
        return ElementActionsFactory.getInstance();
    }
    public AlertActionsFactory alertAction() {
        return new AlertActionsFactory();
    }

    public ElementActionsFactory elementAction() {
        return ElementActionsFactory.getInstance();
    }



    public String getCurrentURL() {
        return
            ((WebDriver) DriverFactoryHelper.getDriver().get()).getCurrentUrl();
    }

    public String getPageSource() {
        return
           ((WebDriver) DriverFactoryHelper.getDriver().get()).getPageSource();
    }

    public String getCurrentWindowTitle() {
        return
            ((WebDriver)DriverFactoryHelper.getDriver().get()).getTitle();
    }

    public String getWindowHandle() {
        return
            ((WebDriver)DriverFactoryHelper.getDriver().get()).getWindowHandle();
    }

    public BrowserActionsFactory navigateToURL(String url) {
        try {
            ((WebDriver)DriverFactoryHelper.getDriver().get()).navigate().to(url);
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        return this;
    }

    public BrowserActionsFactory navigateToURL() {
        try {
            ((WebDriver)DriverFactoryHelper.getDriver().get()).navigate().to(URL_NAME);
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        return this;
    }

    public BrowserActionsFactory navigateBack() {
        LoggingManager.logNavigateBack();
        return this.performNavigationAction(NavigationActions.BACK);
    }

    public BrowserActionsFactory navigateForward() {
        LoggingManager.logNavigateForward();
        return this.performNavigationAction(NavigationActions.FORWARD);
    }

    public BrowserActionsFactory refreshCurrentPage() {
        LoggingManager.logRefresh();
        return this.performNavigationAction(NavigationActions.REFRESH);
    }

    private BrowserActionsFactory performNavigationAction(NavigationActions navigationAction) {
        try {
            switch (navigationAction) {
                case FORWARD -> ((WebDriver) DriverFactoryHelper.getDriver().get()).navigate().forward();
                case BACK -> ((WebDriver) DriverFactoryHelper.getDriver().get()).navigate().back();
                case REFRESH -> ((WebDriver) DriverFactoryHelper.getDriver().get()).navigate().refresh();
            }
        } catch (Throwable throwable){
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }
        return this;
    }


    public enum NavigationActions {
            FORWARD,
            BACK,
            REFRESH;

            private NavigationActions() {
            }
    }
}
