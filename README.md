# Microservicio de Gestión de Clientes

Este proyecto es un microservicio desarrollado en **Spring Boot con Java 17**, que gestiona clientes con funcionalidades para **crear clientes, listar clientes y obtener métricas**. Utiliza **MySQL** como base de datos y valida el **token de autenticación** mediante una conexión HTTP POST al microservicio de usuarios.

## Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de contar con:

- **Java 17**
- **Maven**
- **MySQL Server**
- **Microservicio de Usuarios en ejecución**
- **IDE** (IntelliJ, Eclipse, VS Code, etc.)

## Configuración de la Base de Datos
Para conectar el microservicio de clientes a MySQL, edita el archivo de configuración `application.properties` ubicado en:

```
src/main/resources/application.properties
```

Modifica las credenciales de la base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mss_client
spring.datasource.username=useraldis
spring.datasource.password=passAldis
spring.jpa.hibernate.ddl-auto=update
```

> **Nota:** Reemplaza `mss_client`, `useraldis` y `passAldis` con los valores correctos.

## Configuración de Validación del Token
Este microservicio **no autentica usuarios**, pero valida los tokens enviados en las solicitudes. Para ello, se debe configurar la URL del microservicio de usuario en `application.properties`:

📌 **userRest(Microservicio que valida el token):** [http://localhost:8080/swagger-ui.html](https://github.com/almeidaAldis/userRest)

```properties
mss.user.rest.host=http://localhost:8084
```

Cada petición al microservicio de clientes deberá incluir un **token JWT** en el encabezado, el cual será validado mediante una llamada HTTP POST al microservicio de usuarios.

## Ejecución del Proyecto
Para compilar y ejecutar el proyecto, usa los siguientes comandos:

```sh
# Compilar el proyecto
mvn clean install

# Ejecutar el microservicio
mvn spring-boot:run
```

Al iniciar, las tablas se generarán automáticamente en la base de datos configurada.


## Endpoints Disponibles
Este microservicio expone los siguientes endpoints:

- **Crear Cliente:** `POST /clients`
- **Listar Clientes:** `GET /clients`
- **Obtener Métricas:** `GET /clients/metrics`

### 📌 Swagger UI
Puedes acceder a la documentación de la API en:

📌 **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

📌 **API Docs (JSON):** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## Validación del Token de Acceso
Para acceder a los endpoints, se debe incluir un **token JWT** en el encabezado de la petición:

```http
token: <token_obtenido>
```

El microservicio de clientes validará este token realizando una petición HTTP POST al microservicio de usuarios en:

```http
POST http://localhost:8084/auth/verify-access-token
```

Con el siguiente cuerpo JSON:

```json
{
  "token": "<token_obtenido>"
}
```

Si el token es válido, se permitirá el acceso al recurso solicitado.

## Contacto y Soporte
Para dudas o problemas, contacta al equipo de desarrollo.

---
**© 2025 - Microservicio de Gestión de Clientes - Java 17**

