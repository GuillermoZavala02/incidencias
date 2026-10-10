package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.incidencias.entidad.*;
import com.example.incidencias.repositorio.*;
import com.example.incidencias.exepcion.ElementoNoEncontradoException;
import com.example.incidencias.seguridad.AutorizacionServicio;

import jakarta.transaction.Transactional;

import com.example.incidencias.enums.Permiso;

@Service
public class IncidenciaServicio {
    private final IncidenciaRepositorio incidenciaRepositorio;
    private final MiembroRepositorio miembroRepositorio;

    public IncidenciaServicio(IncidenciaRepositorio incidenciaRepositorio, MiembroRepositorio miembroRepositorio) {
        this.incidenciaRepositorio = incidenciaRepositorio;
        this.miembroRepositorio = miembroRepositorio;
    }


    public Incidencia ObtenerIncidenciaPorId(Long id) {
        return incidenciaRepositorio.findById(id).orElseThrow(() -> new ElementoNoEncontradoException("Incidencia: ", id));
    }

    public List<Incidencia> ObtenerIncidenciasPorProyecto(Long idProyecto) {
        return incidenciaRepositorio.findByProyecto_IdProyecto(idProyecto);
    }

    public List<Incidencia> ObtenerIncidenciasAsignadasAUsuario(Long idUsuario) {
        return incidenciaRepositorio.findByMiembroResposable_IdMiembro(idUsuario);
    }

    public List<Incidencia> ObtenerIncidenciasCreadasPorUsuario(Long idUsuario) {
        return incidenciaRepositorio.findByMiembroCreador_IdMiembro(idUsuario);
    }
    
    @Transactional
    public void EliminarIncidencia(Long idIncidencia, Long idMiembro) {

        Miembro miembro = miembroRepositorio.findById(idMiembro)
                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro: ", idMiembro));
        Incidencia incidencia = incidenciaRepositorio.findById(idIncidencia)
                .orElseThrow(() -> new ElementoNoEncontradoException("Incidencia: ", idIncidencia));
        

        Long idProyecto = incidencia.getProyecto().getIdProyecto();
        

        if (AutorizacionServicio.verificarPermisoYPertenenciaMiembro(miembro,idProyecto, Permiso.CREAR_INCIDENCIA));

            incidenciaRepositorio.deleteById(idIncidencia);
    }

    private Incidencia CrearIncidencia(Incidencia incidencia, Long idMiembroCreador) {
        Miembro creador = miembroRepositorio.findById(idMiembroCreador)
                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro: ", idMiembroCreador));
        incidencia.setMiembroCreador(creador);
        return incidenciaRepositorio.save(incidencia);
    }

    private Incidencia ActualizarIncidencia(Incidencia incidencia) {
        return incidenciaRepositorio.save(incidencia);
    }
    
}
