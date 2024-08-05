package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.enums.PatchType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CrumbPatchTest {

    @Test
    public void testCrumbPatchCreation() {
        Position position = new Position(1, 1);
        CrumbPatch crumbPatch = new CrumbPatch(position);

        assertNotNull(crumbPatch);
        assertEquals(position, crumbPatch.getPosition());
        assertFalse(crumbPatch.isCleaned());
        assertEquals(PatchType.CRUMB, crumbPatch.getPatchType());
    }

    @Test
    public void testSetCleaned() {
        Position position = new Position(1, 1);
        CrumbPatch crumbPatch = new CrumbPatch(position);

        crumbPatch.setCleaned();
        assertTrue(crumbPatch.isCleaned());
    }
}
