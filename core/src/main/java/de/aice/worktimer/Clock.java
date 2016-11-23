package de.aice.worktimer;

public interface Clock {

    void onTick(Runnable action);

    void start();

    void stop();
}
