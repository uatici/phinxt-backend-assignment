package com.phinxt.homeassignment.model.room;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.patch.Patch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoObstacleRoom implements Room {
    private Position position;
    private List<Patch> patches;

    @Override
    public boolean isMoveAllowed(Position newPosition) {
        return newPosition.getX() >= 0 && newPosition.getX() < position.getX() &&
                newPosition.getY() >= 0 && newPosition.getY() < position.getY();
    }

    @Override
    public Patch getPatchAt(Position position) {
        for (Patch patch : patches) {
            if (patch.getPosition().equals(position)) {
                return patch;
            }
        }
        return null;
    }

}
