package com.example.incidencias.exepcion;

public class ElementoNoEncontradoException extends RuntimeException {

    public ElementoNoEncontradoException(String tipo, Long id) {
        super(tipo + " no encontrado con el ID: " + id);
    }
}
