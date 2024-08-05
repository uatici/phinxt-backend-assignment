package com.phinxt.homeassignment.service;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.dto.RunRobotRequest.PatchDetail;
import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.exception.InstructionNotFoundException;
import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.movement.EastMovement;
import com.phinxt.homeassignment.model.movement.MovementHelper;
import com.phinxt.homeassignment.model.movement.NorthMovement;
import com.phinxt.homeassignment.model.movement.SouthMovement;
import com.phinxt.homeassignment.model.movement.WestMovement;
import com.phinxt.homeassignment.model.patch.DustPatch;
import com.phinxt.homeassignment.model.patch.Patch;
import com.phinxt.homeassignment.model.patch.PatchFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class RobotServiceTest {

    private RobotService robotService;
    private MovementHelper movementHelper;
    private PatchFactory patchFactory;

    @BeforeEach
    public void setUp() {
        EastMovement eastMovement = new EastMovement();
        WestMovement westMovement = new WestMovement();
        NorthMovement northMovement = new NorthMovement();
        SouthMovement southMovement = new SouthMovement();
        movementHelper = new MovementHelper(List.of(eastMovement, westMovement, northMovement, southMovement));
        ReflectionTestUtils.invokeMethod(movementHelper, "init");
        patchFactory = Mockito.mock(PatchFactory.class);
        robotService = new RobotService(movementHelper, patchFactory);
    }

    @Test
    public void testRunRobot() {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(0, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NESW");
        PatchDetail patchDetail1 = new PatchDetail(List.of(1, 1), null);
        PatchDetail patchDetail2 = new PatchDetail(List.of(2, 2), null);
        request.setPatches(List.of(patchDetail1, patchDetail2));
        Patch patch1 = new DustPatch(new Position(1,1));
        Patch patch2 = new DustPatch(new Position(2,2));
        when(patchFactory.createPatches(anyList())).thenReturn(List.of(patch1, patch2));

        RunRobotResponse response = robotService.runRobot(request);

        assertNotNull(response);
        assertEquals(List.of(0, 0), response.getCoords());
        assertEquals(1, response.getPatches());
    }

    @Test
    public void testRunRobotWithInvalidInstruction() {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(0, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NXSW");
        PatchDetail patchDetail1 = new PatchDetail(List.of(1, 1), null);
        PatchDetail patchDetail2 = new PatchDetail(List.of(2, 2), null);
        request.setPatches(List.of(patchDetail1, patchDetail2));
        Patch patch1 = new DustPatch(new Position(1,1));
        Patch patch2 = new DustPatch(new Position(2,2));
        when(patchFactory.createPatches(anyList())).thenReturn(List.of(patch1, patch2));

        assertThrows(InstructionNotFoundException.class, () -> robotService.runRobot(request));
    }

    @Test
    public void testRunRobotWithNoDirtyPatches() {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(0, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NESW");
        request.setPatches(List.of());

        when(patchFactory.createPatches(anyList())).thenReturn(List.of());

        RunRobotResponse response = robotService.runRobot(request);

        assertNotNull(response);
        assertEquals(List.of(0, 0), response.getCoords());
        assertEquals(0, response.getPatches());
    }
}