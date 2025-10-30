# Healtry

## Manual de puesta en marcha y notas para el profesor

### 1️ Requisitos previos

El proyecto está configurado y dedsarrollado con: 

- **Java 21**
- **Maven 3.9+**
- **MariaDB 12.0.2**
- **Eclipse 2023-09 (4.29.0)**

---

### 2 Configuración de base de datos

Tienes el esquema de la base de datos con sus create e insert en el fichero healtry.sql localizado en la raiz de este proyecto. 

La configuración de conexión a la base de datos se encuentra en:
```
src/main/resources/application.yaml
```

Configuración por defecto:
```yaml
   server:
     port: 8080

   spring:
     datasource:
       url: jdbc:mariadb://localhost:3306/healtry?useSSL=false&allowPublicKeyRetrieval=true
       username: root
       password: root
       driver-class-name: org.mariadb.jdbc.Driver
   ```

   tedrás que modificar el usuario y contraseña seguramente de este fichero para que pueda lanzarse.

---

### 3 Ejecución del proyecto

Puedes ejecutarlo con maven:
   ```bash
   mvn spring-boot:run
   ```
   
O directamente haciendo un run as java project clicando con botón derecho sobre la clase
   ```
   com.oscar.healtry.HealtryApplication
   ```

La aplicación se iniciará en el puerto **8080**:
   ```
   http://localhost:8080
   ```

---


## 4 Estructura relevante del proyecto

```
healtry/
├── src/
│   ├── main/
│   │   ├── java/com/oscar/healtry/
│   │   │   ├── HealtryApplication.java     # Clase main (Aquí va el @SpringBootApplication)
│   │   │   ├── controller/                 # Controladores de entrada 
│   │   │   ├── dto/                        # objetos de transferencia de datos (POJOS)
│   │   │   ├── model/                      # Entidades JPA
│   │   │   ├── repository/                 # Repositorios con Spring Data
│   │   │   └── service/                    # interfaces de servicios
│   │   │   └── service/impl                # implementación de la lógica y casos de uso 
│   │   └── resources/						
│   │       ├── templates/                  # Plantillas de thymeleaf *
│   │       ├── static/                     # Recursos estáticos *
│   │       └── application.yaml            # Config del proyecto
└── pom.xml                                 # Definición de proyecto con maven
```

 (*) Estos elementos se eliminarán en versiones posteriores cuando monte un servidor para el frontal, mi idea es que todo esto vaya por rest.

---

## 5 Consulta de Api

La documentación de la API está en swagger, puedes acceder por:
  ```
  http://localhost:8080/swagger-ui/index.html
  ```
En caso de arrancarlo en modo debug es probable que te de error, esto es por el devtools, de ser así comentar esta dependencia
  ```
spring-boot-devtools
  ```
Un arranque normal no da este tipo de problemas.
