package com.ej.properties.setup;


import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/properties/timeOuts.properties"})
public interface Timeout extends FrameProperties{

    @Key("elementWaitTimeOut")
    @DefaultValue("10000")
    int elementWaitTimeOut();

    @Key("fluentWaitPollingDelay")
    @DefaultValue("500")
    int fluentWaitPollingDelay();

    @Key("defaultPageLoadTimeOut")
    @DefaultValue("10")
    int defaultPageLoadTimeOut();
}
