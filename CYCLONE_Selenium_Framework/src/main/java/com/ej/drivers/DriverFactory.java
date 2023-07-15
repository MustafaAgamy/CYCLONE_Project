package com.ej.drivers;


import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;



public class DriverFactory {

    public DriverFactory(){}
    

    public static WebDriver getDriver(MutableCapabilities options) {
        if(options != null) {
            DriverFactoryHelper.initializeDriver(options);
        } else {
            DriverFactoryHelper.initializeDriver();
        }
        return (WebDriver)DriverFactoryHelper.getDriver().get();}


    public static WebDriver getDriver(DriverType driverType, MutableCapabilities options) {
        if(options != null) {
            DriverFactoryHelper.initializeDriver(driverType, options);
        } else {
            DriverFactoryHelper.initializeDriver(driverType);
        }
        return (WebDriver)DriverFactoryHelper.getDriver().get();}


    public static WebDriver getDriver(String browser, MutableCapabilities options) {
        if(options != null) {
            DriverFactoryHelper.initializeDriver(browser, options);
        }else {
            DriverFactoryHelper.initializeDriver(browser);
        }
        return (WebDriver) DriverFactoryHelper.getDriver().get();}



    public enum DriverType{

        CHROME(Browser.CHROME.browserName()),
        FIREFOX(Browser.FIREFOX.browserName()),
        EDGE(Browser.EDGE.browserName());
        private final String key;

        private DriverType(String type) {
            this.key = type;
        }

        public String getKey() {
            return this.key;
        }
    }

}
