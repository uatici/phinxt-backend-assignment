package com.phinxt.homeassignment.model.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MovementHelperTest {

    private MovementHelper movementHelper;
    private EastMovement eastMovement;
    private WestMovement westMovement;
    private NorthMovement northMovement;
    private SouthMovement southMovement;

    @BeforeEach
    public void setUp() {
        eastMovement = new EastMovement();
        westMovement = new WestMovement();
        northMovement = new NorthMovement();
        southMovement = new SouthMovement();
        movementHelper = new MovementHelper(List.of(eastMovement, westMovement, northMovement, southMovement));
        ReflectionTestUtils.invokeMethod(movementHelper, "init");
    }

    @Test
    public void testGetMovement() {
        assertEquals(eastMovement, movementHelper.getMovement('E'));
        assertEquals(westMovement, movementHelper.getMovement('W'));
        assertEquals(northMovement, movementHelper.getMovement('N'));
        assertEquals(southMovement, movementHelper.getMovement('S'));
    }

    @Test
    public void testGetMovementNotExists() {
        assertNull(movementHelper.getMovement('X'));
    }

    @Test
    public void testValidMovement() {
        assertEquals(true, movementHelper.validMovement('E'));
        assertEquals(false, movementHelper.validMovement('X'));
    }
}