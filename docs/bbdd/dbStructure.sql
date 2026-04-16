-- 1. Creación de estructura (DDL)

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


-- 2. Introducción de primeros valores

-- Introducir valores posibles en Tipo_movimiento

INSERT INTO tipo_movimiento (nombre, descripcion) VALUES
('Tracción vertical', 'Movimiento que consiste en tirar de una carga de arriba hacia abajo, o elevar el propio cuerpo. Se enfoca principalmente en el dorsal ancho y los bíceps.'),
('Tracción horizontal', 'Movimiento de acercar una carga hacia el torso en un plano horizontal. Trabaja el grosor de la espalda, romboides y trapecios.'),
('Empuje vertical', 'Acción de alejar un peso hacia arriba, por encima de la cabeza. Su enfoque muscular principal son los deltoides (hombros) y los tríceps.'),
('Empuje horizontal', 'Movimiento que consiste en alejar una carga del pecho hacia el frente en un plano horizontal. Activa principalmente el pectoral, deltoides anterior y tríceps.'),
('Bisagra cadera (glúteo)', 'Movimiento donde el cuerpo pivota sobre la articulación de la cadera, buscando la máxima contracción y tensión en el glúteo mayor al extender la cadera.'),
('Bisagra de cadera (isquios)', 'Movimiento de pivote sobre la cadera con rodillas semiflexionadas, cuyo objetivo es el estiramiento bajo carga y la activación de los músculos isquiosurales.'),
('Dominante de rodilla', 'Movimiento donde la principal palanca y grado de flexión ocurre en la articulación de la rodilla. El trabajo recae predominantemente en los cuádriceps.'),
('Bíceps', 'Movimientos de aislamiento que consisten en la flexión del codo para estimular específicamente el músculo bíceps braquial.'),
('Tríceps', 'Movimientos de aislamiento basados en la extensión del codo para aislar el trabajo en las tres cabezas del músculo tríceps braquial.'),
('Gemelos', 'Movimientos de aislamiento que implican la flexión plantar del tobillo para desarrollar el gastrocnemio y el sóleo.'),
('Core anti-extensión', 'Ejercicios isométricos o dinámicos donde la musculatura abdominal debe contraerse para evitar que la columna lumbar se arquee hacia atrás.'),
('Core anti-inclinación', 'Ejercicios donde el tronco debe resistir la flexión lateral. Obligan a la musculatura oblicua y estabilizadora a mantener la columna recta frente a una carga asimétrica.'),
('Core anti-rotación', 'Ejercicios en los que el abdomen debe resistir fuerzas de torsión, manteniendo los hombros y caderas alineados y evitando que el tronco gire.'),
('Cardio', 'Actividad física rítmica y continua diseñada para elevar la frecuencia cardíaca, mejorando la resistencia aeróbica y la salud cardiovascular.'),
('Clean', 'Movimiento explosivo propio de la halterofilia en el que se levanta la barra desde el suelo (o posiciones colgantes) hasta apoyarla sobre los hombros frontales en un solo movimiento rápido.'),
('Yerk', 'Segundo movimiento clásico de halterofilia. Consiste en impulsar la carga de forma explosiva desde los hombros hasta la extensión completa de los brazos por encima de la cabeza, usando el empuje de las piernas.'),
('Snatch', 'Movimiento de halterofilia altamente técnico y rápido donde se levanta la barra desde el suelo hasta por encima de la cabeza, con los brazos completamente extendidos, en un único movimiento ininterrumpido.');

-- Introducir primeros registros en tabla Ejercicio

INSERT INTO ejercicio (nombre, id_tipo_movimiento, enlace_video, explicacion) VALUES
('Dominada', 1, 'https://www.youtube.com/watch?v=un6HKZo2Mhs', 'Subiremos de manera explosiva y aguantaremos la fase de bajada. Al bajar es importante no perder la tensión muscular por completo ni descolgar los hombros.'),
('Jalon al pecho', 1, 'https://www.youtube.com/watch?v=ShqtJk37UPM', 'Tracciona llevando la barra hacia zona del esternón. Los hombros irán hacia atras (simulando sacar pecho), evitaremos que los hombros roten hacia dentro al tracionar. En la fase excéntrica iremos lento y controlado, sin que perdamos tensión al final del rango.'),
('Remo en cuerda', 1, 'https://www.youtube.com/watch?v=vRsHJtFbyDY', ''),
('Face pull', 2, 'https://www.youtube.com/watch?v=0Po47vvj9g4&pp=ygUJZmFjZSBwdWxs', 'Lleva las manos hacia la altura de la frente, llevando los codos hacia arriba y hacia atras.'),
('Remo con alcance desde abajo', 2, 'https://www.youtube.com/watch?v=I5r_tzXW0S4', ''),
('Renegade Row ', 2, 'https://www.youtube.com/watch?v=G1AcX8Y_byg', 'Realizar una flexion estricta  (si no sale puedes apoyar las rodillas para este gesto) y despues tracionamos llevando la mano hacia la cadera manteniendo el torso estable, sin que rote.'),
('Elevaciones laterales', 3, 'https://www.youtube.com/watch?v=n_r-ROwHkdA', 'Eleva las mancuernas ligeramente en diagonal, lateralmente y hacia delante. Intentanta que los codos esten extendidos o ligeramente flexionados. Y manten las manos todo y hombro siempre en linea.'),
('Press militar barra', 3, 'https://www.youtube.com/watch?v=_aISMzimYEA', ''),
('Push press', 3, 'https://www.youtube.com/watch?v=d0d0TWaiukA', ''),
('Aperturas con mancuernas', 4, 'https://www.youtube.com/watch?v=z8juzhSsFKU', ''),
('Flexiones', 4, 'https://www.youtube.com/watch?v=mm6_WcoCVTA&list=PLyqKj7LwU2RuyZwWCIiDHuFZGN11QW3Ff&index=26', ''),
('Press banca con barra', 4, 'https://www.youtube.com/watch?v=SCVCLChPQFY', '');
