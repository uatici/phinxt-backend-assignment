package com.phinxt.homeassignment.controller;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.dto.RunRobotRequest.PatchDetail;
import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.exception.ValidationErrorException;
import com.phinxt.homeassignment.model.movement.Movement;
import com.phinxt.homeassignment.service.RobotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/robot")
@RequiredArgsConstructor
@Slf4j
public class RobotController {

    @Value("${application.validation.check-patches-in-room-before-run}")
    private boolean checkPatchesInRoomBeforeRun;
    @Value("${application.validation.check-instructions-is-valid-before-run}")
    private boolean checkInstructionsIsValidBeforeRun;
    private final RobotService robotService;
    private final List<Movement> movements;


    @PostMapping("/run")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RunRobotResponse> runRobot(@Valid @RequestBody RunRobotRequest runRobotRequest) {
        log.info("/api/v1/robot/run is calling. Request: {}", runRobotRequest);
        validate(runRobotRequest);
        var runRobotResponse = robotService.runRobot(runRobotRequest);
        log.info("/api/v1/robot/run called. Response: {}", runRobotResponse);
        return new ResponseEntity<>(runRobotResponse, HttpStatus.OK);
    }

    private void validate(RunRobotRequest runRobotRequest) {
        validateIsRoomHasPositiveSize(runRobotRequest);
        validateIsRobotInRoom(runRobotRequest);
        validateIsPatchesInRoom(runRobotRequest);
        validateInstructions(runRobotRequest);
    }

    private void validateIsRoomHasPositiveSize(RunRobotRequest runRobotRequest) {
        if (runRobotRequest.getRoomSize().get(0) <= 0 || runRobotRequest.getRoomSize().get(1) <= 0) {
            throw new ValidationErrorException("Room size cannot be negative");
        }
    }

    private void validateIsRobotInRoom(RunRobotRequest runRobotRequest) {
        if (runRobotRequest.getCoords().get(0) < 0 || runRobotRequest.getCoords().get(1) < 0
                || runRobotRequest.getCoords().get(0) >= runRobotRequest.getRoomSize().get(0)
                || runRobotRequest.getCoords().get(1) >= runRobotRequest.getRoomSize().get(1)) {
            throw new ValidationErrorException("Robot is not in the room");
        }
    }

    private void validateIsPatchesInRoom(RunRobotRequest runRobotRequest) {
        if (checkPatchesInRoomBeforeRun) {
            for (PatchDetail patchDetail : runRobotRequest.getPatches()) {
                if (patchDetail.getCoords().get(0) < 0 || patchDetail.getCoords().get(1) < 0
                        || patchDetail.getCoords().get(0) >= runRobotRequest.getRoomSize().get(0)
                        || patchDetail.getCoords().get(1) >= runRobotRequest.getRoomSize().get(1)) {
                    throw new ValidationErrorException(
                            String.format("Patch with coordinates %d, %d is not in the room", patchDetail.getCoords().get(0),
                                    patchDetail.getCoords().get(1)));
                }
            }
        }
    }

    private void validateInstructions(RunRobotRequest runRobotRequest) {
        if (checkInstructionsIsValidBeforeRun && runRobotRequest.getInstructions() != null) {
            for (char ch : runRobotRequest.getInstructions().toCharArray()) {
                boolean matchFound = false;
                for (var movement : this.movements) {
                    if (movement.movementType().equals(ch)) {
                        matchFound = true;
                        break;
                    }
                }
                if (!matchFound) {
                    throw new ValidationErrorException(String.format("Instruction %s is not found", ch));
                }
            }
        }
    }
}