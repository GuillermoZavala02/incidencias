package com.example.incidencias.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incidencia_etiqueta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "incidencia", "etiqueta" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IncidenciaEtiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia_etiqueta")
    @EqualsAndHashCode.Include
    private Long idIncidenciaEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incidencia", nullable = false, foreignKey = @ForeignKey(name = "fk_incidencia_etiqueta_incidencia"))
    private Incidencia incidencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etiqueta", nullable = false, foreignKey = @ForeignKey(name = "fk_incidencia_etiqueta_etiqueta"))
    private Etiqueta etiqueta;
}
