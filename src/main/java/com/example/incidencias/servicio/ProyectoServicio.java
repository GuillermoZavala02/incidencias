package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.entidad.Proyecto;
import com.example.incidencias.entidad.Usuario;
import com.example.incidencias.repositorio.MiembroRepositorio;
import com.example.incidencias.repositorio.ProyectoRepositorio;
import com.example.incidencias.enums.Rol;
import java.time.OffsetDateTime;

import jakarta.transaction.Transactional;

@Service
public class ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;
    private final MiembroRepositorio miembroRepositorio;

    public ProyectoServicio(ProyectoRepositorio proyectoRepositorio, MiembroRepositorio miembroRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
        this.miembroRepositorio = miembroRepositorio;
    }

    public List<Proyecto> ObtenerTodosLosProyectos() {
        return proyectoRepositorio.findAll();
    }

    public Proyecto ObtenerProyectoPorId(Long id) {
        return proyectoRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Proyecto GuardarProyecto(Proyecto proyecto, Usuario usuario) {
        Proyecto Guardado = proyectoRepositorio.save(proyecto);
        Miembro nuevoMiembro = new Miembro();
        nuevoMiembro.setProyecto(Guardado);
        nuevoMiembro.setUsuario(usuario);
        nuevoMiembro.setFechaUnion(OffsetDateTime.now());
        nuevoMiembro.setRol(Rol.ADMINISTRADOR);
        miembroRepositorio.save(nuevoMiembro);
        return Guardado;
    }

    public void EliminarProyecto(Long id) {
        proyectoRepositorio.deleteById(id);
    }

}
