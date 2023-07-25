module com.github.sikorka.util.screen.test {
    requires com.github.sikorka.util.screen;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter;
    requires hamcrest.all;

    opens com.github.sikorka.util.screen.test to org.junit.platform.commons;
}