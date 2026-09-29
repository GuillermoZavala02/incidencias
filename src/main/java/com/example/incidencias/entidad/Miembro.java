package com.example.incidencias.entidad;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "miembro", uniqueConstraints = @UniqueConstraint(columnNames = { "id_proyecto", "id_usuario" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMiembro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private OffsetDateTime fechaUnion;

    @Column(nullable = false)
    private String rol;

}
