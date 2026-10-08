package com.example.incidencias.seguridad;

import com.example.incidencias.entidad.Incidencia;
import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.enums.Permiso;
import com.example.incidencias.enums.Rol;
import com.example.incidencias.exepcion.AccesoDenegadoExepcion;

import java.util.Objects;

public class AutorizacionServicio {
    public static boolean verificarPermiso(Miembro miembro, Permiso permiso) {
        Rol rol = miembro.getRol();
        return rol.tienePermiso(permiso) && miembro.isActivo();
    }

    public static boolean EsMiembroDelProyecto(Miembro miembro, Incidencia incidencia) {
        return Objects.equals(miembro.getProyecto().getIdProyecto(), incidencia.getProyecto().getIdProyecto())
                && miembro.isActivo();
    }

    public static boolean verificarPermisoYPertenenciaIncidencia(Incidencia incidencia, Miembro miembro,
            Permiso permiso) {
        return EsMiembroDelProyecto(miembro, incidencia) && verificarPermiso(miembro, permiso);
    }

    public static boolean verificarPermisoYPertenenciaMiembro(Miembro miembro, Long idProyecto, Permiso permiso) {
        if (verificarPermiso(miembro, permiso) && Objects.equals(idProyecto, miembro.getProyecto().getIdProyecto())) {
            return true;
        } else {
            throw new AccesoDenegadoExepcion("No tienes permiso para realizar esta accion");
        }
    }
}