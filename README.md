Trying to record screen with newest Monte Media Screenrecorder. 

Run
---

Run with java 17 or 20. 

    export JAVA_HOME=`/usr/libexec/java_home -v 20.0`

1. In IntelliJ run the main method in class `Screencorder`. 

    You need to first select its module as a Java Module in Module Settings > Modules > choose the module > Dependencies > check all modules from `module-info.java`. 

    If something does not work remove the IntelliJ caches system directory https://www.jetbrains.com/help/idea/directories-used-by-the-ide-to-store-settings-caches-plugins-and-logs.html#system-directory. 

    The code is run but unfortunately the movie is size 0:00.

2. Or run Main method using maven: 
    
       cd cd com.github.sikorka.util.screen
       mvn compile exec:java

    This gives the old error: 

       java.io.IOException: Error no writer found for file format: Format{mimeType:video/avi,mediaType:FILE}.

3. Using tests: 

       mvn clean test

   This gives error: 

       java.lang.reflect.InaccessibleObjectException: Unable to make com.github.sikorka.util.screen.ScreenRecordingTest() accessible
