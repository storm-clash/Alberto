-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 29-08-2023 a las 12:10:14
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `alberto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

DROP TABLE IF EXISTS `camion`;
CREATE TABLE IF NOT EXISTS `camion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `capacidad` double NOT NULL,
  `ruedas` int NOT NULL,
  `kilometraje` double NOT NULL,
  `utilizado` tinyint NOT NULL,
  `combustible` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d87wlpoghtcscy3hcamk7iof5` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`id`, `matricula`, `capacidad`, `ruedas`, `kilometraje`, `utilizado`, `combustible`) VALUES
(3, 'HAV345H', 200, 4, 1500, 1, 'Eléctrico'),
(4, 'PR56JU', 222, 8, 400, 0, 'Gasolina'),
(9, 'HAV12345l', 678, 4, 9, 0, 'Eléctrico'),
(10, 'HAV12345lg', 1000, 4, 400, 0, 'Eléctrico'),
(11, 'HAV345HD', 333, 5, 7000, 0, 'Eléctrico'),
(12, 'HAV12345JKL', 67890, 8, 7500, 0, 'Petróleo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion_has_chofer`
--

DROP TABLE IF EXISTS `camion_has_chofer`;
CREATE TABLE IF NOT EXISTS `camion_has_chofer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `camion_id` int NOT NULL,
  `chofer_id` int NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_termino` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`camion_id`,`chofer_id`),
  KEY `fk_camion_has_chofer_chofer1_idx` (`chofer_id`),
  KEY `fk_camion_has_chofer_camion_idx` (`camion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `camion_has_chofer`
--

INSERT INTO `camion_has_chofer` (`id`, `camion_id`, `chofer_id`, `fecha_inicio`, `fecha_termino`) VALUES
(8, 10, 1, '2023-08-28 00:00:00', '2023-08-28 00:00:00'),
(10, 3, 1, '2023-08-28 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chofer`
--

DROP TABLE IF EXISTS `chofer`;
CREATE TABLE IF NOT EXISTS `chofer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `edad` int NOT NULL,
  `salario` double NOT NULL,
  `sexo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `chofer`
--

INSERT INTO `chofer` (`id`, `nombre`, `apellido`, `edad`, `salario`, `sexo`) VALUES
(1, 'Jesús', 'García Valladares', 32, 1500, 'male'),
(2, 'Diana', 'garcia', 22, 300000, 'male'),
(3, 'Pochi', 'delfino', 25, 130000, 'female');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pass` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `activado` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `pass`, `user`, `email`, `activado`) VALUES
(1, 'ifarocks26', 'ifarocks', 'ifarocks26@gmail.com', 1),
(2, 'matt1234*', 'matt', 'matt@gmail.com', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `camion_has_chofer`
--
ALTER TABLE `camion_has_chofer`
  ADD CONSTRAINT `fk_camion_has_chofer_camion` FOREIGN KEY (`camion_id`) REFERENCES `camion` (`id`),
  ADD CONSTRAINT `fk_camion_has_chofer_chofer1` FOREIGN KEY (`chofer_id`) REFERENCES `chofer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
