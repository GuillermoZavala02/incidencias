package com.example.incidencias.entidad;

import java.time.OffsetDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incidencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "proyecto", "miembroCreador", "miembroResponsable" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    @EqualsAndHashCode.Include
    private Long idIncidencia;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "cuerpo", nullable = false, columnDefinition = "TEXT")
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto", nullable = false, foreignKey = @ForeignKey(name = "fk_incidencia_proyecto"))
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro_creador", nullable = false, foreignKey = @ForeignKey(name = "fk_incidencia_miembro_creador"))
    private Miembro miembroCreador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro_responsable", foreignKey = @ForeignKey(name = "fk_incidencia_miembro_responsable"))
    private Miembro miembroResponsable;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private OffsetDateTime fechaModificacion;

    @Column(name = "prioridad", nullable = false, length = 50)
    private String prioridad;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
}
