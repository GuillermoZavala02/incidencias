package com.example.incidencias.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incidencia_etiqueta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class IncidenciaEtiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncidenciaEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidencia", nullable = false)
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etiqueta", nullable = false)
    private Etiqueta etiqueta;
}
