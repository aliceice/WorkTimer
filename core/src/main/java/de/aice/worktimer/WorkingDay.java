package de.aice.worktimer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Supplier;

public final class WorkingDay {

    public static WorkingDay fixed(Duration workingTime, LocalTime currentTime) {
        return new WorkingDay(workingTime,
                              LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),
                              () -> LocalDateTime.of(LocalDate.now(), currentTime));
    }

    public static WorkingDay with(Duration workingTime) {
        return WorkingDay.with(workingTime, LocalTime.now());
    }

    public static WorkingDay with(Duration workingTime, LocalTime currentTime) {
        return new WorkingDay(workingTime, LocalDateTime.of(LocalDate.now(), currentTime), LocalDateTime::now);
    }

    private final Duration                workingTime;
    private final LocalDateTime           endTime;
    private final Supplier<LocalDateTime> currentTime;

    private WorkingDay(Duration workingTime, LocalDateTime startTime, Supplier<LocalDateTime> currentTime) {
        this.workingTime = workingTime;
        this.endTime = startTime.plus(this.workingTime);
        this.currentTime = currentTime;
    }

    public Duration getRemainingTime() {
        Duration remainingTime = Duration.between(this.currentTime.get(), this.endTime);
        if (remainingTime.isNegative())
            return Duration.ZERO;
        return remainingTime;
    }

    public Progress getProgress() {
        return new Progress(this.workingTime, getRemainingTime());
    }

}
