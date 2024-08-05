package com.phinxt.homeassignment.model.movement;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.room.Room;

public interface Movement {
    void move(Position position, Room room);

    Character movementType();
}
