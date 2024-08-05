package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.enums.PatchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatchFactory {

    public List<Patch> createPatches(List<RunRobotRequest.PatchDetail> requestPatchDetails) {
        return requestPatchDetails.stream().map(this::createPatch).collect(Collectors.toList());
    }

    public Patch createPatch(RunRobotRequest.PatchDetail patchDetail) {
        Integer xCoordinate = patchDetail.getCoords().get(0);
        Integer yCoordinate = patchDetail.getCoords().get(1);
        Position position = new Position(xCoordinate, yCoordinate);

        switch (PatchType.getPatchType(patchDetail.getType())) {
            case PatchType.CRUMB:
                return new CrumbPatch(position);
            case PatchType.LIQUID:
                return new LiquidPatch(position);
            case PatchType.DUST:
                return new DustPatch(position);
            default:
                throw new IllegalArgumentException("Unknown patch type: " + patchDetail.getType());
        }
    }
}
