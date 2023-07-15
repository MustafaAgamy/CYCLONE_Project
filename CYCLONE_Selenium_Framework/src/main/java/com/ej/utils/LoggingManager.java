package com.ej.utils;

import com.ej.drivers.DriverFactoryHelper;
import com.ej.properties.setup.Reporting;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.*;
import org.testng.*;


import java.util.Arrays;

import static com.ej.utils.LoggingStreams.*;


public class LoggingManager {

    //TODO -> Work on Exception Handling.
    //TODO -> Need to make a method that should call the Logging Manager preferences to take some load off the.
    private static Logger logger;
    private static LoggingStreams loggingStreams;
    private static final Reporting reporting = ConfigFactory.create(Reporting.class);

    private static void initializeLogger(){
        Configurator.initialize(null,reporting.log4j2propertiesPath());
        logger = LogManager.getLogger(LoggingManager.class.getSimpleName());
        loggingStreams = new LoggingStreams(logger);
    }
    public static void logCYCLONE(double version){
        String logMessage =
                createStringBuilder().append("\n").append(createDoubleSeparator(149)).append("\n")
                        .append(createSpacing(60)).append("Starting CYCLONE v").append(version).append("\n")
                        .append(createDoubleSeparator(149)).toString();
        logAction(logMessage,null);}
    public static void logWindowSize(Dimension windowSize) {
        String logMessage =
                logConfigurationExecution("Setting Window Size to : ", String.valueOf(windowSize));
        logAction(logMessage, null);}
    public static void logWindowMaximize() {
        String logMessage =
                logConfigurationExecution("Setting Window Size to : Maximize");
        logAction(logMessage,null);}
    public static void logQuitDriver() {
        String logMessage =
                logConfigurationExecution("Successfully Quit The Driver");
        logAction(logMessage,null);
        System.out.println(createSingleSeparator(149));}
    public static void logCloseDriver() {
        String logMessage =
                logConfigurationExecution("Successfully Closed The Driver");
        logAction(logMessage,null);
        System.out.println(createSingleSeparator(149));}
    public static void logSetUp(String targetBrowserName){
        String logMessage =
                logConfigurationExecution("Successfully Setup: ", targetBrowserName.toUpperCase(), " DRIVER");
        logAction(logMessage,null);
        System.out.println(createSingleSeparator(149));}

    public static void logSetUpTypo(String targetBrowserName){
        String logMessage =
                logConfigurationExecution("\" " + DriverFactoryHelper.getTargetBrowserName() + " \" \n" + createSpacing(60)
                                + "LOOKS LIKE YOU HAVE A TYPO WITH YOUR ", "\" " + targetBrowserName.toUpperCase()+  " \" ", " DRIVER.\n"
                                + createSpacing(60) + "NO WORRIES, I'M FIXING IT.");
        logAction(logMessage,null);
        System.out.println(createSingleSeparator(149));}

