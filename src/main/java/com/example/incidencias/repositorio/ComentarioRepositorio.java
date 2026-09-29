package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

}
