package com.phinxt.homeassignment.model.patch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatchCounterTest {

    @Test
    public void testInitialValue() {
        PatchCounter patchCounter = new PatchCounter();
        assertEquals(0, patchCounter.getValue());
    }

    @Test
    public void testIncrement() {
        PatchCounter patchCounter = new PatchCounter();
        patchCounter.increment();
        assertEquals(1, patchCounter.getValue());
    }

    @Test
    public void testIncrementMultipleTimes() {
        PatchCounter patchCounter = new PatchCounter();
        patchCounter.increment();
        patchCounter.increment();
        patchCounter.increment();
        assertEquals(3, patchCounter.getValue());
    }

    @Test
    public void testConstructorWithInitialValue() {
        PatchCounter patchCounter = new PatchCounter(5);
        assertEquals(5, patchCounter.getValue());
    }
}