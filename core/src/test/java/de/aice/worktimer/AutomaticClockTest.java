package de.aice.worktimer;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public final class AutomaticClockTest {

    @Test
    public void tickIsBasedOnDuration() throws Exception {
        AtomicInteger counter = new AtomicInteger();

        Clock subject = AutomaticClock.withPause(Duration.ofSeconds(1L));
        subject.onTick(counter::incrementAndGet);

        subject.start();
        Thread.sleep(800L);
        subject.stop();

        assertEquals(1, counter.get());
    }

}
