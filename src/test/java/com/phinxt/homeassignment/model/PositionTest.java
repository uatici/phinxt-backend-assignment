package com.phinxt.homeassignment.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testEquals() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        Position position3 = new Position(2, 3);

        assertEquals(position1, position2, "Positions with same coordinates should be equal");
        assertNotEquals(position1, position3, "Positions with different coordinates should not be equal");
    }

    @Test
    void testHashCode() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);

        assertEquals(position1.hashCode(), position2.hashCode(), "Hash codes should be equal for same coordinates");
    }

    @Test
    void testGettersAndSetters() {
        Position position = new Position();
        position.setX(5);
        position.setY(10);

        assertEquals(5, position.getX(), "Getter for x should return the correct value");
        assertEquals(10, position.getY(), "Getter for y should return the correct value");
    }

    @Test
    void testNoArgsConstructor() {
        Position position = new Position();
        assertNotNull(position, "No-args constructor should create a non-null object");
    }

    @Test
    void testAllArgsConstructor() {
        Position position = new Position(3, 4);
        assertEquals(3, position.getX(), "All-args constructor should set x correctly");
        assertEquals(4, position.getY(), "All-args constructor should set y correctly");
    }
}