package com.example.incidencias.repositorio;

import com.example.incidencias.entidad.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiembroRepositorio extends JpaRepository<Miembro, Long> {

}
