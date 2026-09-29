CREATE TABLE IF NOT EXISTS pruebas.cambios_incidencia
(
    id_cambios_incidencia bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    id_incidencia bigint NOT NULL,
    id_miembro bigint NOT NULL,
    fecha timestamp with time zone NOT NULL,
    campo_modificado character varying(50) COLLATE pg_catalog."default" NOT NULL,
    valor_anterior character varying(255) COLLATE pg_catalog."default",
    valor_nuevo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cambios_incidencia_pkey PRIMARY KEY (id_cambios_incidencia)
);

CREATE TABLE IF NOT EXISTS pruebas.comentario
(
    id_comentario bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cuerpo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_incidencia bigint NOT NULL,
    id_miembro bigint NOT NULL,
    fecha_creacion timestamp with time zone NOT NULL,
    CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario)
);

CREATE TABLE IF NOT EXISTS pruebas.etiqueta
(
    id_etiqueta bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    titulo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT etiqueta_pkey PRIMARY KEY (id_etiqueta)
);

CREATE TABLE IF NOT EXISTS pruebas.flyway_schema_history
(
    installed_rank integer NOT NULL,
    version character varying(50) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    script character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    checksum integer,
    installed_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    installed_on timestamp without time zone NOT NULL DEFAULT now(),
    execution_time integer NOT NULL,
    success boolean NOT NULL,
    CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);

CREATE TABLE IF NOT EXISTS pruebas.incidencia
(
    id_incidencia bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    titulo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cuerpo text COLLATE pg_catalog."default" NOT NULL,
    id_proyecto bigint NOT NULL,
    id_miembro_creador bigint NOT NULL,
    id_miembro_responsable bigint,
    fecha_creacion timestamp with time zone NOT NULL,
    fecha_modificacion timestamp with time zone,
    prioridad character varying(50) COLLATE pg_catalog."default" NOT NULL,
    estado character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT incidencia_pkey PRIMARY KEY (id_incidencia)
);

CREATE TABLE IF NOT EXISTS pruebas.incidencia_etiqueta
(
    id_incidencia_etiqueta bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    id_incidencia bigint NOT NULL,
    id_etiqueta bigint NOT NULL,
    CONSTRAINT incidencia_etiqueta_pkey PRIMARY KEY (id_incidencia_etiqueta)
);

CREATE TABLE IF NOT EXISTS pruebas.miembro
(
    id_miembro bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    id_usuario bigint NOT NULL,
    id_proyecto bigint NOT NULL,
    fecha_union timestamp with time zone NOT NULL,
    rol character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT miembro_pkey PRIMARY KEY (id_miembro),
    CONSTRAINT uq_usuario_proyecto UNIQUE (id_usuario, id_proyecto)
);

CREATE TABLE IF NOT EXISTS pruebas.proyecto
(
    id_proyecto bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    fecha timestamp with time zone NOT NULL,
    CONSTRAINT proyecto_pkey PRIMARY KEY (id_proyecto)
);

CREATE TABLE IF NOT EXISTS pruebas.usuario
(
    id_usuario bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nombre character varying(50) COLLATE pg_catalog."default" NOT NULL,
    correo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    contrasenia character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT usuario_correo_key UNIQUE (correo)
);

ALTER TABLE IF EXISTS pruebas.cambios_incidencia
    ADD CONSTRAINT fk_cambios_incidencia_incidencia FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia (id_incidencia) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.cambios_incidencia
    ADD CONSTRAINT fk_cambios_incidencia_miembro FOREIGN KEY (id_miembro)
    REFERENCES pruebas.miembro (id_miembro) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.comentario
    ADD CONSTRAINT fk_comentario_incidencia FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia (id_incidencia) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.comentario
    ADD CONSTRAINT fk_comentario_miembro FOREIGN KEY (id_miembro)
    REFERENCES pruebas.miembro (id_miembro) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.incidencia
    ADD CONSTRAINT fk_incidencia_miembro_creador FOREIGN KEY (id_miembro_creador)
    REFERENCES pruebas.miembro (id_miembro) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.incidencia
    ADD CONSTRAINT fk_incidencia_miembro_responsable FOREIGN KEY (id_miembro_responsable)
    REFERENCES pruebas.miembro (id_miembro) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.incidencia
    ADD CONSTRAINT fk_incidencia_proyecto FOREIGN KEY (id_proyecto)
    REFERENCES pruebas.proyecto (id_proyecto) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.incidencia_etiqueta
    ADD CONSTRAINT fk_incidencia_etiqueta_etiqueta FOREIGN KEY (id_etiqueta)
    REFERENCES pruebas.etiqueta (id_etiqueta) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.incidencia_etiqueta
    ADD CONSTRAINT fk_incidencia_etiqueta_incidencia FOREIGN KEY (id_incidencia)
    REFERENCES pruebas.incidencia (id_incidencia) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.miembro
    ADD CONSTRAINT fk_miembro_proyecto FOREIGN KEY (id_proyecto)
    REFERENCES pruebas.proyecto (id_proyecto) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


ALTER TABLE IF EXISTS pruebas.miembro
    ADD CONSTRAINT fk_miembro_usuario FOREIGN KEY (id_usuario)
    REFERENCES pruebas.usuario (id_usuario) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;