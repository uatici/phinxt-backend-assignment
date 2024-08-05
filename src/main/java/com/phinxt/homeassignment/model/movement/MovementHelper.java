package com.phinxt.homeassignment.model.movement;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovementHelper {
    private final List<Movement> movements;
    private Map<Character, Movement> MOVEMENT_MAP = new HashMap<>();

    @PostConstruct
    private void init() {
        for (Movement movement : movements) {
            MOVEMENT_MAP.put(movement.movementType(), movement);
        }
    }

    public Movement getMovement(Character movement) {
        return MOVEMENT_MAP.get(movement);
    }

    public boolean validMovement(Character movement) {
        return MOVEMENT_MAP.containsKey(movement);
    }
}
