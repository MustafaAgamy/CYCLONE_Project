# CYCLONE_Project

## Introudction 

CYCLONE is a selenium wrapper project with built-in utilities for simplifying your daily automation runs.
it contains many of the utilities that any Test Automation Engineer might need like testDataReaders to read from different kinds of files, Listeners, and loggers to
help the tester in following the track of their automation runs, different yet simple ways to setup the selenium WebDriver versions and add some custom options to it.

## testDataReaders
1. CSV Reader.
2. JSON Reader.
3. EXCEL Reader.
4. Properties Reader.

## Listners and Loggers
Logging every action being processed to make sure every step is being tracked correctly with a -work-in-progress Exception handling module to
notify the users of the exact location of the exception being thrown and the root cause of it and give some flexibility to the user in case they want to log
or change anything.

## Custome Action Methods
Custome Action Methods for sendkeys, clicks or gettingText..etc 
The methods contains a built-it FluentWaits with some enhancements for scrolling to the element and highlighting the element the driver is interacting with
And a built-in Exception handling in case some exceptions are thrown the method will handle it properly and retry the action before throwing or logging the exception

## Different approaches for setting SeleniumDriver
You can setUp set up Selenium Driver in so many ways and even if you mistyped something CYCLONE will handle it for you and notify you of the typo for future runs.

## Configurations set through Properties Files
You can easily set or change many of your test configurations with a simple change of value in properties Files and CYCLONE will handle the rest.
This way it's easier to handle your tests rather than having to add every piece of code yourself.


 
