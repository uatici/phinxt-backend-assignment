package com.phinxt.homeassignment.model.movement;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.room.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WestMovement implements Movement {

    @Override
    public void move(Position position, Room room) {
        int xCoordinate = position.getX();
        if (room.isMoveAllowed(new Position(position.getX() - 1, position.getY()))) {
            position.setX(xCoordinate - 1);
            log.info("Moved to West. Position: [{},{}]", position.getX(), position.getY());
        }
    }

    @Override
    public Character movementType() {
        return 'W';
    }

}
