package de.aice.worktimer.util;

/**
 * @todo #1 Maybe move to aliceice-lang?
 */
public final class Exceptions {

    private Exceptions() {
    }

    public static <T> T unchecked(UnsafeSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw sneakyThrow(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Exception> RuntimeException sneakyThrow(Exception t) throws T {
        assert t != null;
        throw (T) t;
    }
}
