package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.enums.Permiso;
import com.example.incidencias.enums.Rol;
import com.example.incidencias.exepcion.MiembroNoEncontradoException;
import com.example.incidencias.exepcion.ProyectoNoEncontradoException;
import com.example.incidencias.exepcion.UsuarioNoEncontradoException;
import com.example.incidencias.repositorio.MiembroRepositorio;
import com.example.incidencias.repositorio.UsuarioRepositorio;
import com.example.incidencias.seguridad.AutorizacionServicio;
import com.example.incidencias.repositorio.ProyectoRepositorio;
import java.time.OffsetDateTime;

@Service
public class MiembroServicio {
    private final MiembroRepositorio miembroRepositorio;
    private final ProyectoRepositorio proyectoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public MiembroServicio(MiembroRepositorio miembroRepositorio, ProyectoRepositorio proyectoRepositorio,
            UsuarioRepositorio usuarioRepositorio) {
        this.miembroRepositorio = miembroRepositorio;
        this.proyectoRepositorio = proyectoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Miembro> ObtenerTodosLosMiembros() {
        return miembroRepositorio.findAll();
    }

    public Miembro ObtenerMiembroPorId(Long id) {
        return miembroRepositorio.findById(id).orElseThrow(() -> new MiembroNoEncontradoException(id));
    }

    public Miembro GuardarMiembro(Long idMiembroActor, Long idUsuarioNuevo, Long idProyecto, Rol rol) {

        Miembro actor = miembroRepositorio.findById(idMiembroActor)
                .orElseThrow(() -> new MiembroNoEncontradoException(idMiembroActor));
        AutorizacionServicio.verificarPermisoYPertenenciaMiembro(actor, idProyecto, Permiso.AGREGAR_MIEMBRO);

        Miembro miembro = new Miembro();
        miembro.setProyecto(proyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new ProyectoNoEncontradoException(idProyecto)));
        miembro.setUsuario(usuarioRepositorio.findById(idUsuarioNuevo)
                .orElseThrow(() -> new UsuarioNoEncontradoException(idUsuarioNuevo)));
        miembro.setFechaUnion(OffsetDateTime.now());
        miembro.setRol(rol);

        return miembroRepositorio.save(miembro);
    }

    public void EliminarMiembro(Long idMiembroActor, Long idMiembroAEliminar) {
        Miembro actor = miembroRepositorio.findById(idMiembroActor)
                .orElseThrow(() -> new MiembroNoEncontradoException(idMiembroActor));
        Miembro miembroAEliminar = miembroRepositorio.findById(idMiembroAEliminar)
                .orElseThrow(() -> new MiembroNoEncontradoException(idMiembroAEliminar));
        AutorizacionServicio.verificarPermisoYPertenenciaMiembro(actor, miembroAEliminar.getProyecto().getIdProyecto(),
                Permiso.ELIMINAR_MIEMBRO);
        miembroRepositorio.deleteById(idMiembroAEliminar);
    }

}
