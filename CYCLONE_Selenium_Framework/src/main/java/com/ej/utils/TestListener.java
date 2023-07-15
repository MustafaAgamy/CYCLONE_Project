package com.ej.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ej.drivers.DriverFactoryHelper;
import com.ej.properties.setup.ExceptionHandling;
import com.ej.properties.setup.Reporting;
import com.ej.properties.setup.Test;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener, WebDriverListener {

    //TODO -> To be removed once the ExceptionModule is done
    private static final ExceptionHandling exceptionHandling = ConfigFactory.create(ExceptionHandling.class);
    private static final boolean USE_EXCEPTION_LOGGING = exceptionHandling.logExceptionsModule();
    private static final ExtentReportClass extentReportClass = new ExtentReportClass();
    private static final ExtentReports extentReports = extentReportClass.setReportAttributes();
    private static final Reporting reporting = ConfigFactory.create(Reporting.class);
    private static final Test testSetups = ConfigFactory.create(Test.class);
    private static final boolean CLEAR_ALLURE_REPORT_FOLDER = reporting.clearAllureReportFolder();
    private static final boolean ATTACH_SCREENSHOT_TO_ALLURE = reporting.attachFailureScreenshot();
    private static final boolean ATTACH_LOGFILE_TO_ALLURE = reporting.attachLogFile();
    private static final boolean AUTO_CLEAR_LOGFILE = reporting.autoClearLogFile();
    private static final boolean AUTO_OPEN_ALLURE_REPORT = reporting.autoOpenAllureReport();
    private static final boolean AUTO_OPEN_EXTENT_REPORT = reporting.autoOpenExtentReport();
    private static final String PATH_TO_LOG_FILE = System.getProperty("user.dir") + "/src/main/resources/reports/logs/Logs.log";
    private static final String PATH_TO_PROPERTIES_FILES = System.getProperty("user.dir") + "/src/main/resources/properties/";
    private static ExtentTest test;


    //This Parameter will be used in a future release
    private static final String screenShotName = "";


    //OnTestCaseStart
    @Override
    public void onTestStart(ITestResult result) {
        //ExtentReport
        test = extentReports.createTest("TestCase " + result.getMethod().getMethodName());
        test.info(result.getMethod().getMethodName() + " has started.");

        LoggingManager.logTestCaseExecution(result);
    }


    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        //ExtentReport
        test.log(Status.PASS, result.getMethod().getMethodName() + " has passed.");

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        //ExtentReport
        test.log(Status.FAIL, result.getMethod().getMethodName() + " has failed! Failure reason:" + result.getThrowable());
        test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),(WebDriver)DriverFactoryHelper.getDriver().get()),
                result.getMethod().getMethodName());
        test.addScreenCaptureFromBase64String(screenShotBase64(),result.getMethod().getMethodName());

        //Path to Screenshot in the Project
        String screenShotMsg = "\033[31mScreenshot saved to: " + "\n" + System.getProperty("user.dir") + reporting.screenShotPath()
                                + result.getMethod().getMethodName() + ".png\033[0m";
        System.out.println("\n" + screenShotMsg + "\n");
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        //ExtentReport
        test.log(Status.SKIP, result.getMethod().getMethodName() + " has been skipped!");
    }

    //OnTestStart
    @Override
    public synchronized void onStart(ITestContext context) {
        LoggingManager.logTestExecution(context);
    }

    //OnTestFinish
    @Override
    public synchronized void onFinish(ITestContext context) {
        LoggingManager.logTestExecutionFinish(context);
    }

    //BeforeEveryMethod
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.beforeInvocation(method, testResult);
    }

    //AfterEveryMethod
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //TODO -> Need to find a better approach.
//        //Attach the screenshot and LogFile to Allure - Sometimes the report is actually generated before the attachment is finalized.
//        //That's why it's being invoked here to make sure the screenshot is actually generated and attached successfully.
        if (testResult.getStatus() == 2) {
            if (ATTACH_SCREENSHOT_TO_ALLURE) {

                Allure.addAttachment(testResult.getMethod().getMethodName() + " - Screenshot.png",
                        new ByteArrayInputStream(((TakesScreenshot) (WebDriver) DriverFactoryHelper.getDriver().get())
                                .getScreenshotAs(OutputType.BYTES)));
            }
            if (ATTACH_LOGFILE_TO_ALLURE) {
                try {
                    File logFile = new File(PATH_TO_LOG_FILE);
                    byte[] logBytes = Files.readAllBytes(logFile.toPath());
                    Allure.addAttachment(testResult.getMethod().getMethodName() + " - Log File", "text/plain", new String(logBytes));
                } catch (IOException e) {
                    if(USE_EXCEPTION_LOGGING){
                        LoggingManager.logThrowable(e);
                    }else {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    //BeforeEveryConfigurationMethod
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//        if(testResult.getMethod().isAfterMethodConfiguration()){
//            LoggingManager.writeToLogFile(,"-");
//        }

        if(testResult.getMethod().isBeforeMethodConfiguration() || testResult.getMethod().isAfterMethodConfiguration()) {
            LoggingManager.logConfigurationMethods(method, testResult, context);
        }

    }

    //AfterEveryConfigurationMethod
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        IInvokedMethodListener.super.afterInvocation(method, testResult, context);
    }


    //OnSuiteStart
    @Override
    public void onStart(ISuite suite) {
        //AutoClearAllureFolder
        if (CLEAR_ALLURE_REPORT_FOLDER) {
            File allureDirectory = new File(reporting.pathToAllureReport());
            File[] files = allureDirectory.listFiles();
            for (File file : files != null ? files : new File[0]) {
                if (file.isFile() && file.delete()) {
                   //DoNothing
                } else {
                    LoggingManager.logThrowable(new Throwable("Couldn't Delete File because it doesn't exist"));
                }
            }
        }
        //AutoClearLogFileIfTheSizeIsTooLarge
        if(AUTO_CLEAR_LOGFILE) {
            try {
                File logFile = new File(PATH_TO_LOG_FILE);
                if (logFile.length() > 0) {
                    FileWriter writer = new FileWriter(PATH_TO_LOG_FILE, false);
                    writer.write("");
                    writer.close();
                }
            } catch (IOException e) {
                LoggingManager.logThrowable(e);
            }
            LoggingManager.logCYCLONE(testSetups.versionCYCLONE());
            LoggingManager.logSuiteExecution(suite);
        }
    }

    //OnSuiteFinish
    @Override
    public void onFinish(ISuite suite) {
        extentReports.flush();

        //AutoOpen Extent and Allure Reports.
        try {
            String projectDirectory = System.getProperty("user.dir").replaceAll("\\\\", "/");
            String reportsConfigFile = projectDirectory + "/src/main/resources/properties/reporting.properties";

            if (AUTO_OPEN_EXTENT_REPORT) {
                Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + reporting.pathToExtentReport()).toURI());
            }
            else {
                System.out.println("AutoOpen Extent Report is set to \" False \" at \n " + "file:///" + reportsConfigFile);

            }
            if (AUTO_OPEN_ALLURE_REPORT) {
                String command = "cmd /c start \"\" cmd.exe /K \"cd /d" + System.getProperty("user.dir") + "&& allure serve allure-results\"";
                Runtime.getRuntime().exec(command);
            }
            else {
                System.out.println("AutoOpen Allure Report is set to \" False \" at \n " + "file:///" + reportsConfigFile);
            }
            String logFilePath = projectDirectory + "/src/main/resources/reports/logs/Logs.log";
            String logMessage = "file:///" + logFilePath;
            System.out.println("Logs saved to LogFile: " + "\n" +logMessage);

        } catch (IOException e) {
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(e);
            }else {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getScreenshotPath(String testcaseName, WebDriver driver) {
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + reporting.screenShotPath() + testcaseName + ".png";
        try {
            FileUtils.copyFile(source, new File(destinationFile));
        } catch (Exception e) {
            if(USE_EXCEPTION_LOGGING){
                LoggingManager.logThrowable(e);
            }else {
                throw new RuntimeException(e);
            }
        }
        return destinationFile;
    }

    private static String screenShotBase64() {
        return ((TakesScreenshot)(WebDriver) DriverFactoryHelper.getDriver().get()).getScreenshotAs(OutputType.BASE64);
    }

    private static String elementName(WebElement element){
        var accessibleName = element.getAccessibleName();
        if (accessibleName.equals("")){
            return "element";
        }
        else{
            return accessibleName;
        }
    }
}
