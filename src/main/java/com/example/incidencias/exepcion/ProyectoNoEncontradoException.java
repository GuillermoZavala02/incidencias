package com.example.incidencias.exepcion;

public class ProyectoNoEncontradoException extends RuntimeException {

    public ProyectoNoEncontradoException(Long id) {
        super("Proyecto no encontrado con el ID: " + id);
    }
}
