package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.CambiosIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambiosIncidenciaRepositorio extends JpaRepository<CambiosIncidencia, Long> {

}
