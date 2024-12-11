-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2024 a las 22:27:37
-- Versión del servidor: 11.4.4-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `makrosoft`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `copy`
--

CREATE TABLE `copy` (
  `id` int(11) NOT NULL,
  `movieId` int(11) NOT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `createUser` varchar(250) DEFAULT NULL,
  `updateUser` varchar(250) DEFAULT NULL,
  `status` enum('ALQUILADA','DISPONIBLE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `rentalPrice` double NOT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `createUser` varchar(250) DEFAULT NULL,
  `updateUser` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movie`
--

CREATE TABLE `movie` (
  `genreId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  `createUser` varchar(250) DEFAULT NULL,
  `name` varchar(250) NOT NULL,
  `updateUser` varchar(250) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rental`
--

CREATE TABLE `rental` (
  `amountCharged` double DEFAULT NULL,
  `copyId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `dueDate` datetime(6) DEFAULT NULL,
  `rentalDate` datetime(6) DEFAULT NULL,
  `returnDate` datetime(6) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  `createUser` varchar(250) DEFAULT NULL,
  `updateUser` varchar(250) DEFAULT NULL,
  `customer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `copy`
--
ALTER TABLE `copy`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKml8y5r3bhw2g1y2m8pnf8tnws` (`code`),
  ADD KEY `FKnw3uohqp8xffs8ts4ix5jscbi` (`movieId`);

--
-- Indices de la tabla `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKf0cxck09qxl9ea0dhvl6aejis` (`name`);

--
-- Indices de la tabla `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK71w6530ty0ytxg3o720ajx0q1` (`genreId`);

--
-- Indices de la tabla `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmh6fm43j49go9ljxx883qj9hs` (`copyId`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `copy`
--
ALTER TABLE `copy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rental`
--
ALTER TABLE `rental`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `copy`
--
ALTER TABLE `copy`
  ADD CONSTRAINT `FKnw3uohqp8xffs8ts4ix5jscbi` FOREIGN KEY (`movieId`) REFERENCES `movie` (`id`);

--
-- Filtros para la tabla `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `FK71w6530ty0ytxg3o720ajx0q1` FOREIGN KEY (`genreId`) REFERENCES `genre` (`id`);

--
-- Filtros para la tabla `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `FKmh6fm43j49go9ljxx883qj9hs` FOREIGN KEY (`copyId`) REFERENCES `copy` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
