package de.aice.worktimer;

import java.util.ArrayList;
import java.util.List;

public final class ManualClock implements Clock {

    private boolean isStarted;
    private List<Runnable> tickActions = new ArrayList<>();

    @Override
    public void onTick(Runnable action) {
        this.tickActions.add(action);
    }

    @Override
    public void start() {
        this.isStarted = true;
    }

    @Override
    public void stop() {
        this.isStarted = false;
    }

    public void tick() {
        if(this.isStarted)
            this.tickActions.forEach(Runnable::run);
    }
}
