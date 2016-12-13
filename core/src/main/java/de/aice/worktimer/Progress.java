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
        return Objects.hash(this.workingTime, this.remainingTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Progress other = (Progress) o;
        return Objects.equals(this.workingTime, other.workingTime)
               && Objects.equals(this.remainingTime, other.remainingTime);
    }
}
