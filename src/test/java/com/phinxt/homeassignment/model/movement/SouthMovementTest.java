package com.phinxt.homeassignment.model.movement;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SouthMovementTest {

    private SouthMovement southMovement;
    private Room room;
    private Position position;

    @BeforeEach
    public void setUp() {
        southMovement = new SouthMovement();
        room = Mockito.mock(Room.class);
        position = new Position(2, 2);
    }

    @Test
    public void testMoveAllowed() {
        when(room.isMoveAllowed(new Position(2, 1))).thenReturn(true);

        southMovement.move(position, room);

        assertEquals(2, position.getX());
        assertEquals(1, position.getY());
        verify(room, times(1)).isMoveAllowed(new Position(2, 1));
    }

    @Test
    public void testMoveNotAllowed() {
        when(room.isMoveAllowed(new Position(2, 1))).thenReturn(false);

        southMovement.move(position, room);

        assertEquals(2, position.getX());
        assertEquals(2, position.getY());
        verify(room, times(1)).isMoveAllowed(new Position(2, 1));
    }

    @Test
    public void testMovementType() {
        assertEquals('S', southMovement.movementType());
    }
}