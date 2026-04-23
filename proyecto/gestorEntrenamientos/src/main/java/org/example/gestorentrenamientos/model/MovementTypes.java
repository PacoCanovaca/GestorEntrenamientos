package org.example.gestorentrenamientos.model;

import lombok.Getter;

@Getter
public enum MovementTypes {

    TRACCIONVERTICAL(1, "Tracción vertical", "Movimiento que consiste en tirar de una carga de arriba hacia abajo, o elevar el propio cuerpo. Se enfoca principalmente en el dorsal ancho y los bíceps."),
    TRACCIONHORIZONTAL(2, "Tracción horizontal", "Movimiento de acercar una carga hacia el torso en un plano horizontal. Trabaja el grosor de la espalda, romboides y trapecios."),
    EMPUJEVERTICAL(3, "Empuje vertical", "Acción de alejar un peso hacia arriba, por encima de la cabeza. Su enfoque muscular principal son los deltoides (hombros) y los tríceps."),
    EMPUJEHORIZONTAL(4, "Empuje horizontal", "Movimiento que consiste en alejar una carga del pecho hacia el frente en un plano horizontal. Activa principalmente el pectoral, deltoides anterior y tríceps."),
    BISAGRACADERAGLUTEO(5, "Bisagra cadera (glúteo)", "Movimiento donde el cuerpo pivota sobre la articulación de la cadera, buscando la máxima contracción y tensión en el glúteo mayor al extender la cadera."),
    BISAGRADECADERAISQUIOS(6, "Bisagra de cadera (isquios)", "Movimiento de pivote sobre la cadera con rodillas semiflexionadas, cuyo objetivo es el estiramiento bajo carga y la activación de los músculos isquiosurales."),
    DOMINANTEDERODILLA(7, "Dominante de rodilla", "Movimiento donde la principal palanca y grado de flexión ocurre en la articulación de la rodilla. El trabajo recae predominantemente en los cuádriceps."),
    BICEPS(8, "Bíceps", "Movimientos de aislamiento que consisten en la flexión del codo para estimular específicamente el músculo bíceps braquial."),
    TRICEPS(9, "Tríceps", "Movimientos de aislamiento basados en la extensión del codo para aislar el trabajo en las tres cabezas del músculo tríceps braquial."),
    GEMELOS(10, "Gemelos", "Movimientos de aislamiento que implican la flexión plantar del tobillo para desarrollar el gastrocnemio y el sóleo."),
    COREANTIEXTENSION(11, "Core anti-extensión", "Ejercicios isométricos o dinámicos donde la musculatura abdominal debe contraerse para evitar que la columna lumbar se arquee hacia atrás."),
    COREANTIINCLINACION(12, "Core anti-inclinación", "Ejercicios donde el tronco debe resistir la flexión lateral. Obligan a la musculatura oblicua y estabilizadora a mantener la columna recta frente a una carga asimétrica."),
    COREANTIROTACION(13, "Core anti-rotación", "Ejercicios en los que el abdomen debe resistir fuerzas de torsión, manteniendo los hombros y caderas alineados y evitando que el tronco gire."),
    CARDIO(14, "Cardio", "Actividad física rítmica y continua diseñada para elevar la frecuencia cardíaca, mejorando la resistencia aeróbica y la salud cardiovascular."),
    CLEAN(15, "Clean", "Movimiento explosivo propio de la halterofilia en el que se levanta la barra desde el suelo (o posiciones colgantes) hasta apoyarla sobre los hombros frontales en un solo movimiento rápido."),
    YERK(16, "Yerk", "Segundo movimiento clásico de halterofilia. Consiste en impulsar la carga de forma explosiva desde los hombros hasta la extensión completa de los brazos por encima de la cabeza, usando el empuje de las piernas."),
    SNATCH(17, "Snatch", "Movimiento de halterofilia altamente técnico y rápido donde se levanta la barra desde el suelo hasta por encima de la cabeza, con los brazos completamente extendidos, en un único movimiento ininterrumpido."),
    NULL(-1, null, null);

    private final int id;
    private final String name;
    private final String description;

    MovementTypes(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
