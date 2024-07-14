package com.aluracursos.challengeliteralura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData implements IgetData{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T Data(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
