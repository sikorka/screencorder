module com.github.sikorka.util.screen {
    requires java.desktop;
    requires java.prefs;

    requires org.monte.media;
    requires org.monte.media.swing;
    requires org.monte.media.screenrecorder;
    requires awaitility;
    requires lombok;
    requires log4j;

    exports com.github.sikorka.util.screen;
}