-- ======================================================
-- Creación de Esquema
-- ======================================================
DROP DATABASE IF EXISTS `healtry`;
CREATE DATABASE `healtry`;
USE `healtry`;

-- ======================================================
-- Tablas base
-- ======================================================

CREATE TABLE `roles` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE `alimentos` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) NOT NULL,
    `proteinas` DECIMAL(6,2) DEFAULT 0.00,
    `grasas` DECIMAL(6,2) DEFAULT 0.00,
    `carbohidratos` DECIMAL(6,2) DEFAULT 0.00
);

CREATE TABLE `comidas` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE `configuraciones` (
    `clave` VARCHAR(100) NOT NULL PRIMARY KEY,
    `valor` TEXT NOT NULL,
    `tipo` VARCHAR(50) DEFAULT NULL,
    `descripcion` TEXT DEFAULT NULL
);


-- ======================================================
-- Tablas con relaciones
-- ======================================================

CREATE TABLE `comidas_alimentos` (
    `id_comida` INT NOT NULL,
    `id_alimento` INT NOT NULL,
    `gramos` DECIMAL(8,2) NOT NULL CHECK (`gramos` >= 0),
    PRIMARY KEY (`id_comida`, `id_alimento`),
    FOREIGN KEY (`id_comida`) REFERENCES `comidas` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`id_alimento`) REFERENCES `alimentos` (`id`) ON DELETE CASCADE
);

CREATE TABLE `usuarios` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_rol` INT NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `apellidos` VARCHAR(200) DEFAULT NULL,
    `correo` VARCHAR(100) NOT NULL UNIQUE,
    `contrasena` VARCHAR(255) DEFAULT NULL,
    `activo` TINYINT(1) DEFAULT 1,
    FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
);

CREATE TABLE `nutricionistas` (
    `id` INT PRIMARY KEY,
    `especialidad` VARCHAR(100) DEFAULT NULL,
    `experiencia_anios` INT DEFAULT NULL,
    FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
);

CREATE TABLE `clientes` (
    `id` INT PRIMARY KEY,
    `id_nutricionista` INT DEFAULT NULL,
    `fecha_nacimiento` DATE DEFAULT NULL,
    `sexo` VARCHAR(10) DEFAULT NULL,
    `altura_cm` DECIMAL(5,2) DEFAULT NULL,
    `peso_kg` DECIMAL(5,2) DEFAULT NULL,
    FOREIGN KEY (`id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`id_nutricionista`) REFERENCES `nutricionistas` (`id`) ON DELETE CASCADE
);

CREATE TABLE `planes_semanales` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_nutricionista` INT NOT NULL,
    `alias` VARCHAR(100) NOT NULL,
    FOREIGN KEY (`id_nutricionista`) REFERENCES `nutricionistas`(`id`) ON DELETE CASCADE
);

CREATE TABLE `planes_dias` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_plan` INT NOT NULL,
    `id_comida` INT DEFAULT NULL,
    `dia` VARCHAR(20) NOT NULL,
    `tipo_comida` VARCHAR(30) NOT NULL,
    FOREIGN KEY (`id_plan`) REFERENCES `planes_semanales` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`id_comida`) REFERENCES `comidas` (`id`) ON DELETE SET NULL
);

CREATE TABLE `dietas` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
	 `id_plan` INT NOT NULL,
    `fecha_inicio` DATE NOT NULL,
    `fecha_fin` DATE NOT NULL,
	 FOREIGN KEY (`id_plan`) REFERENCES `planes_semanales` (`id`) ON DELETE CASCADE
);

CREATE TABLE `dietas_clientes` (
    `id_dieta` INT NOT NULL,
    `id_cliente` INT NOT NULL,
    PRIMARY KEY (`id_dieta`, `id_cliente`),
    FOREIGN KEY (`id_dieta`) REFERENCES `dietas` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE
);