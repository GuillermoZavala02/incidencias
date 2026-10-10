package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByProyecto_IdProyecto(Long idProyecto);

    List<Incidencia> findByMiembroResposable_IdMiembro(Long idMiembroResponsable);

    List<Incidencia> findByMiembroCreador_IdMiembro(Long idMiembroCreador);
}
