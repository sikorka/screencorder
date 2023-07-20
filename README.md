This is a working example of how to record screen with newest [Monte Media Screenrecorder](https://github.com/wrandelshofer/MonteMedia/tree/main/org.monte.demo.screenrecorder). 

Does not run from Maven yet :/ 

Was tested on Mac only. 

Run
---

Run with Java 17 or 20. 

1. In IntelliJ run the main method in class `Screencorder`. 

   NOTE: You need to first select its module as a Java Module in Module Settings > Modules > choose the module > Dependencies > check all modules from `module-info.java`. If something does not work [remove the IntelliJ caches system directory](https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#system-directory).

   The code is run, the movie is recorded. 

2. Or run test `ScreenRecordingTest.java`. 
