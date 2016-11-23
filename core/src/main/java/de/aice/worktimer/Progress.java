package de.aice.worktimer;

import java.time.Duration;
import java.util.Objects;

public final class Progress {

    private final Duration workingTime;
    private final Duration remainingTime;

    public Progress() {
        this(Duration.ZERO, Duration.ZERO);
    }

    public Progress(Duration workingTime, Duration remainingTime) {
        this.workingTime = workingTime;
        this.remainingTime = remainingTime;
    }

    public double onScaleTo(double max) {
        return max - ((max / this.workingTime.getSeconds()) * this.remainingTime.getSeconds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingTime, remainingTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Progress progress = (Progress) o;
        return Objects.equals(workingTime, progress.workingTime)
               && Objects.equals(remainingTime, progress.remainingTime);
    }
}
