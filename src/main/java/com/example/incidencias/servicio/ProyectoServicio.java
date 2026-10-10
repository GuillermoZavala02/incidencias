package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.entidad.Proyecto;
import com.example.incidencias.repositorio.MiembroRepositorio;
import com.example.incidencias.repositorio.ProyectoRepositorio;
import com.example.incidencias.enums.Rol;
import java.time.OffsetDateTime;
import com.example.incidencias.seguridad.AutorizacionServicio;
import com.example.incidencias.exepcion.ElementoNoEncontradoException;
import com.example.incidencias.enums.Permiso;
import jakarta.transaction.Transactional;
import com.example.incidencias.repositorio.UsuarioRepositorio;
import java.util.ArrayList;

@Service
public class ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;
    private final MiembroRepositorio miembroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public ProyectoServicio(ProyectoRepositorio proyectoRepositorio, MiembroRepositorio miembroRepositorio,
            UsuarioRepositorio usuarioRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
        this.miembroRepositorio = miembroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Proyecto> ObtenerTodosLosProyectos() {
        return proyectoRepositorio.findAll();
    }

    public List<Proyecto> ObtenerProyectosPorUsuario(Long idUsuario) {
        List<Miembro> miembros = miembroRepositorio.findByUsuario_IdUsuarioAndActivoTrue(idUsuario);
        List<Proyecto> proyectos = new ArrayList<>();
        miembros.forEach(miembro -> {
            proyectos.add(miembro.getProyecto());
        });
        return proyectos;
    }

    @Transactional
    public Proyecto GuardarProyecto(Proyecto proyecto, Long idUsuario) {
        if (proyecto.getIdProyecto() != null) {
            throw new IllegalArgumentException("El proyecto ya existe");
        }

        proyecto.setFecha(OffsetDateTime.now());
        Proyecto Guardado = proyectoRepositorio.save(proyecto);

        Miembro nuevoMiembro = new Miembro();
        nuevoMiembro.setProyecto(Guardado);
        nuevoMiembro.setUsuario(
                usuarioRepositorio.findById(idUsuario)
                        .orElseThrow(() -> new ElementoNoEncontradoException("Usuario", idUsuario)));
        nuevoMiembro.setFechaUnion(OffsetDateTime.now());
        nuevoMiembro.setRol(Rol.ADMINISTRADOR);
        nuevoMiembro.setActivo(true);
        miembroRepositorio.save(nuevoMiembro);
        return Guardado;
    }

    public void EliminarProyecto(Long id, Long idMiembro) {
        Miembro miembro = miembroRepositorio.findById(idMiembro)
                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembro));

        if (AutorizacionServicio.verificarPermisoYPertenenciaMiembro(miembro, id, Permiso.ELIMINAR_PROYECTO))
            proyectoRepositorio.deleteById(id);
    }
}
