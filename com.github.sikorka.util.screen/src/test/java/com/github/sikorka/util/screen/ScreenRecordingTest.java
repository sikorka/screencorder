package com.github.sikorka.util.screen;

import org.junit.jupiter.api.Test;
import org.monte.media.screenrecorder.ScreenRecorder;

import java.io.File;
import java.time.Duration;

import static org.awaitility.Awaitility.await;

class ScreenRecordingTest {

    @Test
    void recordingTest() throws Exception {
        Screencorder.record5seconds();
    }
}
