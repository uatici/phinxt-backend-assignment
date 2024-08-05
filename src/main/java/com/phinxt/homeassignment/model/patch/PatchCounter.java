package com.phinxt.homeassignment.model.patch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCounter {
    private int value;

    public void increment() {
        value++;
    }

    public Integer getValue() {
        return value;
    }
}