
INSERT INTO `configuraciones` (`clave`, `valor`, `tipo`, `descripcion`) VALUES
	('AAAAA', 'AA', 'AA', 'AAA'),
	('TITULO', 'Healtry', 'string', 'poseso');

INSERT INTO `roles` (`id`, `nombre`) VALUES
	(1, 'ADMINISTRACION'),
	(2, 'NUTRICIONISTA'),
	(3, 'USUARIO');

INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `correo`, `contraseña`, `id_rol`, `activo`) VALUES
	(1, 'otro', 'mismo', 'admin@healtry.com', '1234', 1, 1),
	(2, 'yo', 'mismo', 'oscar@healtry.com', '1234', 2, 1);
