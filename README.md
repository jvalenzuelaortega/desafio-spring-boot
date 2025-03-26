# Desafio Spring Boot

Este proyecto es una aplicación de Spring Boot que proporciona una API REST para gestionar tareas y usuarios. Utiliza Spring Security para la autenticación y autorización, y Swagger para la documentación de la API.

## Requisitos

- Java 17 o superior
- Maven 3.8.1 o superior

## Configuración

### Base de Datos

La aplicación utiliza una base de datos en memoria H2 para el almacenamiento de datos. La configuración de la base de datos se encuentra en el archivo `src/main/resources/application.properties`.

### Swagger

Swagger está integrado para proporcionar documentación de la API. Puedes acceder a la interfaz de Swagger en `http://localhost:8080/swagger-ui.html`.

### Seguridad

La aplicación utiliza JWT para la autenticación. La configuración de seguridad se encuentra en `src/main/java/com/example/desafio_spring_boot/config/security/config/WebSecurityConfig.java`.

## Ejecución

Para ejecutar la aplicación, utiliza el siguiente comando de Maven:

```sh
mvn spring-boot:run
```

### Endpoints
#### Autenticación
- **POST** /api/auth/get-token: Genera un token JWT para autenticación.

```
curl --location --request POST 'http://localhost:8080/api/auth/get-token?username=admin&password=password123'
```

```json
{"message":"Operacion exitosa","status_code":200,"response_data":{"token":"eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4"}}
```

#### Usuarios
- **GET** /api/users: Obtiene la lista de todos los usuarios.

```
curl --location 'http://localhost:8080/api/user/get-all-users' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4'
```

```json
{"message":"Operacion exitosa: listado","status_code":200,"response_data":[{"id":1,"userName":"admin","password":"password123"},{"id":2,"userName":"test-user-1","password":"password123"},{"id":3,"userName":"jvalenzo","password":"123456"}]}
```

- **POST** /api/users: Crea un nuevo usuario.

```
curl --location 'http://localhost:8080/api/user/create-user' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4' \
--data '{
   "username" : "jvalenzo",
   "password" : "123456"
}'
```

```json
{"message":"Operacion exitosa: guardar","status_code":201,"response_data":{"id":3,"userName":"jvalenzo","password":"123456"}}
```

#### Tareas
- **GET** /api/tasks: Obtiene la lista de todas las tareas.

```
curl --location 'http://localhost:8080/api/tasks/get-all-tasks' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4'
```

```json
{"message":"Operacion exitosa","status_code":200,"response_data":[{"id":1,"description":"Complete project report","create_date":"23-03-2025 07:00:00","update_date":"23-03-2025 07:00:00","user":{"username":"admin","password":"password123"},"status":{"id":1,"status":"Not Started"}},{"id":2,"description":"Review code changes","create_date":"23-03-2025 07:05:00","update_date":"23-03-2025 07:05:00","user":{"username":"test-user-1","password":"password123"},"status":{"id":2,"status":"In Progress"}},{"id":3,"description":"Deployment flow","create_date":"26-03-2025 12:29:36","update_date":"26-03-2025 12:29:36","user":{"username":"admin","password":"password123"},"status":{"id":3,"status":"Completed"}}]}
```

- **POST** /api/tasks: Crea una nueva tarea.

```
curl --location 'http://localhost:8080/api/tasks/create-task' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4' \
--data '{
   "username":"admin",
   "description":"Deployment flow",
   "status_description":"Completed"
}'
```

```json
{"message":"Operacion exitosa: guardar","status_code":201,"response_data":{"id":3,"description":"Deployment flow","create_date":"26-03-2025 12:29:36","update_date":"26-03-2025 12:29:36","user":{"username":"admin","password":"password123"},"status":{"id":3,"status":"Completed"}}}
```

- **PUT** /api/tasks/{id}: Actualiza una tarea existente.

```
curl --location --request PUT 'http://localhost:8080/api/tasks/update-task/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4' \
--data '{
   "username":"test-user-1",
   "description":"Deployment flow",
   "status_description":"Not Started"
}'
```

```json
{"message":"Operacion exitosa: actualizar","status_code":200,"response_data":{"id":2,"description":"Deployment flow","create_date":"23-03-2025 07:05:00","update_date":"26-03-2025 12:29:53","user":{"username":"test-user-1","password":"password123"},"status":{"id":1,"status":"Not Started"}}}
```

- **DELETE** /api/tasks/{id}: Elimina una tarea existente.

```
curl --location --request DELETE 'http://localhost:8080/api/tasks/delete-task/2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0Mjk1OTczNiwiZXhwIjoxNzQyOTYyNzM2fQ.YE4TlwKKSdX0dlwTYaNpbXwirnVy_aC0ZiG-ArPfYlUsCgjmebS2c4pF8s7uHjI4'
```

```json
{"message":"Operacion exitosa: eliminar","status_code":200,"response_data":2}
```

##### Colleccion de postman
 - [Collection Task](Task%20Previred.postman_collection.json)

### Documentación de la API (No generado)
~~La documentación de la API generada por Swagger está disponible en http://localhost:8080/swagger-ui.html.~~


### Estructura del Proyecto
- ```src/main/java/com/example/desafio_spring_boo```t: Contiene el código fuente de la aplicación.
- ```src/main/resources```: Contiene los archivos de configuración y recursos estáticos.

### Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.


### Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.