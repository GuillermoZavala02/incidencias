package com.example.incidencias.seguridad;

import com.example.incidencias.entidad.Incidencia;
import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.enums.Permiso;
import com.example.incidencias.enums.Rol;
import java.util.Objects;

public class AutorizacionServicio {
    public static boolean verificarPermiso(Miembro miembro, Permiso permiso) {
        Rol rol = miembro.getRol();
        return rol.tienePermiso(permiso);
    }

    public static boolean EsMiembroDelProyecto(Miembro miembro, Incidencia incidencia) {
        return Objects.equals(miembro.getProyecto().getIdProyecto(), incidencia.getProyecto().getIdProyecto());
    }

    public static boolean verificarPermisoYPertenencia(Incidencia incidencia, Miembro miembro, Permiso permiso) {
        return EsMiembroDelProyecto(miembro, incidencia) && verificarPermiso(miembro, permiso);
    }
}