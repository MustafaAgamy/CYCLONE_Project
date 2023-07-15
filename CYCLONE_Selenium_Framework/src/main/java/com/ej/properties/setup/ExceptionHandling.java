package com.ej.properties.setup;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/resources/properties/exceptionHandling.properties"})
public interface ExceptionHandling extends FrameProperties{
    @Key("logExceptionsModule")
    @DefaultValue("false")
    boolean logExceptionsModule();
}
