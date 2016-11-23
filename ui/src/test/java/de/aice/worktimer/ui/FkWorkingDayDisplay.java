package de.aice.worktimer.ui;

import java.time.Duration;

import de.aice.worktimer.Progress;

public final class FkWorkingDayDisplay implements WorkingDayDisplay {

    private Boolean  isOpen        = false;
    private Duration remainingTime = Duration.ZERO;
    private Progress progress      = new Progress();

    @Override
    public void open() {
        this.isOpen = true;
    }

    public Boolean isOpen() {
        return this.isOpen;
    }

    @Override
    public void showRemainingTime(Duration remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Duration getRemainingTime() {
        return this.remainingTime;
    }

    @Override
    public void showProgress(Progress progress) {
        this.progress = progress;
    }

    public Progress getProgress() {
        return this.progress;
    }
}