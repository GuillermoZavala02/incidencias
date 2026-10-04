package com.example.incidencias.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.incidencias.entidad.Miembro;
import com.example.incidencias.repositorio.MiembroRepositorio;

@Service
public class MiembroServicio {
    private final MiembroRepositorio miembroRepositorio;

    public MiembroServicio(MiembroRepositorio miembroRepositorio) {
        this.miembroRepositorio = miembroRepositorio;
    }

    public List<Miembro> ObtenerTodosLosMiembros() {
        return miembroRepositorio.findAll();
    }

    public Miembro ObtenerMiembroPorId(Long id) {
        return miembroRepositorio.findById(id).orElse(null);
    }

    public Miembro GuardarMiembro(Miembro miembro) {
        return miembroRepositorio.save(miembro);
    }

    public void EliminarMiembro(Long id) {
        miembroRepositorio.deleteById(id);
    }

}
