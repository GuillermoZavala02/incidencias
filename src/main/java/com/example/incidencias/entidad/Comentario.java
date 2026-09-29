package com.example.incidencias.entidad;

import java.time.OffsetDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    @Column(nullable = false)
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidencia", nullable = false)
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro", nullable = false)
    private Miembro miembro;

    @Column(nullable = false)
    private OffsetDateTime fechaCreacion;
}
