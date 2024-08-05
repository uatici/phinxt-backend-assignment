package com.phinxt.homeassignment.model.movement;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EastMovementTest {

    private EastMovement eastMovement;
    private Room room;
    private Position position;

    @BeforeEach
    public void setUp() {
        eastMovement = new EastMovement();
        room = Mockito.mock(Room.class);
        position = new Position(2, 2);
    }

    @Test
    public void testMoveAllowed() {
        when(room.isMoveAllowed(new Position(3, 2))).thenReturn(true);

        eastMovement.move(position, room);

        assertEquals(3, position.getX());
        assertEquals(2, position.getY());
        verify(room, times(1)).isMoveAllowed(new Position(3, 2));
    }

    @Test
    public void testMoveNotAllowed() {
        when(room.isMoveAllowed(new Position(3, 2))).thenReturn(false);

        eastMovement.move(position, room);

        assertEquals(2, position.getX());
        assertEquals(2, position.getY());
        verify(room, times(1)).isMoveAllowed(new Position(3, 2));
    }

    @Test
    public void testMovementType() {
        assertEquals('E', eastMovement.movementType());
    }
}