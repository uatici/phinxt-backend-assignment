package com.phinxt.homeassignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RunRobotResponse {
    private List<Integer> coords;
    private Integer patches;
    private String errorMessage;

    public RunRobotResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
