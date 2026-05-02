# Programación

Esta aplicación ha sido diseñada con el objetivo de acceder y gestionar una base de datos de ejercicios físicos que puede funcionar como repositorio para cualquiera que quiera mantener un registro de diferentes ejercicios físicos.

Se usan para ello diferentes librerías:
1. JavaFX para el diseño de una interfaz gráfica y la conexión de la lógica con dicha interfaz
2. JDBC para conectar con la base de datos y realizar las diferentes operaciones del CRUD
3. Bind para llevar a cabo la exportación de ejercicios según su tipo al archivo .xml

La aplicación tiene las siguientes funcionalidades:
- Consulta y filtrado: permite al usuario ver la lista de ejercicios completa y llevar a cabo distintos filtros (por nombre, por tipo de movimiento o por la combinación de ambos)
- Operaciones CRUD: da la posibilidad de crear nuevos ejercicios y ver, modificar o eliminar los que ya existen.
- Exportación: existe una función para exportar los ejercicios organizados por su tipo de movimiento a un archivo .xml validado mediante otro archivo .xsd.

El código está organizado en estos paquetes: model (entidades básicas existentes), controller (lógica del programa), data (set de datos estáticos, es decir, accesibles desde todo el programa), database (conexión con la base de datos), dao (acceso a datos de la base), dto (exportación de datos a XML). Además, también hay un directorio resources que incluye, por un lado, los archivos .fxml para la interfaz gráfica y, por otro, el .xml y su .xsd de validación.