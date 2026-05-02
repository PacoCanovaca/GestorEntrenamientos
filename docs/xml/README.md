# Lenguaje de marcas (XML)

El archivo .xml incluido en este apartado tiene la función de recibir una exportación de ejercicios organizados por el tipo de movimiento al que pertenecen. Esto puede ser llevado a cabo por parte del usuario a través de un botón que incluye la aplicación siempre que quiera, y permite registrar los ejercicios de manera ordenada según su tipo, obteniendo además una especie de backup en caso de problemas con la base de datos.

Así, el documento se organiza en los siguientes niveles de elementos:
1. Contenedor padre
2. Tipo de movimiento
3. Ejercicios correspondientes

La lógica controladora extrae los ejercicios de la base de datos y, a través de su id de tipo de movimiento, los clasifica y los exporta al archivo .xml dentro del tipo que coresponde.

El documento se valida a través del esquema en el archivo .xsd, por lo que debe seguir el formato indicado en dicho archivo. Se han incluido dos capturas de pantalla que muestran una validación correcta y otra incorrecta gracias a esta conexión entre XML y XSD.