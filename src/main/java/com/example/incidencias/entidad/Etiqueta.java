package com.example.incidencias.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etiqueta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtiqueta;

    @Column(nullable = false)
    private String titulo;
}
