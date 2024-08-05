package com.phinxt.homeassignment.service;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.Robot;
import com.phinxt.homeassignment.model.movement.MovementHelper;
import com.phinxt.homeassignment.model.patch.PatchFactory;
import com.phinxt.homeassignment.model.room.NoObstacleRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RobotService {

    @Value("${application.validation.continue-if-instruction-not-found}")
    private boolean continueIfInstructionNotFound;
    private final MovementHelper movementHelper;
    private final PatchFactory patchFactory;

    public RunRobotResponse runRobot(RunRobotRequest runRobotRequest) {
        var patches = patchFactory.createPatches(runRobotRequest.getPatches());

        Position roomPosition = new Position(runRobotRequest.getRoomSize().get(0), runRobotRequest.getRoomSize().get(1));
        var room = new NoObstacleRoom(roomPosition, patches);

        Position robotStartingPosition = new Position(runRobotRequest.getCoords().get(0), runRobotRequest.getCoords().get(1));
        var robot = new Robot(room, movementHelper, robotStartingPosition, runRobotRequest.getInstructions(), continueIfInstructionNotFound);

        return robot.cleanRoom();
    }
}
