package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.cleaner.VacuumCleaner;
import com.phinxt.homeassignment.model.enums.PatchType;

public class DustPatch extends Patch {

    public DustPatch(Position position) {
        super(position, new VacuumCleaner());
    }

    @Override
    public PatchType getPatchType() {
        return PatchType.DUST;
    }
}