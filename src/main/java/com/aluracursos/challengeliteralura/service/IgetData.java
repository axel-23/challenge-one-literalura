package com.aluracursos.challengeliteralura.service;

public interface IgetData {
    <T> T Data(String json, Class<T> clase);
}
