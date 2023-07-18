This is a working example of how to record screen with newest [Monte Media Screenrecorder](https://github.com/wrandelshofer/MonteMedia/tree/main/org.monte.demo.screenrecorder). 

Does not run from Maven yet :/

Run
---

Run with Java 17 or 20. 

1. In IntelliJ run the main method in class `Screencorder`. 

   NOTE: You need to first select its module as a Java Module in Module Settings > Modules > choose the module > Dependencies > check all modules from `module-info.java`. If something does not work [remove the IntelliJ caches system directory](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#system-directory).

   The code is run, the movie is recorded. 

2. Or run `main()` method using maven: 
    
       cd com.github.sikorka.util.screen
       mvn compile exec:java

    This gives compilation error: 

          **************************************************************************************************************************************************************
          * Required filename-based automodules detected: [awaitility-4.2.0.jar, log4j-1.2.17.jar]. Please don't publish this project to a public artifact repository! *
          **************************************************************************************************************************************************************

3. Using tests: 

       mvn clean test

   Same error like above. 