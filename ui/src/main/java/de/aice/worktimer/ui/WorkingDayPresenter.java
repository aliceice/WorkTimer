package de.aice.worktimer.ui;

import java.time.Duration;

import de.aice.worktimer.AutomaticClock;
import de.aice.worktimer.Clock;
import de.aice.worktimer.WorkingDay;

public final class WorkingDayPresenter {

    private final WorkingDay        workingDay;
    private final Clock             clock;
    private final WorkingDayDisplay display;

    public WorkingDayPresenter(WorkingDay workingDay, WorkingDayDisplay display) {
        this(workingDay, AutomaticClock.withPause(Duration.ofSeconds(1)), display);
    }

    public WorkingDayPresenter(WorkingDay workingDay, Clock clock, WorkingDayDisplay display) {
        this.workingDay = workingDay;
        this.clock = clock;
        this.display = display;
    }

    public void start() {
        this.display.open();
        this.clock.onTick(this::updateDisplay);
        this.clock.start();
    }

    private void updateDisplay() {
        this.display.showRemainingTime(this.workingDay.getRemainingTime());
        this.display.showProgress(this.workingDay.getProgress());
    }

}