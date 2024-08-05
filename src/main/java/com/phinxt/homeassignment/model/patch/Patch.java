package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.cleaner.Cleaner;
import com.phinxt.homeassignment.model.enums.PatchType;

public abstract class Patch {
    private Position position;
    private boolean isCleaned;
    private Cleaner cleaner;

    public Patch(Position position, Cleaner cleaner) {
        this.position = position;
        this.cleaner = cleaner;
        this.isCleaned = false;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    public abstract PatchType getPatchType();

    public void setCleaned() {
        this.isCleaned = true;
    }

    public boolean isCleaned() {
        return isCleaned;
    }

    public Position getPosition() {
        return position;
    }

}
