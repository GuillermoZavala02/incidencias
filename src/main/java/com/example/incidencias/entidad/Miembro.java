package com.example.incidencias.entidad;

import java.time.OffsetDateTime;

import com.example.incidencias.enums.Rol;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "miembro", uniqueConstraints = @UniqueConstraint(name = "uq_usuario_proyecto", columnNames = {
        "id_usuario", "id_proyecto" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "proyecto", "usuario" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miembro")
    @EqualsAndHashCode.Include
    private Long idMiembro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto", nullable = false, foreignKey = @ForeignKey(name = "fk_miembro_proyecto"))
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_miembro_usuario"))
    private Usuario usuario;

    @Column(name = "fecha_union", nullable = false)
    private OffsetDateTime fechaUnion;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 50)
    private Rol rol;

    @Column(name = "activo", nullable = false)
    private boolean activo;
}
