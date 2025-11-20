USE `healtry`;

INSERT INTO `configuraciones` (`clave`, `valor`, `tipo`, `descripcion`) VALUES
('AAAAA', 'AA', 'AA', 'AAA'),
('TITULO', 'Healtry', 'string', 'poseso');

INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'ADMINISTRACION'),
(2, 'NUTRICIONISTA'),
(3, 'CLIENTE');

INSERT INTO `usuarios` (`id`, `id_rol`, `nombre`, `apellidos`, `correo`, `contrasena`, `activo`) VALUES
(1, 1, 'otro', 'mismo', 'admin@healtry.com', '1234', 1),
(2, 2, 'yo', 'mismo', 'oscar@healtry.com', '1234', 1),
(3, 3, 'cliente', 'no sé', 'cliente@healtry.com', '1234', 1);

INSERT INTO `nutricionistas` (`id`, `especialidad`, `experiencia_anios`) VALUES 
(2, 'Master and commander', 1000);

INSERT INTO `clientes` (`id`, `id_nutricionista`, `fecha_nacimiento`, `sexo`, `altura_cm`, `peso_kg`) VALUES
(3, 2, '1990-11-21', 'M', 151.00, 201.00);



INSERT INTO alimentos (nombre, proteinas, grasas, carbohidratos) VALUES
('Manzana', 0.3, 0.2, 14.0),
('Banana', 1.1, 0.3, 23.0),
('Pera', 0.4, 0.1, 15.0),
('Naranja', 0.9, 0.1, 12.0),
('Fresa', 0.8, 0.3, 7.7),
('Piña', 0.5, 0.1, 13.0),
('Sandía', 0.6, 0.2, 8.0),
('Melón', 0.8, 0.2, 8.0),
('Uva', 0.7, 0.2, 17.0),
('Kiwi', 1.1, 0.5, 15.0),
('Tomate', 0.9, 0.2, 3.9),
('Lechuga', 1.4, 0.2, 2.9),
('Zanahoria', 0.9, 0.2, 10.0),
('Brócoli', 2.8, 0.4, 7.0),
('Espinaca', 2.9, 0.4, 3.6),
('Papa cocida', 2.0, 0.1, 20.0),
('Arroz cocido', 2.7, 0.3, 28.0),
('Pasta cocida', 5.0, 1.1, 31.0),
('Pollo a la plancha', 31.0, 3.6, 0.0),
('Pescado blanco', 20.0, 1.5, 0.0),
('Carne magra', 26.0, 8.0, 0.0),
('Huevo', 13.0, 11.0, 1.1),
('Queso fresco', 11.0, 4.0, 3.0),
('Yogur natural', 4.3, 3.3, 4.7),
('Leche entera', 3.2, 3.7, 4.8),
('Pan integral', 13.0, 4.2, 41.0),
('Avena', 13.0, 7.0, 66.0),
('Lentejas cocidas', 9.0, 0.4, 20.0),
('Garbanzos cocidos', 8.9, 2.6, 27.0),
('Aceite de oliva', 0.0, 100.0, 0.0),
('Atún en agua', 25.0, 1.0, 0.0),
('Salmón', 20.0, 13.0, 0.0),
('Tofu', 8.0, 4.0, 1.5),
('Pechuga de pavo', 29.0, 2.0, 0.0),
('Jamón serrano', 30.0, 12.0, 0.0),
('Queso cheddar', 25.0, 33.0, 1.0),
('Mantequilla', 0.5, 81.0, 0.1),
('Nueces', 15.0, 65.0, 14.0),
('Almendras', 21.0, 49.0, 22.0),
('Avellanas', 15.0, 61.0, 17.0),
('Semillas de chía', 17.0, 31.0, 42.0),
('Semillas de girasol', 20.0, 51.0, 20.0),
('Miel', 0.3, 0.0, 82.0),
('Azúcar', 0.0, 0.0, 100.0),
('Sal', 0.0, 0.0, 0.0),
('Pimienta', 0.0, 3.3, 64.0),
('Ajo', 6.0, 0.5, 33.0),
('Cebolla', 1.1, 0.1, 9.3),
('Pimiento rojo', 1.0, 0.2, 6.0),
('Pepino', 0.6, 0.1, 3.6),
('Calabacín', 1.2, 0.3, 3.1),
('Berenjena', 1.0, 0.2, 5.7),
('Champiñones', 3.1, 0.3, 3.3),
('Guisantes', 5.0, 0.4, 14.0),
('Maíz cocido', 3.0, 1.5, 20.0),
('Judías verdes', 2.0, 0.2, 7.0),
('Yogur griego', 9.0, 10.0, 3.0),
('Cereal integral', 8.0, 3.0, 74.0),
('Chocolate negro 70%', 7.0, 42.0, 46.0),
('Tortilla de maíz', 5.0, 3.0, 40.0),
('Pan blanco', 9.0, 3.0, 49.0),
('Croissant', 8.0, 26.0, 45.0),
('Café', 0.1, 0.0, 0.0),
('Té verde', 0.0, 0.0, 0.0),
('Agua', 0.0, 0.0, 0.0),
('Zumo de naranja', 0.7, 0.2, 10.0),
('Mayonesa', 1.0, 75.0, 0.0),
('Ketchup', 1.0, 0.1, 25.0),
('Salsa de soja', 6.0, 0.0, 4.9),
('Vinagre', 0.0, 0.0, 0.3);

-- =====================================
-- INSERTS COMIDAS
-- =====================================

