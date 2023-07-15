# CYCLONE_Project

## Introudction 

CYCLONE is a selenium wrapper project that contains a built-in utitilies for simplifying your daily automation runs.
it contains many of the utititlies that any Test Automation Engineer might need, like testDataReaders to read from different kinds of file, Listeners and loggers to
help the tester in following the track of their automation runs, different yet simple way to setup the selenium WebDriver versions and add some custome options to it.

## testDataReaders
1. CSV Reader.
2. JSON Reader.
3. EXCEL Reader.
4. Properties Reader.

## Listners and Loggers
Logging every action being processed in order to make sure every step is being tracked correctly with a -work-in-progress Exception handling module to
notify the users with the exact location the exception being thrown and the rootCause of it with and giving some flexibility to the user in case they want to log
or change anything.

## Custome Action Methods
Custome Action Methods for sendkeys, clicks or gettingText..etc 
The methods contains a built-it FluentWaits with some enhancements for scrolling to the element and highlighting the element the driver is interacting with
And a built-in Exception handling in case some exceptions being thrown the method will handle it propery and retry the action before throwing or logging the exception

## Different approaches for setting SeleniumDriver
You can setUp your selenium Driver in so many ways and even if you mistyped something CYCLONE will handle it for you and will notify you with the typo for future runs.



 
