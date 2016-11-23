package de.aice.worktimer.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalTime;

import de.aice.worktimer.ManualClock;
import de.aice.worktimer.Progress;
import de.aice.worktimer.WorkingDay;
import org.junit.Test;

public final class WorkingDayPresenterTest {

    private final Duration            workingTime = Duration.ofHours(8);
    private final WorkingDay          workingDay  = WorkingDay.fixed(this.workingTime, LocalTime.of(9, 30));
    private final ManualClock         clock       = new ManualClock();
    private final FkWorkingDayDisplay display     = new FkWorkingDayDisplay();
    private final WorkingDayPresenter subject     = new WorkingDayPresenter(this.workingDay, this.clock, this.display);

    @Test
    public void opensDisplayOnStart() throws Exception {
        this.subject.start();
        assertTrue(this.display.isOpen());
    }

    @Test
    public void setsRemainingTimeIntoDisplayOnTick() throws Exception {
        this.subject.start();
        assertEquals(Duration.ZERO, this.display.getRemainingTime());

        this.clock.tick();
        assertEquals(this.workingDay.getRemainingTime(), this.display.getRemainingTime());
    }

    @Test
    public void setProgressIntoDisplayOnTick() throws Exception {
        this.subject.start();
        assertEquals(new Progress(), this.display.getProgress());

        this.clock.tick();
        assertEquals(this.workingDay.getProgress(), this.display.getProgress());
    }

    @Test
    public void canCreateInstanceWithAutomaticClockWithPauseOf1Second() throws Exception {
        FkWorkingDayDisplay display = new FkWorkingDayDisplay();
        WorkingDayPresenter subject = new WorkingDayPresenter(WorkingDay.with(Duration.ofHours(8)), display);

        subject.start();
        Thread.sleep(1000);
        assertNotEquals(Duration.ZERO, display.getRemainingTime());
    }

}
