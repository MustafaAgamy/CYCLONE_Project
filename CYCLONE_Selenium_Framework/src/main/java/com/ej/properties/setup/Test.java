package com.ej.properties.setup;

import org.aeonbits.owner.Config.Sources;


@Sources({"file:src/main/resources/properties/testConfig.properties"})
public interface Test  extends FrameProperties{


    @Key("versionCYCLONE")
    @DefaultValue("0.1")
    double versionCYCLONE();
    @Key("baseURL")
    @DefaultValue("https://google.com")
    String baseURL();
    @Key("testDataDefaultFolder")
    @DefaultValue("./src/test/resources/")
    String testDataDefaultFolder();

    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("autoMaximizeBrowserWindow")
    @DefaultValue("True")
    boolean autoMaximizeBrowserWindow();

    @Key("headlessExecution")
    @DefaultValue("True")
    boolean headlessExecution();
    @Key("disableBrowserNotification")
    @DefaultValue("True")
    Boolean disableBrowserNotification();

    @Key("disableBrowserAutomationBar")
    @DefaultValue("False")
    Boolean disableBrowserAutomationBar();
    @Key("targetWindowHeight")
    @DefaultValue("1080")
    int targetWindowHeight();
    @Key("targetWindowWidth")
    @DefaultValue("1920")
    int targetWindowWidth();
    @Key("pageLoadTimeout")
    @DefaultValue("10")
    int pageLoadTimeout();
    @Key("scriptTimeout")
    @DefaultValue("10")
    int scriptTimeout();

}
