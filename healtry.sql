-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               12.0.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for healtry
DROP DATABASE IF EXISTS `healtry`;
CREATE DATABASE IF NOT EXISTS `healtry` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `healtry`;

-- Dumping structure for table healtry.clientes
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id_cliente` int(11) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `altura_cm` decimal(5,2) DEFAULT NULL,
  `peso_kg` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.clientes: ~0 rows (approximately)

-- Dumping structure for table healtry.comidas
DROP TABLE IF EXISTS `comidas`;
CREATE TABLE IF NOT EXISTS `comidas` (
  `id_comida` int(11) NOT NULL AUTO_INCREMENT,
  `id_dieta` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `dia_semana` varchar(15) DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`id_comida`),
  KEY `id_dieta` (`id_dieta`),
  CONSTRAINT `comidas_ibfk_1` FOREIGN KEY (`id_dieta`) REFERENCES `dietas` (`id_dieta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.comidas: ~0 rows (approximately)

-- Dumping structure for table healtry.comidas_ingredientes
DROP TABLE IF EXISTS `comidas_ingredientes`;
CREATE TABLE IF NOT EXISTS `comidas_ingredientes` (
  `id_comida` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `cantidad` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id_comida`,`id_ingrediente`),
  KEY `id_ingrediente` (`id_ingrediente`),
  CONSTRAINT `comidas_ingredientes_ibfk_1` FOREIGN KEY (`id_comida`) REFERENCES `comidas` (`id_comida`),
  CONSTRAINT `comidas_ingredientes_ibfk_2` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id_ingrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.comidas_ingredientes: ~0 rows (approximately)

-- Dumping structure for table healtry.configuraciones
DROP TABLE IF EXISTS `configuraciones`;
CREATE TABLE IF NOT EXISTS `configuraciones` (
  `clave` varchar(100) NOT NULL,
  `valor` text NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.configuraciones: ~0 rows (approximately)

-- Dumping structure for table healtry.dietas
DROP TABLE IF EXISTS `dietas`;
CREATE TABLE IF NOT EXISTS `dietas` (
  `id_dieta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_nutricionista` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_dieta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_nutricionista` (`id_nutricionista`),
  CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `dietas_ibfk_2` FOREIGN KEY (`id_nutricionista`) REFERENCES `nutricionistas` (`id_nutricionista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.dietas: ~0 rows (approximately)

-- Dumping structure for table healtry.ejercicios
DROP TABLE IF EXISTS `ejercicios`;
CREATE TABLE IF NOT EXISTS `ejercicios` (
  `id_ejercicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `url_multimedia` text DEFAULT NULL,
  PRIMARY KEY (`id_ejercicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.ejercicios: ~0 rows (approximately)

-- Dumping structure for table healtry.ingredientes
DROP TABLE IF EXISTS `ingredientes`;
CREATE TABLE IF NOT EXISTS `ingredientes` (
  `id_ingrediente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `calorias_por_racion` decimal(6,2) DEFAULT NULL,
  `gramos_por_racion` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id_ingrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.ingredientes: ~0 rows (approximately)

-- Dumping structure for table healtry.medidas
DROP TABLE IF EXISTS `medidas`;
CREATE TABLE IF NOT EXISTS `medidas` (
  `id_medida` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `cintura_cm` decimal(5,2) DEFAULT NULL,
  `cadera_cm` decimal(5,2) DEFAULT NULL,
  `imc` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id_medida`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `medidas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.medidas: ~0 rows (approximately)

-- Dumping structure for table healtry.nutricionistas
DROP TABLE IF EXISTS `nutricionistas`;
CREATE TABLE IF NOT EXISTS `nutricionistas` (
  `id_nutricionista` int(11) NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  `experiencia_anios` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_nutricionista`),
  CONSTRAINT `nutricionistas_ibfk_1` FOREIGN KEY (`id_nutricionista`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.nutricionistas: ~0 rows (approximately)

-- Dumping structure for table healtry.planes_ejercicio
DROP TABLE IF EXISTS `planes_ejercicio`;
CREATE TABLE IF NOT EXISTS `planes_ejercicio` (
  `id_plan` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_plan`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `planes_ejercicio_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.planes_ejercicio: ~0 rows (approximately)

-- Dumping structure for table healtry.planes_ejercicio_ejercicios
DROP TABLE IF EXISTS `planes_ejercicio_ejercicios`;
CREATE TABLE IF NOT EXISTS `planes_ejercicio_ejercicios` (
  `id_plan` int(11) NOT NULL,
  `id_ejercicio` int(11) NOT NULL,
  `dia_semana` varchar(15) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_plan`,`id_ejercicio`,`dia_semana`),
  KEY `id_ejercicio` (`id_ejercicio`),
  CONSTRAINT `planes_ejercicio_ejercicios_ibfk_1` FOREIGN KEY (`id_plan`) REFERENCES `planes_ejercicio` (`id_plan`),
  CONSTRAINT `planes_ejercicio_ejercicios_ibfk_2` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicios` (`id_ejercicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.planes_ejercicio_ejercicios: ~0 rows (approximately)

-- Dumping structure for table healtry.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.roles: ~3 rows (approximately)
INSERT INTO `roles` (`id_rol`, `nombre`) VALUES
	(1, 'ADMINISTRACION'),
	(2, 'NUTRICIONISTA'),
	(3, 'USUARIO');

-- Dumping structure for table healtry.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dumping data for table healtry.usuarios: ~1 rows (approximately)
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `apellidos`, `correo`, `contraseña`, `id_rol`, `activo`) VALUES
	(1, 'yo', 'mismo', 'oscar@healtry.com', '1234', 1, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
