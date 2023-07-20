module com.github.sikorka.util.test {
    requires com.github.sikorka.util.screen;
    requires org.junit.jupiter.api;
    requires awaitility;
    requires org.hamcrest;
    requires log4j;
    requires lombok;

    opens com.github.sikorka.util.test to org.junit.platform.commons;
}