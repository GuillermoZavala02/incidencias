package com.example.incidencias.exepcion;

public class MiembroNoEncontradoException extends RuntimeException {

    public MiembroNoEncontradoException(Long id) {
        super("Miembro no encontrado con el ID: " + id);
    }
}
