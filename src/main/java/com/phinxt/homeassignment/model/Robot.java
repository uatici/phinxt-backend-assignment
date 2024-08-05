package com.phinxt.homeassignment.model;

import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.exception.InstructionNotFoundException;
import com.phinxt.homeassignment.model.movement.MovementHelper;
import com.phinxt.homeassignment.model.patch.PatchCounter;
import com.phinxt.homeassignment.model.room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Robot {
    private Room room;
    private MovementHelper movementHelper;
    private Position position;
    private String instructions;
    private boolean continueIfInstructionNotFound;


    public RunRobotResponse cleanRoom() {
        log.info("Cleaning started. Position: [{},{}]", position.getX(), position.getY());
        PatchCounter patchCounter = new PatchCounter();
        cleanPatchIfExist(patchCounter);

        List<Character> instructionList = instructions.chars().mapToObj(c -> (char) c).toList();
        for (Character instruction : instructionList) {
            boolean matchFound = false;
            if (movementHelper.validMovement(instruction)) {
                movementHelper.getMovement(instruction).move(this.position, this.room);
                matchFound = true;
                cleanPatchIfExist(patchCounter);
            }
            if (!matchFound && !continueIfInstructionNotFound) {
                throw new InstructionNotFoundException(String.format("Instruction %s not found in instruction list", instruction));
            }
        }

        List<Integer> currentRobotCoordinates = new ArrayList<>(Arrays.asList(position.getX(), position.getY()));
        return RunRobotResponse.builder()
                .coords(currentRobotCoordinates)
                .patches(patchCounter.getValue())
                .build();
    }

    private void cleanPatchIfExist(PatchCounter patchCounter) {
        var patch = this.room.getPatchAt(position);
        if (patch != null && !patch.isCleaned()) {
            patch.getCleaner().clean();
            patch.setCleaned();
            patchCounter.increment();
        }
    }

}
