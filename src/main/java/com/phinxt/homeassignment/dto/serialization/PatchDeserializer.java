package com.phinxt.homeassignment.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.exception.ValidationErrorException;

import java.io.IOException;
import java.util.List;

public class PatchDeserializer extends JsonDeserializer<RunRobotRequest.PatchDetail> {
    @Override
    public RunRobotRequest.PatchDetail deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ArrayNode arrayNode = p.getCodec().readTree(p);

        if (arrayNode.size() < 2) {
            throw new ValidationErrorException("Patch entry must contain at least 2 elements: x and y coordinates") {
            };

        }

        if (arrayNode.size() > 3) {
            throw new ValidationErrorException("Patch entry must contain at most 3 elements: x and y coordinates and type") {
            };
        }

        if (arrayNode.get(0).isMissingNode() || arrayNode.get(1).isMissingNode()) {
            throw new ValidationErrorException("Patch entry must contain at least 2 elements: x and y coordinates") {
            };
        }

        if (!arrayNode.get(0).isInt() || !arrayNode.get(1).isInt()) {
            throw new ValidationErrorException("Patch coordinates must be integers") {
            };
        }

        if (arrayNode.size() > 2 && !arrayNode.get(2).isTextual()) {
            throw new ValidationErrorException("Patch type must be a string") {
            };
        }

        List<Integer> coords = List.of(
                arrayNode.get(0).asInt(),
                arrayNode.get(1).asInt());

        String type = null;
        if (arrayNode.size() > 2) {
            TextNode typeNode = (TextNode) arrayNode.get(2);
            type = typeNode.asText();
        }

        return new RunRobotRequest.PatchDetail(coords, type);
    }
}
