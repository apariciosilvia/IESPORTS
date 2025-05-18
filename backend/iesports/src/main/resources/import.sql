INSERT INTO `courses` (`year`, `name`) VALUES (1, 'Desarrollo de Aplicaciones Web');
INSERT INTO `courses` (`year`, `name`) VALUES (2, 'Administración de Sistemas Informáticos en Red');
INSERT INTO `courses` (`year`, `name`) VALUES (1, 'Técnico en Cuidados Auxiliares de Enfermería');
INSERT INTO `courses` (`year`, `name`) VALUES (2, 'Gestión Administrativa');
INSERT INTO `courses` (`year`, `name`) VALUES (1, 'Mecatrónica Industrial');
INSERT INTO `courses` (`year`, `name`) VALUES (2, 'Desarrollo de Aplicaciones Web');
INSERT INTO `courses` (`year`, `name`) VALUES (1, 'Administración de Sistemas Informáticos en Red');
INSERT INTO `courses` (`year`, `name`) VALUES (2, 'Técnico en Cuidados Auxiliares de Enfermería');
INSERT INTO `courses` (`year`, `name`) VALUES (1, 'Gestión Administrativa');
INSERT INTO `courses` (`year`, `name`) VALUES (2, 'Mecatrónica Industrial');

INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Administrador');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Anfitrión');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Profesor');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Alumno');

INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 5, 3, 'laura.prof@example.com', 'Laura Ruiz', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 7, 2, 'ana.host@example.com', 'Ana Pérez', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 2, 2, 'mario.host@example.com', 'Mario López', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 1, 1, 'admin@example.com', 'Administrador General', 'admin123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 6, 4, 'julia.alumno@example.com', 'Julia Medina', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 9, 4, 'david.alumno@example.com', 'David Ramos', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 4, 4, 'sofia.alumno@example.com', 'Sofía Navarro', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 8, 4, 'lucas.alumno@example.com', 'Lucas Ortega', 'pass123');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 10, 4, 'martina.alumno@example.com', 'Martina Castillo', 'pass123');

INSERT INTO `sports` (`id`, `name`) VALUES (1, 'Fútbol');
INSERT INTO `sports` (`id`, `name`) VALUES (2, 'Baloncesto');
INSERT INTO `sports` (`id`, `name`) VALUES (3, 'Hockey');

-- Fútbol Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2022/23', 'Copa Nacional de Fútbol', 'Programado');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2021/22', 'Liga Juvenil Fútbol', 'Finalizado');
-- Baloncesto Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2022/23', 'Torneo Escolar Baloncesto', 'En curso');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2023/24', 'Campeonato Nacional Basket', 'Programado');
-- Hockey Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (3, '2021/22', 'Hockey Winter Cup', 'Finalizado');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (3, '2022/23', 'Liga Hockey Indoor', 'En curso');


