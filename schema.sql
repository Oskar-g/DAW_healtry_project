
-- Creación ded Esquema 
DROP DATABASE `healtry`;
CREATE DATABASE `healtry`; 
USE `healtry`;

-- Creación de tablas


CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ejercicios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `url_multimedia` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ingredientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `calorias_por_racion` decimal(6,2) DEFAULT NULL,
  `gramos_por_racion` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `configuraciones` (
  `clave` varchar(100) NOT NULL,
  `valor` text NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`clave`)
);

-- Tablas con foráneas

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(255) NULL,
  `id_rol` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `correo` (`correo`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuarios_ibfk_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
);


CREATE TABLE `nutricionistas` (
  `id` int(11) NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  `experiencia_anios` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `nutricionistas_ibfk_usuarios` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`)
);


CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `altura_cm` decimal(5,2) DEFAULT NULL,
  `peso_kg` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `clientes_ibfk_usuarios` FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`)
);

CREATE TABLE `medidas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `cintura_cm` decimal(5,2) DEFAULT NULL,
  `cadera_cm` decimal(5,2) DEFAULT NULL,
  `imc` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `medidas_ibfk_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
);


CREATE TABLE `dietas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_nutricionista` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_nutricionista` (`id_nutricionista`),
  CONSTRAINT `dietas_ibfk_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `dietas_ibfk_nutricionistas` FOREIGN KEY (`id_nutricionista`) REFERENCES `nutricionistas` (`id`)
);

CREATE TABLE `comidas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_dieta` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `dia_semana` varchar(15) DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dieta` (`id_dieta`),
  CONSTRAINT `comidas_ibfk_dietas` FOREIGN KEY (`id_dieta`) REFERENCES `dietas` (`id`)
);

CREATE TABLE `comidas_ingredientes` (
  `id_comida` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `cantidad` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id_comida`,`id_ingrediente`),
  KEY `id_ingrediente` (`id_ingrediente`),
  CONSTRAINT `comidas_ingredientes_ibfk_comidas` FOREIGN KEY (`id_comida`) REFERENCES `comidas` (`id`),
  CONSTRAINT `comidas_ingredientes_ibfk_ingredientes` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id`)
);



CREATE TABLE `planes_ejercicio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `planes_ejercicio_ibfk_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
);

CREATE TABLE `planes_ejercicio_ejercicios` (
  `id_plan` int(11) NOT NULL,
  `id_ejercicio` int(11) NOT NULL,
  `dia_semana` varchar(15) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_plan`,`id_ejercicio`,`dia_semana`),
  KEY `id_ejercicio` (`id_ejercicio`),
  CONSTRAINT `planes_ejercicio_ejercicios_ibfk_planes_ejercicio` FOREIGN KEY (`id_plan`) REFERENCES `planes_ejercicio` (`id`),
  CONSTRAINT `planes_ejercicio_ejercicios_ibfk_ejercicios` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicios` (`id`)
);

