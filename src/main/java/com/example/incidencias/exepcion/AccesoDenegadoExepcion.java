package com.example.incidencias.exepcion;

public class AccesoDenegadoExepcion extends RuntimeException {
    public AccesoDenegadoExepcion(String mensaje) {
        super(mensaje);
    }
}
