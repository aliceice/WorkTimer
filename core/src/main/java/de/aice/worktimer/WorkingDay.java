package de.aice.worktimer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.function.Supplier;

public final class WorkingDay {
    
    public static WorkingDay fixed(Duration workingTime, LocalTime currentTime) {
        return new WorkingDay(workingTime, LocalTime.of(8, 0), () -> currentTime);
    }
    
    public static WorkingDay with(Duration workingTime) {
        return WorkingDay.with(workingTime, LocalTime.now());
    }
    
    public static WorkingDay with(Duration workingTime, LocalTime currentTime) {
        return new WorkingDay(workingTime, currentTime, LocalTime::now);
    }
    
    private final Duration            workingTime;
    private final LocalTime           endTime;
    private final Supplier<LocalTime> currentTime;
    
    private WorkingDay(Duration workingTime, LocalTime startTime, Supplier<LocalTime> currentTime) {
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