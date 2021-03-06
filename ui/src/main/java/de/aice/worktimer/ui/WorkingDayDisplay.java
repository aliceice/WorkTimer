package de.aice.worktimer.ui;

import java.io.IOException;
import java.time.Duration;

import de.aice.worktimer.Progress;

public interface WorkingDayDisplay {

    void open() throws IOException;

    void showRemainingTime(Duration remainingTime);

    void showProgress(Progress progress);
}
