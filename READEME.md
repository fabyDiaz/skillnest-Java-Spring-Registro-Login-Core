# Login y Registro

## Objetivos
Crear una aplicación de registro y login con validaciones, seguridad y buenas prácticas de desarrollo utilizando Spring Boot.

## Configuración Inicial

### Base de Datos
- Crear una base de datos llamada `login_registro_db`
- Configurar las credenciales en el archivo `application.properties`

### Proyecto
- Nombre del proyecto: `login_registro`
- Incluir todas las dependencias necesarias en `pom.xml`:
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - MySQL Connector
  - Tomcat Jasper (para JSP)
  - JSTL
  - Spring Boot DevTools
  - BCrypt para encriptación

## Modelo de Usuario

El modelo debe incluir los siguientes campos:

- **Id**: Identificador único del usuario
- **NombreUsuario**: Nombre de usuario para login
- **Contraseña**: Contraseña encriptada
- **ConfirmacionContraseña**: Campo temporal para validación (no se guarda en BD)
- **Correo**: Correo electrónico del usuario
- **Nombre**: Nombre real del usuario
- **Apellido**: Apellido del usuario
- **FechaDeNacimiento**: Fecha de nacimiento
- **FechaCreacion**: Timestamp de creación del registro
- **FechaActualizacion**: Timestamp de última actualización

## Validaciones Requeridas

### Nombre de Usuario
- Longitud mínima: 3 caracteres
- Longitud máxima: 15 caracteres
- Debe ser único en la base de datos

### Contraseña
- Longitud mínima: 8 caracteres
- Debe incluir al menos una letra