INSERT INTO comidas (id, nombre) VALUES
(1, 'Tostadas con aguacate y huevo'),
(2, 'Ensalada de pollo'),
(3, 'Pasta con atún'),
(4, 'Batido de frutas y avena'),
(5, 'Salmón al horno con verduras'),
(6, 'Tortilla de claras con espinacas'),
(7, 'Porridge de avena con miel y plátano'),
(8, 'Sandwich integral de pavo'),
(9, 'Yogur griego con frutos secos'),
(10, 'Ensalada mixta con atún'),
(11, 'Arroz con pollo y verduras'),
(12, 'Revuelto de champiñones y jamón'),
(13, 'Filete de ternera con puré de patata'),
(14, 'Sopa de verduras'),
(15, 'Crema de calabacín'),
(16, 'Tacos de pescado'),
(17, 'Pizza casera vegetal'),
(18, 'Pechuga de pollo al limón con arroz'),
(19, 'Ensalada de garbanzos'),
(20, 'Lentejas estofadas'),
(21, 'Tosta de salmón y queso crema'),
(22, 'Huevos revueltos con tomate y cebolla'),
(23, 'Smoothie verde'),
(24, 'Hamburguesa casera'),
(25, 'Pasta con salsa de tomate y queso'),
(26, 'Pollo al curry con arroz basmati'),
(27, 'Tortitas de avena'),
(28, 'Bowl de quinoa con verduras'),
(29, 'Sándwich vegetal'),
(30, 'Pan con tomate y jamón serrano');

-- =====================================
-- INSERTS COMIDAS_ALIMENTOS
-- =====================================

-- 1. Tostadas con aguacate y huevo
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(1, 26, 50),
(1, 22, 100),
(1, 30, 5);

-- 2. Ensalada de pollo
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(2, 19, 120),
(2, 12, 40),
(2, 11, 60),
(2, 30, 10);

-- 3. Pasta con atún
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(3, 18, 150),
(3, 31, 80),
(3, 30, 10);

-- 4. Batido de frutas y avena
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(4, 2, 80),
(4, 10, 60),
(4, 27, 30),
(4, 25, 150);

-- 5. Salmón al horno con verduras
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(5, 32, 150),
(5, 14, 100),
(5, 13, 50),
(5, 30, 10);

-- 6. Tortilla de claras con espinacas
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(6, 22, 150),
(6, 15, 50),
(6, 30, 5);

-- 7. Porridge de avena con miel y plátano
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(7, 27, 60),
(7, 43, 10),
(7, 2, 100),
(7, 25, 150);

-- 8. Sandwich integral de pavo
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(8, 26, 60),
(8, 34, 80),
(8, 12, 40);

-- 9. Yogur griego con frutos secos
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(9, 57, 150),
(9, 38, 20),
(9, 39, 15);

-- 10. Ensalada mixta con atún
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(10, 31, 80),
(10, 12, 50),
(10, 11, 50),
(10, 13, 30),
(10, 30, 10);

-- 11. Arroz con pollo y verduras
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(11, 17, 150),
(11, 19, 120),
(11, 14, 50),
(11, 13, 40),
(11, 30, 10);

-- 12. Revuelto de champiñones y jamón
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(12, 53, 100),
(12, 35, 50),
(12, 22, 100),
(12, 30, 5);

-- 13. Filete de ternera con puré de patata
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(13, 21, 150),
(13, 16, 200),
(13, 30, 10);

-- 14. Sopa de verduras
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(14, 11, 50),
(14, 12, 40),
(14, 13, 50),
(14, 14, 50),
(14, 48, 30);

-- 15. Crema de calabacín
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(15, 51, 150),
(15, 25, 50),
(15, 30, 5);

-- 16. Tacos de pescado
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(16, 20, 100),
(16, 60, 50),
(16, 12, 30),
(16, 30, 10);

-- 17. Pizza casera vegetal
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(17, 18, 120),
(17, 12, 40),
(17, 11, 50),
(17, 51, 50),
(17, 30, 10);

-- 18. Pechuga de pollo al limón con arroz
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(18, 19, 150),
(18, 17, 150),
(18, 30, 10);

-- 19. Ensalada de garbanzos
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(19, 29, 150),
(19, 12, 50),
(19, 11, 50),
(19, 30, 10);

-- 20. Lentejas estofadas
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(20, 28, 150),
(20, 13, 50),
(20, 48, 30),
(20, 30, 10);

-- 21. Tosta de salmón y queso crema
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(21, 32, 80),
(21, 36, 30),
(21, 26, 50);

-- 22. Huevos revueltos con tomate y cebolla
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(22, 22, 150),
(22, 11, 50),
(22, 48, 30),
(22, 30, 5);

-- 23. Smoothie verde
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(23, 15, 50),
(23, 12, 50),
(23, 10, 60),
(23, 25, 150);

-- 24. Hamburguesa casera
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(24, 21, 150),
(24, 26, 60),
(24, 12, 40),
(24, 30, 10);

-- 25. Pasta con salsa de tomate y queso
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(25, 18, 150),
(25, 11, 80),
(25, 36, 30),
(25, 30, 5);

-- 26. Pollo al curry con arroz basmati
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(26, 19, 150),
(26, 17, 150),
(26, 49, 20),
(26, 30, 10);

-- 27. Tortitas de avena
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(27, 27, 80),
(27, 2, 50),
(27, 43, 10),
(27, 25, 150);

-- 28. Bowl de quinoa con verduras
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(28, 28, 80),
(28, 12, 50),
(28, 11, 50),
(28, 30, 10);

-- 29. Sándwich vegetal
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(29, 26, 60),
(29, 12, 40),
(29, 11, 50),
(29, 51, 30);

-- 30. Pan con tomate y jamón serrano
INSERT INTO comidas_alimentos (id_comida, id_alimento, gramos) VALUES
(30, 61, 50),
(30, 11, 40),
(30, 35, 50),
(30, 30, 5);