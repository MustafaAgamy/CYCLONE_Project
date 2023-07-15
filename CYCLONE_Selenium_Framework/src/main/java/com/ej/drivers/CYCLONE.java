package com.ej.drivers;

import com.ej.factories.AlertActionsFactory;
import com.ej.factories.BrowserActionsFactory;
import com.ej.factories.elements.ElementActionsFactory;
import com.ej.factories.testData.ReadCSV;
import com.ej.factories.testData.ReadEXCEL;
import com.ej.factories.testData.ReadJSON;
import com.ej.factories.testData.ReadPROPERTIES;
import com.ej.utils.WebDriverListener;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class CYCLONE {
        public CYCLONE(){}

    public static class WebDriver{

        public org.openqa.selenium.WebDriver seleniumDriver(){
            return new EventFiringDecorator<>(org.openqa.selenium.WebDriver.class, new WebDriverListener()).decorate(DriverFactoryHelper.getDriver().get());
        }

        public static WebDriver getInstance(){ return new WebDriver();}

        public static WebDriver getInstance(MutableCapabilities options){ return new WebDriver(options);}
        public static WebDriver getInstance(DriverFactory.DriverType driverType){
            return new WebDriver(driverType);}
        public static WebDriver getInstance(DriverFactory.DriverType driverType, MutableCapabilities options) {
            return new WebDriver(driverType, options);}
        public static WebDriver getInstance(String browser) {
            return new WebDriver(browser);
        }

        public static WebDriver getInstance(String browser, MutableCapabilities options) {
            return new WebDriver(browser, options);
        }


        public WebDriver() {
            DriverFactory.getDriver(null);
        }

        public WebDriver(MutableCapabilities options) { DriverFactory.getDriver(options); }

        public WebDriver(DriverFactory.DriverType driverType) {
            DriverFactory.getDriver(driverType, null);
        }

        public WebDriver(DriverFactory.DriverType driverType, MutableCapabilities options) {
            DriverFactory.getDriver(driverType, options);}

        public WebDriver(String browser) {
            DriverFactory.getDriver(browser, null);
        }

        public WebDriver(String browser, MutableCapabilities options) {
            DriverFactory.getDriver(browser, options);
        }



        public void quit() {
            DriverFactoryHelper.quitDriver();
        }

        public ElementActionsFactory elementAction() {
            return ElementActionsFactory.getInstance();
        }
        public BrowserActionsFactory browserAction() {
            return BrowserActionsFactory.getInstance();
        }
        public AlertActionsFactory alertAction(){return  new AlertActionsFactory();}
    }


    public static class DataDriven {
        public DataDriven() {
        }
        public static class JsonClass extends ReadJSON {

            public JsonClass(String jsonFilePath) {super(jsonFilePath);}
            public static ReadJSON getInstance(String jsonFilePath) {return new ReadJSON(jsonFilePath);}
        }

        public static class CSVClass extends ReadCSV {
            public CSVClass(String csvFilePath) {super(csvFilePath);}
            public static ReadCSV getInstance(String csvFilePath) {return new ReadCSV(csvFilePath);}
        }

        public static class PropertiesClass extends ReadPROPERTIES {
            public PropertiesClass(String propertiesFilePath) {super(propertiesFilePath);}
            public static ReadPROPERTIES getInstance(String propertiesFilePath) {return new ReadPROPERTIES(propertiesFilePath);}
        }

        public static class ExcelClass extends ReadEXCEL {

            public ExcelClass(String excelFilePath) {super(excelFilePath);}
            public static ReadEXCEL getInstance(String excelFilePath) {return new ReadEXCEL(excelFilePath);}
        }
    }
}
