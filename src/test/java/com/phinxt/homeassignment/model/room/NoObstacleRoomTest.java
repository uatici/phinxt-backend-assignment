package com.phinxt.homeassignment.model.room;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.patch.CrumbPatch;
import com.phinxt.homeassignment.model.patch.DustPatch;
import com.phinxt.homeassignment.model.patch.LiquidPatch;
import com.phinxt.homeassignment.model.patch.Patch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoObstacleRoomTest {

    private NoObstacleRoom noObstacleRoom;
    private Position roomSize;
    private List<Patch> patches;

    @BeforeEach
    public void setUp() {
        roomSize = new Position(5, 5);
        patches = Arrays.asList(
            new DustPatch(new Position(1, 1)),
            new CrumbPatch(new Position(2, 2)),
            new LiquidPatch(new Position(3, 3))
        );
        noObstacleRoom = new NoObstacleRoom(roomSize, patches);
    }

    @Test
    public void testIsMoveAllowedWithinBounds() {
        Position newPosition = new Position(4, 4);
        assertTrue(noObstacleRoom.isMoveAllowed(newPosition));
    }

    @Test
    public void testIsMoveAllowedOutOfBounds() {
        Position newPosition = new Position(5, 5);
        assertFalse(noObstacleRoom.isMoveAllowed(newPosition));
    }

    @Test
    public void testGetPatchAtExistingPosition() {
        Position patchPosition = new Position(1, 1);
        Patch patch = noObstacleRoom.getPatchAt(patchPosition);
        assertNotNull(patch);
        assertEquals(patchPosition, patch.getPosition());
    }

    @Test
    public void testGetPatchAtNonExistingPosition() {
        Position patchPosition = new Position(4, 4);
        Patch patch = noObstacleRoom.getPatchAt(patchPosition);
        assertNull(patch);
    }

    @Test
    public void testConstructor() {
        assertEquals(roomSize, noObstacleRoom.getPosition());
        assertEquals(patches, noObstacleRoom.getPatches());
    }
}