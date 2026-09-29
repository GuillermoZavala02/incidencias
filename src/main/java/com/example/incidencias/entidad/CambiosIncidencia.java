package com.example.incidencias.entidad;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "cambios_incidencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambiosIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCambiosIncidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidencia", nullable = false)
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro", nullable = false)
    private Miembro miembro;

    @Column(nullable = false)
    private OffsetDateTime fecha;

    @Column(nullable = false)
    private String campoModificado;

    private String valorAnterior;

    @Column(nullable = false)
    private String valorNuevo;
}