    public static void logConfigurationMethods(IInvokedMethod method, ITestResult testResult, ITestContext context){
        String logMessage =
                logExecutionMessage("Executing The Configuration Method: ", "Name: " + method.getTestMethod().getMethodName());
        logAction(logMessage,null);
    }
    public static void logTestCaseExecution(ITestResult result){
        String logMessage = logExecutionMessage("STARTING EXECUTION OF THE FOLLOWING:","Method: " + result.getMethod().getMethodName() +
                " || " + "Class: " + result.getMethod().getRealClass().getSimpleName());
        logAction(logMessage,null);}
    public static void logSuiteExecution(ISuite suite) {
        String logMessage = logExecutionMessage("STARTING EXECUTION OF SUITE:","Name: " + suite.getName() +
                " || " + "TCs No. : " + suite.getAllMethods().size());
        logAction(logMessage, null);}
    public static void logTestExecution(ITestContext context){
        String logMessage = logExecutionMessage("STARTING EXECUTION OF TEST:","Name: " + context.getName());
        logAction(logMessage, null);}
    public static void logTestExecutionFinish(ITestContext context){
        String logMessage = logExecutionMessage("FINISHED EXECUTION OF TEST:","Name: " + context.getName());
        logAction(logMessage, null);}
    public static void logClick(By by){
        logAction("Clicking on element -> ",by);
    }
    public static void logSendKeys(By by, String value){
        logAction("Sending > " + value + " < to element -> ",by);
    }
    public static void logGetText(By by){ logAction("Getting Text from element -> ",by);}
    public static void logClear(By by){
        logAction("Clearing Input from element -> ",by);
    }
    public static void logHover(By by){
        logAction("Hovering to element -> ",by);
    }
    public static void logSelectValue(By by, String value){ logAction("Selecting Value > " + value + " < from dropdown -> ",by);}
    public static void logSelectText(By by, String text){ logAction("Selecting Text > " + text + " < from dropdown -> ",by);}
    public static void logSelectIndex(By by, int index){ logAction("Selecting Text > " + index + " < from dropdown -> ",by);}
    public static void logIsDisplayed(By by){
        logAction("Checking if Element -> ",by,  "<- is Displayed.");
    }
    public static void logIsSelected(By by){
        logAction("Checking if Element -> ",by,  "<- is Selected.");
    }
    public static void logIsEnabled(By by){
        logAction("Checking if Element -> ",by,  "<- is Enabled.");
    }
    public static void logSwitchAlert(){ logAction("Switching to the Alert.", null);}
    public static void logTextAlert(){logAction("Getting Text from the Alert.",null);}
    public static void logTypeAlert(String text){
        logAction("Typing > " + text + " < Into the Alert.",null);
    }
    public static void logAcceptAlert(){
        logAction("Accepting the Alert.",null);
    }
    public static void logDismissAlert(){
        logAction("Dismissing the Alert.",null);
    }
    public static void logNavigateUrl(String url){
        logAction("Navigating to Url: " + url,null);
    }
    public static void logNavigateForward() {
        logAction("Navigating Forward",null);
    }
    public static void logNavigateBack() {
        logAction("Navigating Back",null);
    }
    public static void logRefresh(){ logAction("Refreshing The Page",null);}

    public static void logThrowable(Throwable... rootCause) {
        logThrowable((By) null, rootCause);
    }

    public static void logThrowable(String context, Throwable... rootCause){
        if(logger == null){
            initializeLogger();
        }
        logger.log(Level.ERROR, context);
        String exceptionMsg = rootCause[0].getLocalizedMessage();
        String rootCauseMsg = "";
        if (rootCause[0].getCause() != null) {
            rootCauseMsg = "\nRootCauseFailure: " + "\n" + Arrays.toString(Arrays.stream(new Throwable[]{rootCause[0].getCause()}).toArray());
        }
        String message = exceptionMsg + "\n" + rootCauseMsg;
        Assert.fail(message);
    }
    public static void logThrowable(By by, Throwable... rootCause) {

        String action1 = Thread.currentThread().getStackTrace()[2].getMethodName();
        String action2 = Thread.currentThread().getStackTrace()[3].getMethodName();

        logger.log(Level.ERROR,
                by != null ?
                        (action1.matches(".*log.*") ? action2 + " -> " + by : action2 + " -> " + action1 + " -> " + by) :
                        (action1.matches(".*log.*") ? action2 : action2 + " -> " + action1));

        String exceptionMsg = rootCause[0].getLocalizedMessage();
        String rootCauseMsg;
        rootCauseMsg = rootCause[0].getCause() != null ? "\nRootCauseFailure: \n" + rootCause[0].getCause() : "";

        String message = exceptionMsg + "\n" + rootCauseMsg;

        //Add The Screenshot Path to the Log Msg, Note that it's already added to the OnTestFailure Console Msg.
//        message += "\nScreenshot: " + System.getProperty("user.dir") + reporting.screenShotPath();

        Assert.fail(message);
    }

    public static String logConfigurationExecution(String... strings) {
        if(logger == null){
            initializeLogger();
        }
        System.out.println(createSingleSeparator(149));
        StringBuilder sb = createStringBuilder().append("\n").append(createSpacing(60));
        sb.append(strings.length > 0 ? strings[0] : "");
        sb.append(strings.length > 1 ? strings[1] : "");
        sb.append(strings.length > 2 ? strings[2] : "");
        return sb.toString();
    }
    public static String logExecutionMessage(String... strings){
        if(logger == null){
            initializeLogger();
        }
        System.out.println(createSingleSeparator(149));
        return createStringBuilder().append("\n").append(createSpacing(60))
                .append(strings[0]).append("\n").append(createSpacing(60)).append(strings[1]).append("\n")
                .append(createSingleSeparator(149)).toString();}
    private static StringBuilder createStringBuilder() {
        return new StringBuilder();}
    private static void logAction(String message, By by, String... messageCont){
        if(logger == null){
            initializeLogger();
        }
        loggingStreams.logAction(message, by, messageCont);}
}
