package com.ej.factories;

import com.ej.drivers.DriverFactoryHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScriptsActionsFactory {

    private ScriptsActionsFactory(){}


    protected static void moveToElementScript(WebElement webElement){
            executeJavascript
                    (ScriptActions.MOVE.value, webElement, "");
    }

    protected static void highLightElementScript(WebElement webElement){
            executeJavascript
                    (ScriptActions.HIGHLIGHT.value, webElement, "");
    }

    protected static void removeHighLightScript(WebElement webElement){
            executeJavascript
                    (ScriptActions.REMOVE_HIGHLIGHT.value, webElement, "");
    }

    protected static void clickUsingJavaScript(WebElement webElement){
        executeJavascript
                (ScriptActions.CLICK_ON_ELEMENT.value, webElement, "");
    }

    protected static void sendKeysUsingJavaScript(WebElement webElement, String value){
        executeJavascript(ScriptActions.SEND_KEYS_TO_ELEMENT.value, webElement, value);
    }

    //TODO -> Need to read more about JQuery, Angular and JavaScript Waits implementation.
//    public static void waitForJQuery(){
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) ((WebDriver)DriverFactoryHelper.getDriver().get());
//        javascriptExecutor.executeScript(ScriptActions.WAIT_FOR_JQUERY.value);
//        javascriptExecutor.executeScript(ScriptActions.WAIT_FOR_ANGULAR.value);
//        javascriptExecutor.executeScript(ScriptActions.WAIT_FOR_JAVASCRIPT.value).toString().equals("complete");
//    }

    protected static void waitForAngular(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) ((WebDriver)DriverFactoryHelper.getDriver().get());
        javascriptExecutor.executeScript(ScriptActions.WAIT_FOR_ANGULAR.value);
    }

    protected static void waitForJavaScript(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) ((WebDriver)DriverFactoryHelper.getDriver().get());
        javascriptExecutor.executeScript(ScriptActions.WAIT_FOR_JAVASCRIPT.value).toString().equals("complete");
    }
    private static void executeJavascript(String script, WebElement webElement, String value) {
        JavascriptExecutor executor = (JavascriptExecutor) ((WebDriver)DriverFactoryHelper.getDriver().get());
        try {
            Thread.sleep(50);
            executor.executeScript(script, webElement, value);
            Thread.sleep(50);
        }   catch (InterruptedException e) {
            throw new RuntimeException(e);
            }
    }
    public enum ScriptActions {

        MOVE("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})"),
        HIGHLIGHT("arguments[0].style.border= '3px solid black'"),
        REMOVE_HIGHLIGHT("arguments[0].style.border=''"),
        CLICK_ON_ELEMENT("arguments[0].click();"),
        SEND_KEYS_TO_ELEMENT("arguments[0].value = arguments[1]"),
        WAIT_FOR_JQUERY("return jQuery.active==0"),
        WAIT_FOR_ANGULAR("return angular.element(document).injector().get('$http').pendingRequests.length === 0"),
        WAIT_FOR_JAVASCRIPT("return document.readyState");


        private final String value;

        ScriptActions(String type) {
            this.value = type;
        }

        public String getValue() {
            return this.value;
        }
    }
}
