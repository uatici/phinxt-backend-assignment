package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.model.Position;
import com.phinxt.homeassignment.model.cleaner.MopCleaner;
import com.phinxt.homeassignment.model.enums.PatchType;

public class LiquidPatch extends Patch {
    public LiquidPatch(Position position) {
        super(position, new MopCleaner());
    }

    @Override
    public PatchType getPatchType() {
        return PatchType.LIQUID;
    }
}
