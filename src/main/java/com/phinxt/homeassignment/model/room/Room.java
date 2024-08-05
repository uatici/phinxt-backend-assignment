package com.phinxt.homeassignment.model.room;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.patch.Patch;

public interface Room {
    boolean isMoveAllowed(Position position);

    Patch getPatchAt(Position position);
}
