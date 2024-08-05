package com.phinxt.homeassignment.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.phinxt.homeassignment.dto.serialization.PatchDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RunRobotRequest {
    @Size(min = 2, max = 2, message = "roomSize must contain exactly 2 integer items")
    private List<Integer> roomSize;
    @Size(min = 2, max = 2, message = "coords must contain exactly 2 integer items")
    @NotNull(message = "coords must not be null")
    private List<@NotNull(message = "coords element must not be null") Integer> coords;
    private List<PatchDetail> patches;
    @NotBlank(message = "instructions must not be blank")
    private String instructions;


    @JsonDeserialize(using = PatchDeserializer.class)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class PatchDetail {
        @Size(min = 2, max = 2, message = "Patch coords must contain exactly 2 integer items")
        private List<Integer> coords;
        private String type;
    }
}