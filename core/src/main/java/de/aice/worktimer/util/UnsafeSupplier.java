package de.aice.worktimer.util;

public interface UnsafeSupplier<T> {

    T get() throws Exception;

}
