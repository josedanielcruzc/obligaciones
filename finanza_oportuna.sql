
CREATE DATABASE finanza_oportuna_obl
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

    CREATE SEQUENCE public.sec_cliente_id
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.sec_cliente_id
    OWNER TO postgres;

    CREATE SEQUENCE public.sec_obligacion_id
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.sec_obligacion_id
    OWNER TO postgres;

    CREATE SEQUENCE public.sec_pago_id
    INCREMENT 1
    START 9
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.sec_pago_id
    OWNER TO postgres;



CREATE TABLE public.banco
(
    codigo character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    nombre character varying(22) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT "PRIMARY" PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.banco
    OWNER to postgres;

    

CREATE TABLE public.cliente
(
    identificacion character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    primer_nombre character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    segundo_nombre character varying(20) COLLATE pg_catalog."default",
    primer_apellido character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    segundo_apellido character varying(20) COLLATE pg_catalog."default",
    razon_social character varying(20) COLLATE pg_catalog."default",
    direccion character varying(20) COLLATE pg_catalog."default",
    telefono character varying(20) COLLATE pg_catalog."default",
    tipo_documento character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    codigo character varying(6) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PRIMARY00000" PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.cliente
    OWNER to postgres;

-- Index: fkIdx_13200000

-- DROP INDEX public."fkIdx_13200000";

CREATE INDEX "fkIdx_13200000"
    ON public.cliente USING btree
    (tipo_documento COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;



    CREATE TABLE public.obligacion
(
    codigo character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    descripcion character varying(200) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    valor_total character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    valor_periodo_actual character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    numero_periodos character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    periodo_actual character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    cliente character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    estado character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    producto character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT "PRIMARY00001" PRIMARY KEY (codigo),
    CONSTRAINT "FK_CLIENTE_OBLIGACION" FOREIGN KEY (cliente)
        REFERENCES public.cliente (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.obligacion
    OWNER to postgres;

COMMENT ON CONSTRAINT "FK_CLIENTE_OBLIGACION" ON public.obligacion
    IS 'FK_CLIENTE_OBLIGACION';

-- Index: fkIdx_17200000

-- DROP INDEX public."fkIdx_17200000";

CREATE INDEX "fkIdx_17200000"
    ON public.obligacion USING btree
    (producto COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


    CREATE TABLE public.pago
(
    codigo character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    banco_pago character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    fecha_pago timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    valor_pagado numeric(5,2) NOT NULL,
    periodo_pagado integer NOT NULL,
    obligacion character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT "PRIMARY00002" PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.pago
    OWNER to postgres;

-- Index: fkIdx_15600000

-- DROP INDEX public."fkIdx_15600000";

CREATE INDEX "fkIdx_15600000"
    ON public.pago USING btree
    (banco_pago COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


-- Index: fkIdx_16900000

-- DROP INDEX public."fkIdx_16900000";

CREATE INDEX "fkIdx_16900000"
    ON public.pago USING btree
    (obligacion COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;



CREATE TABLE public.producto
(
    codigo character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    producto character varying(100) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT "PRIMARY00003" PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.producto
    OWNER to postgres;



CREATE TABLE public.tipo_documento
(
    codigo character varying(6) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    tipo character varying(22) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    prefijo character varying(3) COLLATE pg_catalog."default" NOT NULL DEFAULT ''::character varying,
    CONSTRAINT "PRIMARY00004" PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.tipo_documento
    OWNER to postgres;



    