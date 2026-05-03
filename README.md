# Gestor Entrenamientos

## 📖 ¿Qué es este proyecto?
**Gestor Entrenamientos** es una aplicación de escritorio dirigida al uso personal de entrenadores personales o deportistas particulares. Su objetivo principal es facilitar la gestión y recopilación de ejercicios, funcionando como un repositorio centralizado e interactivo donde el usuario puede almacenar, filtrar y consultar ejercicios de todo tipo. 

En próximas versiones, el sistema ampliará sus capacidades para incluir la creación, planificación y administración de rutinas de entrenamiento completas a partir de los ejercicios guardados. Además, la aplicación permite exportar los datos a formato `.xml`.

## 🛠️ Tecnologías Usadas
El proyecto está desarrollado bajo el patrón Modelo-Vista-Controlador (MVC) y utiliza el siguiente *stack* tecnológico:

* **Lenguaje:** Java 
* **Interfaz Gráfica:** JavaFX
* **Gestión de dependencias y *build*:** Maven
* **Base de Datos:** MySQL (MariaDB) (conector `mysql-connector-j`)
* **Procesamiento de datos:** Jakarta XML Binding (`jakarta.xml.bind`) para la exportación y lectura de archivos XML.

## 🚀 Instrucciones de Instalación y Ejecución

### Requisitos previos
* Tener instalado el **JDK de Java** (versión compatible con JavaFX).
* Tener instalado un IDE como **IntelliJ IDEA**.
* Un servidor de base de datos local. Por ejemplo, **MySQL Server**.

### Configuración de la Base de Datos
1. Inicia el servicio de MySQL Server en tu entorno local.
2. Ejecuta los scripts SQL alojados en la carpeta `docs/bbdd/` en el siguiente orden para preparar el entorno:
   * `scriptsCreacion.sql` (para generar la estructura).
   * `scriptsInsercionDatos.sql` (para insertar información inicial en la base de datos).

### Ejecución de la aplicación
1. Abre **IntelliJ IDEA**.
2. Selecciona `File` -> `Open...` y navega hasta el directorio del proyecto `proyecto/gestorEntrenamientos`.
3. IntelliJ debería detectar automáticamente el archivo `pom.xml` y descargar las dependencias de Maven.
4. Para ejecutar la aplicación, puedes abrir el panel de Maven (generalmente en el lado derecho), navegar a `Plugins` -> `javafx` y hacer doble clic en `javafx:run`. Alternativamente, puedes configurar una configuración de ejecución en IntelliJ para arrancar la clase principal de la aplicación.

## 🗂️ Estructura del Repositorio

El repositorio está dividido en dos grandes bloques: la documentación técnica y el código fuente de la aplicación.

* 📂 **`docs/`**: Contiene toda la documentación, esquemas y bases de datos del proyecto.
  * 📁 **`bbdd/`**: Scripts de creación, modificación, inserción y consultas SQL, junto con los diagramas Entidad-Relación y Lógico-Relacional (`.drawio` y `.png`).
  * 📁 **`programacion/`**: Documentación específica sobre la lógica del código y estructura de clases.
  * 📁 **`sistemas/`**: Informes técnicos, esquema del sistema e incluso un vídeo (`grabacionEjecucionPrograma.mp4`) demostrando el funcionamiento de la aplicación.
* 📂 **`proyecto/gestorEntrenamientos/`**: Raíz del proyecto Java.
  * 📄 `pom.xml`: Archivo de configuración de Maven con las dependencias.
  * 📁 **`src/main/java/`**: Código fuente estructurado en paquetes (controladores, modelos, conexión a BD...).
  * 📁 **`src/main/resources/`**: Archivo XML y vistas en formato FXML de la interfaz gráfica.