Proyecto API Seguros

API REST desarrollada en Java 17 y Spring Boot para gestionar usuarios y pólizas de seguros.

Tecnologías principales

Java 17

Spring Boot 3.x

Spring Data JPA

H2 Database (en memoria)

Maven

Springdoc OpenAPI (Swagger)

Endpoints principales

Usuarios

POST /api/usuarios – Crear usuario.

GET /api/usuarios/{id} – Obtener usuario por ID.

GET /api/usuarios – Listar usuarios.

PUT /api/usuarios/{id} – Actualizar usuario.

DELETE /api/usuarios/{id} – Eliminar usuario.

Pólizas

POST /api/polizas – Crear póliza.

GET /api/polizas/{id} – Obtener póliza por ID.

GET /api/polizas – Listar pólizas.

PUT /api/polizas/{id} – Actualizar póliza.

DELETE /api/polizas/{id} – Eliminar póliza.

Ejecutar el proyecto

Requisitos previos

Java 17 instalado.

Maven instalado.

Comandos

mvn clean install
mvn spring-boot:run

Para empaquetar y ejecutar:

mvn package
java -jar target/seguros-api-0.0.1-SNAPSHOT.jar

Base de datos H2

Consola: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa

Contraseña: (vacío)

Swagger

Documentación disponible en:

http://localhost:8080/swagger-ui.html

Tests unitarios

Se han creado pruebas en UsuarioServiceTest que validan:

Crear usuario (crearUsuario_deberiaGuardarUsuario).

Obtener usuario por ID (obtenerUsuarioPorId_deberiaRetornarUsuario).

Excepción cuando el usuario no existe.

Eliminación de usuario.
