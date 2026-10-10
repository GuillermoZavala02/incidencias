package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MiembroRepositorio extends JpaRepository<Miembro, Long> {
    List<Miembro> findByUsuario_IdUsuarioAndActivoTrue(Long idUsuario);

    Miembro findByUsuario_IdUsuarioAndProyecto_IdProyecto(Long idUsuario, Long idProyecto);

    boolean existsByUsuario_IdUsuarioAndProyecto_IdProyectoAndActivoFalse(Long idUsuario, Long idProyecto);
}
