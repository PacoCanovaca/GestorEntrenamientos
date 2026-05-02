-- Crear base de datos

CREATE DATABASE trainingManager;

-- Crear tabla Tipo_movimiento

CREATE TABLE Tipo_movimiento (
    id_tipo_movimiento SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);

-- Crear tabla Ejercicio

CREATE TABLE Ejercicio (
    id_ejercicio SERIAL PRIMARY KEY,
    id_tipo_movimiento BIGINT UNSIGNED NOT NULL, -- Tuve que añadir las palabras BIGINT UNSIGNED NOT NULL porque son las que derivan de haber creado el id con tipo SERIAL en la tabla Tipo_movimiento
    nombre VARCHAR(50) NOT NULL UNIQUE,
    enlace_video VARCHAR(150),
    explicacion TEXT,
    
    FOREIGN KEY (id_tipo_movimiento) REFERENCES Tipo_movimiento(id_tipo_movimiento)
);