# Movie Rental System

## Descripción del Proyecto

El sistema de alquiler de películas es una aplicación desarrollada con **Spring Boot** para gestionar una tienda de alquiler de películas. Permite a los usuarios buscar películas, alquilar ejemplares disponibles y devolverlos posteriormente. Además, proporciona un reporte detallado de las películas más alquiladas y los ingresos generados.

## Características Principales

- **Gestor de Películas**:

  - Cada película tiene un nombre, descripción y un género (comedia, romance o acción).
  - Las películas cuentan con varios ejemplares con códigos únicos.
- **Alquiler y Devolución**:

  - Los clientes pueden alquilar películas por un período de 7 días.
  - El sistema permite devolver las películas en cualquier momento.
  - El costo del alquiler depende del género de la película:
    - Comedia: $5,000
    - Romance: $6,000
    - Acción: $7,000
  - Los costos pueden ser ajustados por la administración.
- **Reportes**:

  - Se genera un reporte con las películas más alquiladas, el número de veces alquiladas y los ingresos totales.

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.x**:
  - Spring Data JPA
  - Spring Security
  - Spring Web
- **Base de datos**: MySQL 8.2.4
- **Maven** como herramienta de gestión de dependencias
- **Lombok** para reducir el código boilerplate
- **MapStruct** para mapear entidades y DTOs
-- **Docker** para contenerización y despliegue

## Arquitectura del Proyecto

El proyecto está estructurado siguiendo el modelo de arquitectura en capas:

1. **Controladores (controller)**: Manejan las solicitudes HTTP y devuelven respuestas.
2. **Servicios (service)**: Implementan la lógica de negocio.
3. **Repositorios (repository)**: Interactúan con la base de datos mediante JPA.
4. **Modelos (model)**: Representan las entidades de la base de datos.
5. **Data Transfer Objects (dto)**: Facilitan la transferencia de datos entre las capas.
6. **Mapeadores (mapper)**: Convierte entidades en DTOs y viceversa.
7. **Configuracion (config)**: Permite la configuracion de generalidades y de la seguridad de los endpoints.
8. **Excepciones (exception)**: Implementa el manejo de excepciones personalizadas o de reglas de negocio.
9. **Utilidades (util)**: Implementa clases para utilidades del proyecto, por ejemplo: repuestas (response) genericas para todos los controladores.

## Endpoints Principales

### Películas

- **Buscar Películas**: Permite buscar películas por nombre o descripción.

  - **Endpoint**: `GET /api/movies?query={nombre||descripcion}`
- **Reporte de Películas**: Genera un reporte con las películas más alquiladas.

  - **Endpoint**: `GET /api/movies/report`

### Alquiler

- **Alquilar Película**: Permite a un cliente alquilar un ejemplar de una película.

  - **Endpoint**: `POST /api/rentals/1`
  - **Devolver Película**: Permite devolver un ejemplar alquilado.
  - **Endpoint**: `PUT /api/rentals/return/1`

## Configuración de Seguridad

El sistema está configurado con **Spring Security** para proteger rutas sensibles. Las rutas protegidas requieren autenticación básica con un nombre de usuario y contraseña.

### Credenciales por Defecto

Estas son las credenciales del AUTH para las peticiones:

  - **Username**: admin
  - **Password**: 12345

## Configuración del Proyecto

### Requisitos Previos

- JDK 21
- Maven 3.8+
- Docker (para contenerización y despliegue)

### Configuración de Base de Datos

El proyecto utiliza MySQL como base de datos para facilitar el desarrollo y las pruebas.

- **URL**: `jdbc:mysql://localhost/makrosoft?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true`
- **JDBC URL**: `com.mysql.cj.jdbc.Driver`
- **Usuario**: `root`
- **Contraseña**: (vacío)

### Docker

Este proyecto se puede ejecutar dentro de contenedores Docker para facilitar el despliegue y la ejecución. A continuación, se detallan los pasos para construir y ejecutar el proyecto con Docker:

1. **Construir la imagen Docker**: El archivo `Dockerfile` en el directorio raíz del proyecto, contiene la configuración necesaria para empaquetar la aplicación Spring Boot en un contenedor Docker.

```
  # Usar una imagen base de Java
  FROM openjdk:21-jdk-slim

  # Copiar el archivo JAR del proyecto al contenedor
  COPY target/movies-0.0.1-SNAPSHOT.jar app.jar

  # Configurar el contenedor para ejecutar la aplicación Spring Boot
  ENTRYPOINT ["java", "-jar", "app.jar"]
```

El siguiente comando construye la imagen Docker a partir del `Dockerfile`:

```
  docker build -t movies-app .
```

2. **Configurar Docker Compose**: Para gestionar tanto la aplicación Spring Boot como la base de datos MySQL en contenedores separados, se uso Docker Compose. El archivo `docker-compose.yml` define ambos servicios:

```
  version: "3.9"
  services:
    app:
      build:
        context: .
        dockerfile: Dockerfile
      container_name: movies-app-container
      ports:
        - "9090:9090"
      environment:
        SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/makrosoft?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true
        SPRING_DATASOURCE_USERNAME: root
        SPRING_DATASOURCE_PASSWORD: rootpassword
      depends_on:
        - db
    db:
      image: mysql:8.2.4
      container_name: mysql-db
      environment:
        MYSQL_ROOT_PASSWORD: rootpassword
        MYSQL_DATABASE: makrosoft
      ports:
        - "3306:3306"
```

Para ejecutar ambos contenedores (la aplicación y la base de datos MySQL), utiliza Docker Compose:

```
  docker-compose up --build
```

3. **Acceso a la aplicación**:

Una vez que los contenedores estén en ejecución, la aplicación Spring Boot estará disponible en `http://localhost:9090`.

### Ejecutar el Proyecto

#### IDE

Para ejecutar este proyecto se puede usar un IDE con soporte para springboot como IntelliJ, Springtools, Eclipse, etc. En mi caso use Visual Studio Code con las extensiones de java y springboot.

- **Extension Pack for Java**: 0.29.0
- **Spring Boot Extension Pack**: 0.2.1

Solo debe abrir este proyecto en el editor, construir y ejecutar.

#### CMD o Consola

Si se tienen todas las variables de entorno configuradas, puede hacer lo siguiente:

1. Clona el repositorio.
2. Ejecuta el comando:
   ```bash
   mvn spring-boot:run
   ```
3. La aplicación estará disponible en `http://localhost:9090`.

## Pruebas con Thunder o Postman

### Autenticación

Al realizar solicitudes protegidas, utiliza **Basic Auth** en Postman:

- **Username**: `admin`
- **Password**: `12345`

### Ejemplo de Solicitud para Alquilar una Película

- **Endpoint**: `POST /api/rentals/1`
- **Headers**:
  - `Content-Type`: `application/json`

## Notas

- Este proyecto está en desarrollo y puede ser extendido con funcionalidades adicionales como:
  - Registro y gestión de usuarios.
  - Notificaciones automáticas para devoluciones pendientes.
  - Integración con sistemas de pago.

## Contribución

Si deseas contribuir, realiza un fork del repositorio, crea una rama para tu funcionalidad o corrección, y envía un pull request.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
