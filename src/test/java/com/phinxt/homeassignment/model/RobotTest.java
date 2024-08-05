package com.phinxt.homeassignment.model;

import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.exception.InstructionNotFoundException;
import com.phinxt.homeassignment.model.movement.EastMovement;
import com.phinxt.homeassignment.model.movement.MovementHelper;
import com.phinxt.homeassignment.model.movement.NorthMovement;
import com.phinxt.homeassignment.model.movement.SouthMovement;
import com.phinxt.homeassignment.model.movement.WestMovement;
import com.phinxt.homeassignment.model.patch.CrumbPatch;
import com.phinxt.homeassignment.model.patch.DustPatch;
import com.phinxt.homeassignment.model.patch.LiquidPatch;
import com.phinxt.homeassignment.model.room.NoObstacleRoom;
import com.phinxt.homeassignment.model.room.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class RobotTest {

    private Robot robot;
    private Room room;
    private MovementHelper movementHelper;
    private Position startingPosition;
    private String instructions;
    private boolean continueIfInstructionNotFound;

    @BeforeEach
    public void setUp() {
        room = new NoObstacleRoom(
                new Position(5, 5),
                Arrays.asList(
                        new DustPatch(new Position(1, 0)),
                        new DustPatch(new Position(2, 2)),
                        new CrumbPatch(new Position(2, 3)),
                        new LiquidPatch(new Position(3, 3))
                )
        );
        EastMovement eastMovement = new EastMovement();
        WestMovement westMovement = new WestMovement();
        NorthMovement northMovement = new NorthMovement();
        SouthMovement southMovement = new SouthMovement();
        movementHelper = new MovementHelper(List.of(eastMovement, westMovement, northMovement, southMovement));
        ReflectionTestUtils.invokeMethod(movementHelper, "init");
        startingPosition = new Position(0, 0);
        instructions = "NESW";
        continueIfInstructionNotFound = true;
        robot = new Robot(room, movementHelper, startingPosition, instructions, continueIfInstructionNotFound);
    }

    @Test
    public void testCleanRoom() {
        RunRobotResponse response = robot.cleanRoom();
        assertNotNull(response);
        assertEquals(0, response.getCoords().get(0));
        assertEquals(0, response.getCoords().get(1));
        assertEquals(1, response.getPatches());
    }

    @Test
    public void testCleanRoomWithInvalidInstruction() {
        instructions = "NXSW";
        robot = new Robot(room, movementHelper, startingPosition, instructions, false);

        assertThrows(InstructionNotFoundException.class, () -> robot.cleanRoom());
    }
}