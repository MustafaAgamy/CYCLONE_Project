package com.ej.properties.setup;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/properties/reporting.properties"})
public interface Reporting extends FrameProperties{


    //ExtentReport
    @Key("pathToExtentReport")
    @DefaultValue("src/main/resources/reports/extent-results/index.html")
    String pathToExtentReport();

    @Key("reportName")
    @DefaultValue("CYCLONE Test Results")
    String reportName();

    @Key("documentTitle")
    @DefaultValue("CYCLONE Project")
    String documentTitle();

    @Key("jobTitle")
    @DefaultValue("Job Title")
    String jobTitle();

    @Key("personName")
    @DefaultValue("Person Name")
    String personName();

    @Key("autoOpenExtentReport")
    @DefaultValue("False")
    Boolean autoOpenExtentReport();


    //AllureReport
    @Key("autoOpenAllureReport")
    @DefaultValue("False")
    Boolean autoOpenAllureReport();

    @Key("pathToAllureReport")
    @DefaultValue("allure-results")
    String pathToAllureReport();

    @Key("clearAllureReportFolder")
    @DefaultValue("True")
    boolean clearAllureReportFolder();
    @Key("attachFailureScreenshotToAllure")
    @DefaultValue("True")
    Boolean attachFailureScreenshot();

    @Key("attachLogFileToAllure")
    @DefaultValue("True")
    Boolean attachLogFile();


    //RetryAnalyzer
    @Key("retryCount")
    @DefaultValue("0")
    int retryCount();


    //ScreenShotsPathInTheProject
    @Key("screenShotPath")
    @DefaultValue("/src/main/resources/reports/screenshots/")
    String screenShotPath();
    @Key("autoClearLogFile")
    @DefaultValue("True")
    Boolean autoClearLogFile();
    @Key("log4j2propertiesPath")
    @DefaultValue("${sys:user.dir}/src/main/resources/properties/")
    String log4j2propertiesPath();

}
