package com.github.sikorka.util.screen;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j
public class BeBusyForSeconds {

    private final int beBusyForInMillis;

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    private volatile boolean done = false;

    public BeBusyForSeconds(int seconds) {
        beBusyForInMillis = seconds * 1000;

        start();
    }

    private void start() {
        executor.execute(() -> {
            try {
                Thread.sleep(beBusyForInMillis);
            } catch (InterruptedException e) {
                log.warn("Busy-ness interrupted!");
                Thread.currentThread().interrupt();
            }

            done = true;
        });
    }

    boolean isDone() {
        if (done) {
            executor.shutdownNow();
        }

        return done;
    }
}
