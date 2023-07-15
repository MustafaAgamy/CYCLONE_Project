package com.ej.utils;

import com.ej.drivers.DriverFactoryHelper;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.utils.LoggingManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import static com.ej.utils.LoggingManager.logThrowable;


public class LoggingStreams {

    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();
    private Logger logger;

    public LoggingStreams(Logger logger){
        this.logger = logger;
    }

    public void logAction(String message, By by, String... messageCont) {
        String msgCont = messageCont.length > 0 ? " " + messageCont[0] : "";
        StringBuilder logMessage = new StringBuilder();
        try {
            logMessage.append(message)
                    .append(by != null && !locatorAccessibleName(by).isBlank() ?
                    locatorAccessibleName(by) + " >> " + locator(by) : by != null ? locator(by) : "").append(msgCont);

            if (logMessage.length() > 0) {
                logger.log(Level.INFO,logMessage.toString());
            }
        } catch (Exception e2) {
            // Do Nothing
        } catch (Throwable throwable) {
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(throwable);
            }else {
                throw throwable;
            }
        }

    }

    private String locatorAccessibleName(By by){
        WebElement webElement = ((WebDriver) DriverFactoryHelper.getDriver().get()).findElement(by);
        return webElement.getAccessibleName();
    }

    private String locator(By by) {
        if (by instanceof RelativeLocator.RelativeBy relativeBy) {
            return "RelativeBy : " + relativeBy.getRemoteParameters().value().toString();
        } else {
            return by.toString();
        }
    }

    private static String repeatString(String string, int repetition ){
        return StringUtils.repeat(string, repetition);
    }

    public static String createDoubleSeparator(int repetition){
        return repeatString("=",repetition);
    }

    public static String createSingleSeparator(int repetition){
        return repeatString("-",repetition);
    }

    public static String createSpacing(int repetition){
        return repeatString(" ",repetition);
    }

}
