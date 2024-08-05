package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.enums.PatchType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LiquidPatchTest {

    @Test
    public void testLiquidPatchCreation() {
        Position position = new Position(1, 1);
        LiquidPatch liquidPatch = new LiquidPatch(position);

        assertNotNull(liquidPatch);
        assertEquals(position, liquidPatch.getPosition());
        assertFalse(liquidPatch.isCleaned());
        assertEquals(PatchType.LIQUID, liquidPatch.getPatchType());
    }

    @Test
    public void testSetCleaned() {
        Position position = new Position(1, 1);
        LiquidPatch liquidPatch = new LiquidPatch(position);

        liquidPatch.setCleaned();
        assertTrue(liquidPatch.isCleaned());
    }
}