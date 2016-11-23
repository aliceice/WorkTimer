package de.aice.worktimer;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public final class ManualClockTest {

    private final AtomicInteger counter = new AtomicInteger();
    private final ManualClock   subject = new ManualClock();

    @Test
    public void tickRunsActionIfClockIsStarted() throws Exception {
        this.subject.onTick(this.counter::incrementAndGet);

        this.subject.start();
        this.subject.tick();

        assertEquals(1, this.counter.get());
    }

    @Test
    public void tickDoesNothingIfClockIsNotStarted() throws Exception {
        this.subject.onTick(this.counter::incrementAndGet);
        this.subject.tick();

        assertEquals(0, this.counter.get());

        this.subject.start();
        this.subject.tick();
        this.subject.stop();
        this.subject.tick();

        assertEquals(1, this.counter.get());
    }

}