package com.github.sikorka.util.screen.test;

import com.github.sikorka.util.screen.Screencorder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ScreenRecordingTest {

    @Test
    void recordingTest() throws Exception {
        Screencorder recorder = new Screencorder();

        String movieFolder = recorder.getMovieFolder();
        Set<String> initialAviFiles = listFiles(movieFolder, "avi");
        Set<String> newAviFiles;

        LocalDateTime dateTime = LocalDateTime.now();
        String dateStartedString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeStartedString = dateTime.format(DateTimeFormatter.ofPattern("HH.mm."));

        when:
        recorder.start();
        Thread.sleep(Duration.ofSeconds(7).toMillis());
        recorder.stop();

        then:
        newAviFiles = listFiles(movieFolder, "avi");
        newAviFiles.removeAll(initialAviFiles);

//        log.info
        System.out.println
                ("new avi files:\n" + Arrays.toString(new Set[]{newAviFiles}));

        assertThat("an avi file was added", newAviFiles, is(not(emptyIterable())));
        assertThat("only 1 avi file was added", newAviFiles, hasSize(1));

//        log.info
        System.out.println
                ("date started: " + dateStartedString + ", time started: " + timeStartedString);

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
