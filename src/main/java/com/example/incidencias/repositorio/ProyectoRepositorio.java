package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepositorio extends JpaRepository<Proyecto, Long> {

}
