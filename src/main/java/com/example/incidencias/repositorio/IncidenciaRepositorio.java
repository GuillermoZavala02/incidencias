package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

}
