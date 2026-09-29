package com.example.incidencias.entidad;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incidencia")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncidencia;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro_creador", nullable = false)
    private Miembro miembroCreador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro_responsable")
    private Miembro miembroResponsable;

    @Column(nullable = false)
    private OffsetDateTime fechaCreacion;

    private OffsetDateTime fechaModificacion;

    @Column(nullable = false)
    private String prioridad;

    @Column(nullable = false)
    private String estado;
}
