-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-05-2024 a las 18:52:52
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `construirsa`
--
CREATE DATABASE IF NOT EXISTS `construirsa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `construirsa`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `dni` bigint(20) NOT NULL,
  `apellido` varchar(58) NOT NULL,
  `nombre` varchar(58) NOT NULL,
  `acceso` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `dni`, `apellido`, `nombre`, `acceso`, `estado`) VALUES
(1, 16789456, 'Mendez', 'Juan', 1, 0),
(2, 24753951, 'Sosa', 'Guillermo', 1, 0),
(3, 11254986, 'Fernandez', 'Santiago', 1, 1),
(52, 2838847, 'Argento', 'Pepe', 1, 1),
(53, 31543492, 'Hetfield', 'James', 1, 1),
(54, 4574832, 'Barilari', 'Adrian', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `herramienta`
--

DROP TABLE IF EXISTS `herramienta`;
CREATE TABLE IF NOT EXISTS `herramienta` (
  `id_herramienta` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(60) NOT NULL,
  `stock` int(11) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_herramienta`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `herramienta`
--

INSERT INTO `herramienta` (`id_herramienta`, `nombre`, `descripcion`, `stock`, `estado`) VALUES
(1, 'Taladro', 'Taladro rotopercutor Bosch', 7, 1),
(2, 'Martillo', 'Martillo carpintero 1/2kg 12\' ', 20, 1),
(3, 'Pala', 'Pala punta corazon cabo de madera', 25, 1),
(4, 'Tenaza', 'Tenaza de encofrador 8 pulgadas Bremen', 25, 1),
(5, 'Pico', 'Pico doble punta con cabo de madera', 20, 0),
(18, 'Pinza de punta', 'Pinza de 8 pulgadas con alicate', 3, 1),
(19, 'Destornillador electrico', 'Makita', 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
CREATE TABLE IF NOT EXISTS `movimiento` (
  `id_movimiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_empleado` int(11) NOT NULL,
  `id_herramienta` int(11) NOT NULL,
  `fechap` date DEFAULT NULL,
  `fechad` date DEFAULT NULL,
  `cantidadret` int(11) NOT NULL,
  PRIMARY KEY (`id_movimiento`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_herramienta` (`id_herramienta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`id_movimiento`, `id_empleado`, `id_herramienta`, `fechap`, `fechad`, `cantidadret`) VALUES
(1, 2, 1, '2024-04-15', '0000-00-00', 3),
(2, 2, 3, '2024-04-14', '2024-04-15', 1),
(3, 1, 4, '2022-04-15', '2024-04-16', 2),
(5, 3, 2, '2018-06-15', '2018-06-16', 2),
(6, 3, 5, '2023-11-17', '2023-11-20', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD CONSTRAINT `movimiento_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`),
  ADD CONSTRAINT `movimiento_ibfk_2` FOREIGN KEY (`id_herramienta`) REFERENCES `herramienta` (`id_herramienta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
