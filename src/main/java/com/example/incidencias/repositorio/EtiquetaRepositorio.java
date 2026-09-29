package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepositorio extends JpaRepository<Etiqueta, Long> {

}
