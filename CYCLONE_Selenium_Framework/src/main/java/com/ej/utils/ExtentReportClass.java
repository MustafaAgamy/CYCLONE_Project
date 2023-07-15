package com.ej.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ej.properties.setup.Reporting;
import org.aeonbits.owner.ConfigFactory;

public class ExtentReportClass {

    private static ExtentReports extentReports;
    Reporting extentReport = ConfigFactory.create(Reporting.class);
    private final String EXTENT_REPORT_PATH = extentReport.pathToExtentReport();
    private final String REPORT_NAME = extentReport.reportName();
    private final String DOCUMENT_TITLE = extentReport.documentTitle();
    private final String JOB_TITLE = extentReport.jobTitle();
    private final String PERSON_NAME = extentReport.personName();
    public  ExtentReports setReportAttributes(){

        ExtentSparkReporter reporter = new ExtentSparkReporter(EXTENT_REPORT_PATH);
        reporter.config().setDocumentTitle(DOCUMENT_TITLE);
        reporter.config().setReportName(REPORT_NAME);

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo(JOB_TITLE,PERSON_NAME);

        return extentReports;
    }
}
