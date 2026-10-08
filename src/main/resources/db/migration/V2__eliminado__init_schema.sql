ALTER TABLE pruebas.miembro
ADD COLUMN activo BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE pruebas.miembro
DROP CONSTRAINT fk_miembro_proyecto;

ALTER TABLE pruebas.miembro
ADD CONSTRAINT fk_miembro_proyecto
    FOREIGN KEY (id_proyecto)
    REFERENCES pruebas.proyecto(id_proyecto)
    ON DELETE CASCADE;

ALTER TABLE pruebas.incidencia
DROP CONSTRAINT fk_incidencia_proyecto;

ALTER TABLE pruebas.incidencia
ADD CONSTRAINT fk_incidencia_proyecto
    FOREIGN KEY (id_proyecto)
    REFERENCES pruebas.proyecto(id_proyecto)
    ON DELETE CASCADE;

ALTER TABLE pruebas.comentario
DROP CONSTRAINT fk_comentario_incidencia;

ALTER TABLE pruebas.comentario
ADD CONSTRAINT fk_comentario_incidencia
    FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia(id_incidencia)
    ON DELETE CASCADE;

ALTER TABLE pruebas.incidencia_etiqueta
DROP CONSTRAINT fk_incidencia_etiqueta_incidencia;

ALTER TABLE pruebas.incidencia_etiqueta
ADD CONSTRAINT fk_incidencia_etiqueta_incidencia
    FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia(id_incidencia)
    ON DELETE CASCADE;

ALTER TABLE pruebas.cambios_incidencia
DROP CONSTRAINT fk_cambios_incidencia_incidencia;

ALTER TABLE pruebas.cambios_incidencia
ADD CONSTRAINT fk_cambios_incidencia_incidencia
    FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia(id_incidencia)
    ON DELETE CASCADE;