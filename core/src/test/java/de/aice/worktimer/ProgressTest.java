package de.aice.worktimer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.Duration;
import java.util.Objects;

import org.junit.Test;

public final class ProgressTest {

    @Test
    public void progressOnScaleTo100_with8HoursWorkAnd7Remaining() throws Exception {
        Progress progress = new Progress(Duration.ofHours(8), Duration.ofHours(7));
        assertEquals(12.5, progress.onScaleTo(100), .001);
    }

    @Test
    public void progressEquality() throws Exception {
        Progress progress = new Progress();
        assertEquals(progress, progress);
        assertEquals(progress, new Progress());
        assertNotEquals(progress, new Progress(Duration.ofHours(8), Duration.ZERO));
        assertNotEquals(progress, new Progress(Duration.ZERO, Duration.ofHours(8)));
        assertNotEquals(progress, new Object());
        assertNotEquals(progress, null);
    }

    @Test
    public void progressHasCode() throws Exception {
        assertEquals(Objects.hash(Duration.ZERO, Duration.ZERO), new Progress().hashCode());
    }
}