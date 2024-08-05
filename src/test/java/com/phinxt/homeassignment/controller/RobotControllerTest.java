package com.phinxt.homeassignment.controller;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.dto.RunRobotRequest.PatchDetail;
import com.phinxt.homeassignment.dto.RunRobotResponse;
import com.phinxt.homeassignment.exception.ValidationErrorException;
import com.phinxt.homeassignment.service.RobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RobotController.class)
public class RobotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RobotService robotService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRunRobot() throws Exception {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(0, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NESW");
        PatchDetail patchDetail1 = new PatchDetail(List.of(1, 1), null);
        PatchDetail patchDetail2 = new PatchDetail(List.of(2, 2), null);
        request.setPatches(List.of(patchDetail1, patchDetail2));

        RunRobotResponse response = new RunRobotResponse();
        response.setCoords(List.of(0, 0));
        response.setPatches(1);

        when(robotService.runRobot(any(RunRobotRequest.class))).thenReturn(response);

        String requestJson = "{\"coords\":[0,0],\"roomSize\":[5,5],\"instructions\":\"NESW\",\"patches\":[[1,1],[2,2]]}";

        mockMvc.perform(post("/api/v1/robot/run")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coords").isArray())
                .andExpect(jsonPath("$.coords[0]").value(0))
                .andExpect(jsonPath("$.coords[1]").value(0))
                .andExpect(jsonPath("$.patches").value(1));
    }

    @Test
    public void testRunRobotWithInvalidCoords() throws Exception {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(-1, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NESW");
        PatchDetail patchDetail1 = new PatchDetail(List.of(1, 1), null);
        PatchDetail patchDetail2 = new PatchDetail(List.of(2, 2), null);
        request.setPatches(List.of(patchDetail1, patchDetail2));

        when(robotService.runRobot(any(RunRobotRequest.class))).thenThrow(new ValidationErrorException("Robot is not in the room"));

        String requestJson = "{\"coords\":[-1,0],\"roomSize\":[5,5],\"instructions\":\"NESW\",\"patches\":[[1,1],[2,2]]}";

        mockMvc.perform(post("/api/v1/robot/run")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("Robot is not in the room"));
    }

    @Test
    public void testRunRobotWithInvalidInstruction() throws Exception {
        RunRobotRequest request = new RunRobotRequest();
        request.setCoords(List.of(0, 0));
        request.setRoomSize(List.of(5, 5));
        request.setInstructions("NXSW");
        PatchDetail patchDetail1 = new PatchDetail(List.of(1, 1), null);
        PatchDetail patchDetail2 = new PatchDetail(List.of(2, 2), null);
        request.setPatches(List.of(patchDetail1, patchDetail2));

        when(robotService.runRobot(any(RunRobotRequest.class))).thenThrow(new ValidationErrorException("Invalid instruction"));

        String requestJson = "{\"coords\":[0,0],\"roomSize\":[5,5],\"instructions\":\"NXSW\",\"patches\":[[1,1],[2,2]]}";

        mockMvc.perform(post("/api/v1/robot/run")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("Invalid instruction"));
    }
}