package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.enums.PatchType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DustPatchTest {

    @Test
    public void testDustPatchCreation() {
        Position position = new Position(1, 1);
        DustPatch dustPatch = new DustPatch(position);

        assertNotNull(dustPatch);
        assertEquals(position, dustPatch.getPosition());
        assertFalse(dustPatch.isCleaned());
        assertEquals(PatchType.DUST, dustPatch.getPatchType());
    }

    @Test
    public void testSetCleaned() {
        Position position = new Position(1, 1);
        DustPatch dustPatch = new DustPatch(position);

        dustPatch.setCleaned();
        assertTrue(dustPatch.isCleaned());
    }
}