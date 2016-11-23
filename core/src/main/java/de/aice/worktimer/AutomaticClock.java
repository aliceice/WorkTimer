package de.aice.worktimer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class AutomaticClock implements Clock {

    public static Clock withPause(Duration pause) {
        return new AutomaticClock(pause);
    }

    private final Duration       pause;
    private final Timer          timer;
    private final List<Runnable> tickActions;

    private AutomaticClock(Duration pause) {
        this.pause = pause;
        this.timer = new Timer(true);
        this.tickActions = new ArrayList<>();
    }

    @Override
    public void onTick(Runnable action) {
        this.tickActions.add(action);
    }

    @Override
    public void start() {
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tickActions.forEach(Runnable::run);
            }
        }, 0L, this.pause.toMillis());
    }

    @Override
    public void stop() {
        this.timer.cancel();
    }
}
