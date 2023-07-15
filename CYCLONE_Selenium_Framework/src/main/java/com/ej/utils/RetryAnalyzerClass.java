package com.ej.utils;

import com.ej.properties.setup.Reporting;
import org.aeonbits.owner.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerClass implements IRetryAnalyzer {

    Reporting retryAnalyzer = ConfigFactory.create(Reporting.class);

    private int RUN_COUNTER = 0;
    private  final int MAX_RETRY_COUNTER = retryAnalyzer.retryCount();


    @Override
    public boolean retry(ITestResult result) {
        if(RUN_COUNTER < MAX_RETRY_COUNTER)
        {
            RUN_COUNTER++;
            return true;
        }
        return false;
    }
}
