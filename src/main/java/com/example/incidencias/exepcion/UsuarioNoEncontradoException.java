package com.example.incidencias.exepcion;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(Long id) {
        super("Usuario no encontrado con el ID: " + id);
    }
}