package de.aice.worktimer.ui;

import java.time.Duration;

import de.aice.worktimer.Progress;

public interface WorkingDayDisplay {

    void open();

    void showRemainingTime(Duration remainingTime);

    void showProgress(Progress progress);
}
