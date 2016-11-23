package de.aice.worktimer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public final class WorkingDayTest {

    private final LocalTime           currentTime = LocalTime.of(9, 0);
    private final Duration            workingTime = Duration.ofHours(8).plusMinutes(0);
    private final WorkingDay          subject     = WorkingDay.fixed(this.workingTime, this.currentTime);

    @Test
    public void canCalculateRemainingTime() throws Exception {
        assertEquals(Duration.ofHours(7), this.subject.getRemainingTime());
    }

    @Test
    public void canCalculateCurrentProgress() throws Exception {
        assertEquals(12.5, this.subject.getProgress().onScaleTo(100), .001);
    }

    @Test
    public void hasRemainingTimeOfZeroIfInOvertime() throws Exception {
        WorkingDay subject = WorkingDay.fixed(this.workingTime, LocalTime.of(8, 0)
                                                                         .plus(this.workingTime)
                                                                         .plusHours(3));
        assertEquals(Duration.ZERO, subject.getRemainingTime());
    }

    @Test
    public void canCreateInstanceWithCurrentTime() throws Exception {
        WorkingDay subject = WorkingDay.with(Duration.ofMillis(50L));

        assertFalse(subject.getRemainingTime().isNegative());
        assertNotEquals(Duration.ZERO, subject.getRemainingTime());

        Thread.sleep(50L);
        assertEquals(Duration.ZERO, subject.getRemainingTime());
    }
}
