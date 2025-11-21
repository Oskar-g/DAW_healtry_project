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

Tienes el esquema de la base de datos con sus create e insert en el fichero esquema.sql y datos.sql localizado en la raiz de este proyecto. 

La configuración de conexión a la base de datos se encuentra en el proyecto healtryback:
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
```bash
   com.oscar.healtry.HealtryApplication
```
y el front en
```bash
  com.oscar.healtry.HealtryFrontApplication
```

La aplicación se iniciará en el puerto **8080** para el back y 8083 para el front:
```bash
   http://localhost:8080
   http://localhost:8083
```

---


## 4.1 Estructura relevante del proyecto back

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
│   │   │   ├── service/                    # interfaces de servicios
│   │   │   └── service/impl                # implementación de la lógica y casos de uso 
│   │   └── resources/						
│   │       └── application.yaml            # Config del proyecto
└── pom.xml                                 # Definición de proyecto con maven
```

## 4.2 Estructura relevante del proyecto front

```
healtry/
├── src/
│   ├── main/
│   │   ├── java/com/oscar/healtry/
│   │   │   ├── HealtryApplication.java     # Clase main (Aquí va el @SpringBootApplication)
│   │   │   ├── controller/                 # Controladores de acceso a las páginas 
│   │   │   ├── dto/                        # objetos de transferencia de datos (POJOS)
│   │   │   ├── service/                    # interfaces de servicios
│   │   │   └── service/impl                # implementación de la lógica y casos de uso 
│   │   └── resources/            
│   │       ├── templates/                  # Plantillas de thymeleaf
│   │       ├── static/                     # Recursos estáticos
│   │       └── application.yaml            # Config del proyecto
└── pom.xml                                 # Definición de proyecto con maven
```

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
