package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.enums.Permiso;
import com.example.incidencias.enums.Rol;
import com.example.incidencias.exepcion.ElementoNoEncontradoException;
import com.example.incidencias.repositorio.MiembroRepositorio;
import com.example.incidencias.repositorio.UsuarioRepositorio;
import com.example.incidencias.seguridad.AutorizacionServicio;

import jakarta.transaction.Transactional;

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
                return miembroRepositorio.findById(id)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", id));
        }

        @Transactional
        public Miembro GuardarMiembro(Long idMiembroActor, Long idUsuarioNuevo, Long idProyecto, Rol rol) {

                Miembro actor = miembroRepositorio.findById(idMiembroActor)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembroActor));
                AutorizacionServicio.verificarPermisoYPertenenciaMiembro(actor, idProyecto, Permiso.AGREGAR_MIEMBRO);

                if (rol == null) {
                        rol = Rol.MIEMBRO;
                }

                if (miembroRepositorio.existsByUsuario_IdUsuarioAndProyecto_IdProyectoAndActivoFalse(idUsuarioNuevo,
                                idProyecto)) {
                        Miembro miembro = miembroRepositorio
                                        .findByUsuario_IdUsuarioAndProyecto_IdProyecto(idUsuarioNuevo, idProyecto);
                        miembro.setActivo(true);
                        miembro.setRol(rol);
                        miembroRepositorio.save(miembro);
                        return miembro;
                }

                Miembro miembro = new Miembro();
                miembro.setProyecto(proyectoRepositorio.findById(idProyecto)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Proyecto", idProyecto)));
                miembro.setUsuario(usuarioRepositorio.findById(idUsuarioNuevo)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Usuario", idUsuarioNuevo)));
                miembro.setFechaUnion(OffsetDateTime.now());
                miembro.setRol(rol);
                miembro.setActivo(true);

                return miembroRepositorio.save(miembro);
        }

        @Transactional
        public void EliminarMiembro(Long idMiembroActor, Long idMiembroAEliminar) {

                Miembro actor = miembroRepositorio.findById(idMiembroActor)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembroActor));
                Miembro miembroAEliminar = miembroRepositorio.findById(idMiembroAEliminar)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembroAEliminar));
                AutorizacionServicio.verificarPermisoYPertenenciaMiembro(actor,
                                miembroAEliminar.getProyecto().getIdProyecto(), Permiso.ELIMINAR_MIEMBRO);

                miembroAEliminar.setActivo(false);
                miembroRepositorio.save(miembroAEliminar);
        }

        @Transactional
        public void CambiarRol(Long idMiembroActor, Long idMiembro, Rol nuevoRol) {

                if (nuevoRol == null) {
                        throw new IllegalArgumentException("El nuevo rol no puede ser null");
                }

                Miembro actor = miembroRepositorio.findById(idMiembroActor)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembroActor));
                Miembro miembro = miembroRepositorio.findById(idMiembro)
                                .orElseThrow(() -> new ElementoNoEncontradoException("Miembro", idMiembro));
                AutorizacionServicio.verificarPermisoYPertenenciaMiembro(actor, miembro.getProyecto().getIdProyecto(),
                                Permiso.CAMBIAR_ROL);

                miembro.setRol(nuevoRol);
                miembroRepositorio.save(miembro);
        }

}
