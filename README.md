## Challenge ONE Literalura - Catálogo de Libros

En este desafío, he construido una aplicación de consola en Java utilizando Spring Boot y PostgreSQL sobre un catálogo de libros.

### Objetivo del Proyecto

El objetivo principal de Challenge ONE Literalura es permitir a los usuarios interactuar con un catálogo de libros mediante una serie de opciones de un menu:

1. **Búsqueda de Libros por Título**: Utilizando la API Gutendex, la aplicación busca libros por su título y los registra en la base de datos PostgreSQL si no están presentes.

2. **Listado de Libros Registrados**: Muestra todos los libros almacenados en nuestra base de datos, proporcionando detalles como título, autor, idioma, etc.

3. **Listado de Autores Registrados**: Permite visualizar todos los autores de los libros almacenados, proporcionando información sobre sus obras.

4. **Filtrado por Autores Vivos en un Año Específico**: Ofrece la capacidad de listar autores que estaban vivos en un año determinado, utilizando la información disponible en nuestra base de datos.

5. **Filtrado por Idioma**: Permite a los usuarios listar libros según el idioma especificado, facilitando la exploración de libros en diferentes idiomas.

### Tecnologías Utilizadas

Para la realización de este proyecto, se han utilizado las siguientes tecnologías y herramientas:

- **Java**: Lenguaje de programación principal para la lógica de la aplicación.
- **Spring Boot**: Framework que facilita el desarrollo de aplicaciones Java con configuración mínima.
- **Spring Data JPA**: Facilita la integración con la capa de persistencia, permitiendo operaciones CRUD con la base de datos de manera sencilla.
- **PostgreSQL**: Base de datos relacional para almacenar la información de los libros y autores.
- **Maven**: Herramienta de gestión de dependencias que simplifica la configuración del proyecto y la gestión de bibliotecas.

### Configuración y Desarrollo

1. **Clonar el Repositorio**: Abrir una terminal (o CMD) y clona el repositorio Challenge ONE Literalura desde GitHub:

   ```bash
   git clone https://github.com/axel-23/challenge-one-literalura.git
   ```

2. **Configuración de la Base de Datos**: Para la persistencia de datos, configuré una base de datos PostgreSQL. Utilicé variables de entorno en el archivo `application.properties` para mantener la configuración de conexión segura y flexible. Las variables de entorno necesarias son:

    - `DB_USER`: Nombre de usuario de la base de datos.
    - `DB_PASSWORD`: Contraseña del usuario de la base de datos.
    - `DB_HOST`: Dirección del host donde se encuentra la base de datos.
    - `DB_NAME`: Nombre de la base de datos que se utilizará para el proyecto.
    
    Ejemplo de `application.properties`:
    
    ```properties
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
