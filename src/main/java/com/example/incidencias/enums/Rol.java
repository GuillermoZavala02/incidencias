package com.example.incidencias.enums;

import java.util.Set;

public enum Rol {
    ADMINISTRADOR(Set.of(
            Permiso.CREAR_INCIDENCIA,
            Permiso.CREAR_COMENTARIO,
            Permiso.ASIGNAR_RESPONSABLE,
            Permiso.AGREGAR_MIEMBRO,
            Permiso.CAMBIAR_ROL,
            Permiso.CERRAR_INCIDENCIA,
            Permiso.ELIMINAR_MIEMBRO,
            Permiso.ELIMINAR_PROYECTO)),
    MIEMBRO(Set.of(
            Permiso.CREAR_INCIDENCIA,
            Permiso.CREAR_COMENTARIO));

    private Set<Permiso> permisos;

    Rol(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Boolean tienePermiso(Permiso permiso) {
        return permisos.contains(permiso);
    }
}
