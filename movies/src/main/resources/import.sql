INSERT INTO `genre` (`id`, `rentalPrice`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`) VALUES (NULL, 5000, '2024-12-10 17:46:35', 'admin', 'Comedia', '2024-12-10 17:46:35', NULL);
INSERT INTO `genre` (`id`, `rentalPrice`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`) VALUES (NULL, 6000, '2024-12-10 17:46:35', 'admin', 'Romance', '2024-12-10 17:46:35', NULL);
INSERT INTO `genre` (`id`, `rentalPrice`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`) VALUES (NULL, 7000, '2024-12-10 17:46:35', 'admin', 'Acción', '2024-12-10 17:46:35', NULL);

INSERT INTO `movie` (`id`, `genreId`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`, `description`) VALUES (NULL, 1, '2024-12-10 17:46:35', 'admin', 'La Fiesta', '2024-12-10 17:46:35', NULL, 'Una película de comedia divertida sobre una fiesta sorpresa.');
INSERT INTO `movie` (`id`, `genreId`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`, `description`) VALUES (NULL, 2, '2024-12-10 17:46:35', 'admin', 'El Amor Imposible', '2024-12-10 17:46:35', NULL, 'Un romance entre dos personas de mundos diferentes.');
INSERT INTO `movie` (`id`, `genreId`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`, `description`) VALUES (NULL, 3, '2024-12-10 17:46:35', 'admin', 'La Aventura Extrema', '2024-12-10 17:46:35', NULL, 'Un thriller de acción con una peligrosa misión.');
INSERT INTO `movie` (`id`, `genreId`, `createTime`, `createUser`, `name`, `updateTime`, `updateUser`, `description`) VALUES (NULL, 3, '2024-12-10 17:46:35', 'admin', 'Cars', '2024-12-10 17:46:35', NULL, 'Un thriller de acción con una peligrosa misión.');

INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 1, '2024-12-10 17:46:35', 'admin', 'COPY001', '2024-12-10 17:46:35', NULL, 'ALQUILADA');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 1, '2024-12-10 17:46:35', 'admin', 'COPY002', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 1, '2024-12-10 17:46:35', 'admin', 'COPY003', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 2, '2024-12-10 17:46:35', 'admin', 'COPY004', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 2, '2024-12-10 17:46:35', 'admin', 'COPY005', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 2, '2024-12-10 17:46:35', 'admin', 'COPY006', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 3, '2024-12-10 17:46:35', 'admin', 'COPY007', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 3, '2024-12-10 17:46:35', 'admin', 'COPY008', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');
INSERT INTO `copy` (`id`, `movieId`, `createTime`, `createUser`, `code`, `updateTime`, `updateUser`, `status`) VALUES (NULL, 3, '2024-12-10 17:46:35', 'admin', 'COPY009', '2024-12-10 17:46:35', NULL, 'DISPONIBLE');

INSERT INTO `rental` (`id`, `amountCharged`, `copyId`, `createTime`, `dueDate`, `rentalDate`, `returnDate`, `updateTime`, `createUser`, `updateUser`, `customer`) VALUES (NULL, 5000, 1, '2024-12-10 17:46:35', '2024-12-17 17:46:35', '2024-12-10 17:46:35', NULL, '2024-12-10 17:46:35', 'admin', NULL, 'Juan Pérez');
