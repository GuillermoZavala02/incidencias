package com.example.incidencias.entidad;

import java.time.OffsetDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cambios_incidencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "incidencia", "miembro" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CambiosIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cambios_incidencia")
    @EqualsAndHashCode.Include
    private Long idCambiosIncidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidencia", nullable = false, foreignKey = @ForeignKey(name = "fk_cambios_incidencia_incidencia"))
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro", nullable = false, foreignKey = @ForeignKey(name = "fk_cambios_incidencia_miembro"))
    private Miembro miembro;

    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @Column(name = "campo_modificado", nullable = false, length = 50)
    private String campoModificado;

    @Column(name = "valor_anterior", length = 255)
    private String valorAnterior;

    @Column(name = "valor_nuevo", nullable = false, length = 255)
    private String valorNuevo;
}
