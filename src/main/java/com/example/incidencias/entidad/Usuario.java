package com.example.incidencias.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasenia;

}
