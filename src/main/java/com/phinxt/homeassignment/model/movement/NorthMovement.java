package com.phinxt.homeassignment.model.movement;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.room.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NorthMovement implements Movement {

    @Override
    public void move(Position position, Room room) {
        int yCoordinate = position.getY();
        if (room.isMoveAllowed(new Position(position.getX(), position.getY() + 1))) {
            position.setY(yCoordinate + 1);
            log.info("Moved to North. Position: [{},{}]", position.getX(), position.getY());
        }
    }

    @Override
    public Character movementType() {
        return 'N';
    }

}
