package com.example.incidencias.entidad;

import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proyecto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private OffsetDateTime fecha;
}
