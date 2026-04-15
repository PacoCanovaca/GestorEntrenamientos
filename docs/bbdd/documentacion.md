## 1. Análisis de datos

La aplicación va a manejar, en primer lugar, información sobre ejercicios físicos. Es decir, la entidad principal será Ejercicio. Después, la aplicación tendrá la funcionalidad de gestionar sesiones de entrenamientos a partir de la unión de muchos de esos ejercicios. Por tanto, existirá también una entidad Entrenamiento. 

Ambas entidades se relacionarán de tal manera que un ejercicio puede estar en muchos entrenamientos y un entrenamiento puede contener muchos ejercicios. Por tanto, se generará una entidad intermedia que permita llevar a cabo la relación muchos a muchos (N:M).

Además, existirá una entidad Tipo_movimiento que servirá para establecer las distintas categorías de ejercicios que existen en la base de datos. Esta entidad se relacionará con la entidad Ejercicio en cuanto a que un tipo de movimiento puede incluir muchos ejercicios pero un ejercicio sólo pertenece a un tipo de movimiento (1:N).

De la misma manera, también habrá una entidad Tipo_entrenamiento que será destinada a establecer las categorías de entrenamiento que se pueden crear. Siguiendo la línea del párrafo anterior, un tipo de entrenamiento incluye muchos entrenamientos pero a un entrenamiento sólo se le puede asignar un tipo de entrenamiento (1:N).

He decidido crear ambas entidades anteriores (Tipo_entrenamiento y Tipo_movimiento) en lugar de incluirlas como atributos en Entrenamiento y Ejercicio por la potencial escalabilidad futura de la aplicación. Si quiero más adelante incluir nuevos valores para cualquiera de ellas, será más fácil de actualizar la base de datos.