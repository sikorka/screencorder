package com.github.sikorka.util.screen;

import lombok.extern.log4j.Log4j;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Log4j
public class PretendToBeBusyForFewSeconds {

    private static final int BEING_BUSY_FOR = (int) Duration.ofSeconds(7).toMillis();

    private final Executor executor = Executors.newFixedThreadPool(4);

    private volatile boolean done = false;

    void start() {
        executor.execute(() -> {
            sleep(BEING_BUSY_FOR);
            done = true;
        });
    }

    boolean isItDoneYet() {
        return done;
    }

    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            log.warn("Sleep interrupted!");
            Thread.currentThread().interrupt();
        }
    }
}