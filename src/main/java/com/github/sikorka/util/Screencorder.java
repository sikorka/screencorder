package com.github.sikorka.util;

import org.monte.media.av.Format;
import org.monte.media.math.Rational;
import org.monte.media.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.av.FormatKeys.*;
import static org.monte.media.av.codec.video.VideoFormatKeys.*;

public class Screencorder {

    private Screencorder() {}

    public static ScreenRecorder startRecording(String movieFolder) throws IOException, AWTException {

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

        ScreenRecorder screenRecorder = new ScreenRecorder(gc, null,
                fileFormat, screenFormat, mouseFormat,
                null, new File(movieFolder));

        screenRecorder.start();

        return screenRecorder;
    }

    /** Trying another way of recording. Still same error. */
    public static ScreenRecorder startRecording1(String movieFolder) throws IOException, AWTException {
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        ScreenRecorder screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
                // the file format:
                new Format(MediaTypeKey, MediaType.FILE,
                        MimeTypeKey, MIME_AVI),
                //
                // the output format for screen capture:
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        WidthKey, Double.valueOf(gc.getBounds().getWidth()).intValue(), //outputDimension.width,
                        HeightKey, Double.valueOf(gc.getBounds().getHeight()).intValue(), //outputDimension.height,
                        DepthKey, 24, //bitDepth,
                        FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, (15 * 60) // one keyframe per minute is enough
                ),
                //
                // the output format for mouse capture:
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey, ScreenRecorder.ENCODING_BLACK_CURSOR,
                        FrameRateKey, Rational.valueOf(30)),
                //
                // the output format for audio capture:
                null,
                //
                // the storage location of the movie
                new File(movieFolder));

        screenRecorder.start();

        return screenRecorder;
    }

    public static void stopRecording(ScreenRecorder screenRecorder) throws IOException {
        screenRecorder.stop();
    }
}
