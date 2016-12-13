package de.aice.worktimer.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;

import org.junit.Test;

public final class ExceptionsTest {

    @Test
    public void constructorIsPrivate() throws Exception {
        Constructor<?> constructor = Exceptions.class.getDeclaredConstructor();
        assertFalse(constructor.isAccessible());
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    @Test
    public void uncheckedReturnsValueIfNoExceptionIsThrown() throws Exception {
        int expected = 0;
        int result = Exceptions.unchecked(() -> expected);
        assertEquals(expected, result);
    }

    @Test(expected = Exception.class)
    public void uncheckedThrowsExceptionFromSupplier() throws Exception {
        Exceptions.unchecked(() -> {throw new Exception();});
    }
}