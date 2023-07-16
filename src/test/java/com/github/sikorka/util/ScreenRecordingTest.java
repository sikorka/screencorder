package com.github.sikorka.util;

import org.junit.jupiter.api.Test;
import org.monte.media.screenrecorder.ScreenRecorder;

import java.io.File;
import java.time.Duration;

import static org.awaitility.Awaitility.await;

class ScreenRecordingTest {

    @Test
    void recordingTest() throws Exception {
        ScreenRecorder recorder = Screencorder.startRecording1(
                System.getProperty("user.home") + File.separator + "Movies");

        await().atMost(Duration.ofSeconds(2));

        Screencorder.stopRecording(recorder);
    }
}
