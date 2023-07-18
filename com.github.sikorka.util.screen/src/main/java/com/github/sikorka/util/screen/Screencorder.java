package com.github.sikorka.util.screen;

import org.awaitility.Awaitility;
import org.monte.media.av.Format;
import org.monte.media.math.Rational;
import org.monte.media.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.monte.media.av.FormatKeys.*;
import static org.monte.media.av.codec.video.VideoFormatKeys.*;

public class Screencorder {
    ScreenRecorder screenRecorder;
    String movieFolder;

    public Screencorder() {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            movieFolder = System.getProperty("user.home") + File.separator + "Videos";
        } else {
            movieFolder = System.getProperty("user.home") + File.separator + "Movies";
        }

        Awaitility.setDefaultTimeout(Duration.ofMinutes(1));
    }

    public void start() throws IOException, AWTException {

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Format fileFormat = new Format(MediaTypeKey, MediaType.FILE,
                MimeTypeKey, MIME_AVI);
        Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO,
                EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                DepthKey, 24,
                FrameRateKey, Rational.valueOf(15),
                QualityKey, 1.0f,
                KeyFrameIntervalKey, (15 * 60));
        Format mouseFormat = new Format(MediaTypeKey, MediaType.VIDEO,
                EncodingKey, ScreenRecorder.ENCODING_BLACK_CURSOR,
                FrameRateKey, Rational.valueOf(30));

        screenRecorder = new ScreenRecorder(gc, null,
                fileFormat, screenFormat, mouseFormat,
                null, new File(movieFolder));

        screenRecorder.start();
    }

    public void stop() throws IOException {
        screenRecorder.stop();
    }

    public static void recordFewSeconds() throws IOException, AWTException {
        Screencorder recorder = new Screencorder();
        BeBusyForSeconds busyWork;

        recorder.start();

        busyWork = new BeBusyForSeconds(27);
        await().until(busyWork::isDone);

        recorder.stop();
    }

    public static void main(String[] args) throws IOException, AWTException {
        recordFewSeconds();
    }
}
