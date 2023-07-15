package pages.utils;

import com.ej.factories.elements.ElementActionsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Deprecated

public class ActionsPage {

    @Deprecated
    public static void click(WebDriver driver, By locator){
        ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.CLICK, new Object());
    }

    @Deprecated
    public static void sendKeys(WebDriver driver, By locator, String value){
        ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.SEND_KEYS, value);
    }

    @Deprecated
    public static void clear(WebDriver driver, By locator){
        ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.CLEAR, new Object());
    }

    @Deprecated
    public static String getText(WebDriver driver, By locator){
        return
            ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.GET_TEXT, new Object());
    }


    @Deprecated
    public static String getContent(WebDriver driver, By locator){
        return
            ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.GET_CONTENT, new Object());
    }

    @Deprecated
    public static String getValue(WebDriver driver, By locator){
        return
            ElementActionsFactory.PerformAction(driver, locator,
                ElementActionsFactory.ElementActions.GET_VALUE, new Object());
    }

}
