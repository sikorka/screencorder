package com.github.sikorka.util.test;

import com.github.sikorka.util.screen.BeBusyForSeconds;
import com.github.sikorka.util.screen.Screencorder;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Log4j
class ScreenRecordingTest {

    @Test
    void recordingTest() throws Exception {
        BeBusyForSeconds busyWork;
        Screencorder recorder = new Screencorder();

        String movieFolder = recorder.getMovieFolder();
        Set<String> initialAviFiles = listFiles(movieFolder, "avi");
        Set<String> newAviFiles;

        LocalDateTime dateTime = LocalDateTime.now();
        String dateStartedString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeStartedString = dateTime.format(DateTimeFormatter.ofPattern("HH.mm.ss"));

        when:
        recorder.start();
        busyWork = new BeBusyForSeconds(7);
        await().until(busyWork::isDone);
        recorder.stop();

        then:
        newAviFiles = listFiles(movieFolder, "avi");
        newAviFiles.removeAll(initialAviFiles);

        log.info("new avi files:\n" + Arrays.toString(new Set[]{newAviFiles}));

        assertThat("an avi file was added", newAviFiles, is(not(emptyIterable())));
        assertThat("only 1 avi file was added", newAviFiles, hasSize(1));

        log.info("date started: " + dateStartedString + ", time started: " + timeStartedString);

        String newAviFile = newAviFiles.iterator().next();
        assertThat("new avi file was created same day when recorder started", newAviFile, containsString(dateStartedString));
        assertThat("new avi file was created when recorder started", newAviFile, containsString(timeStartedString));
    }

    private Set<String> listFiles(String folder, String extension) {
        if (folder == null) {
            return Collections.emptySet();
        }

        return Stream.of(new File(folder).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .filter(name -> name.endsWith(extension))
                .collect(Collectors.toSet());
    }

}
