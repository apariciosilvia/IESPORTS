INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (1, 'Desarrollo de Aplicaciones Web', 'DAW');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (2, 'Administración de Sistemas Informáticos en Red', 'ASIR');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (1, 'Técnico en Cuidados Auxiliares de Enfermería', 'TCAE');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (2, 'Gestión Administrativa', 'GA');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (1, 'Mecatrónica Industrial', 'MI');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (2, 'Desarrollo de Aplicaciones Web', 'DAW');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (1, 'Administración de Sistemas Informáticos en Red', 'ASIR');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (2, 'Técnico en Cuidados Auxiliares de Enfermería', 'TCAE');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (1, 'Gestión Administrativa', 'GA');
INSERT INTO `courses` (`year`, `name`, `initials`) VALUES (2, 'Mecatrónica Industrial', 'MI');


INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Administrador');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Anfitrión');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Profesor');
INSERT INTO `roles` (`active`, `name`) VALUES (1, 'Alumno');

-- USUARIOS POR DEFECTO (ADMIN CONTRA: a Y ALUMNO CONTRA: s)
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 1, 'a@a', 'a', '$2a$10$w9wUgqR.juKPsuU8.TPwY.acNWPaIbOWHnVlNwQZpPRqcuQmtS.72');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 5, 4, 's@s', 's', '$2a$10$CqPlbAfH5vfKuCZfhnSQBemQcMyAm1gjq4.m3ItJ/kjmeTHmKGlXW');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 5, 4, 'apariciosilvia8@gmail.com', 'silvia', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');

INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 5, 3, 'laura.prof@example.com', 'Laura Ruiz', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 2, 'ana.host@example.com', 'Ana Pérez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 2, 'mario.host@example.com', 'Mario López', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 1, 1, 'admin@example.com', 'Administrador General', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 6, 4, 'julia.alumno@example.com', 'Julia Medina', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 9, 4, 'david.alumno@example.com', 'David Ramos', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'sofia.alumno@example.com', 'Sofía Navarro', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'lucas.alumno@example.com', 'Lucas Ortega', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'martina.alumno@example.com', 'Martina Castillo', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 4, 'juan.perez@example.com', 'Juan Pérez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'lucia.garcia@example.com', 'Lucía García', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'carlos.lopez@example.com', 'Carlos López', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'alejandra.martinez@example.com', 'Alejandra Martínez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 5, 4, 'diego.sanchez@example.com', 'Diego Sánchez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 6, 4, 'sofia.rodriguez@example.com', 'Sofía Rodríguez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 4, 'miguel.torres@example.com', 'Miguel Torres', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'valentina.ramirez@example.com', 'Valentina Ramírez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 9, 4, 'andres.vazquez@example.com', 'Andrés Vázquez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 4, 'isabela.flores@example.com', 'Isabela Flores', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 4, 'pablo.gomez@example.com', 'Pablo Gómez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'martina.lopez@example.com', 'Martina López', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'alexander.morales@example.com', 'Alexander Morales', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'soledad.villar@example.com', 'Soledad Villar', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'diego.mendoza@example.com', 'Diego Mendoza', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'camila.rivera@example.com', 'Camila Rivera', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 9, 4, 'sebastian.castillo@example.com', 'Sebastián Castillo', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 6, 4, 'sofia.arias@example.com', 'Sofía Arias', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 4, 'rafael.soto@example.com', 'Rafael Soto', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'laura.fernandez@example.com', 'Laura Fernández', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 4, 'daniel.fernandez@example.com', 'Daniel Fernández', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 1, 4, 'elena.morales@example.com', 'Elena Morales', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'javier.torres@example.com', 'Javier Torres', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'marta.ruiz@example.com', 'Marta Ruiz', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'pablo.diaz@example.com', 'Pablo Díaz', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 5, 4, 'natalia.mendez@example.com', 'Natalia Méndez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 9, 4, 'sergio.alvarez@example.com', 'Sergio Álvarez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'carmen.lozano@example.com', 'Carmen Lozano', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'adrian.molina@example.com', 'Adrián Molina', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'eva.romero@example.com', 'Eva Romero', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 1, 4, 'alvaro.iglesias@example.com', 'Álvaro Iglesias', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'irene.martin@example.com', 'Irene Martín', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 7, 4, 'marcos.gil@example.com', 'Marcos Gil', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 1, 4, 'laia.santos@example.com', 'Laia Santos', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'gonzalo.lara@example.com', 'Gonzalo Lara', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'noa.marin@example.com', 'Noa Marín', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 4, 'dario.vargas@example.com', 'Darío Vargas', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 1, 4, 'lucia.ramos@example.com', 'Lucía Ramos', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 4, 4, 'ivan.martinez@example.com', 'Iván Martínez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 2, 4, 'ainhoa.delgado@example.com', 'Ainhoa Delgado', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'david.leon@example.com', 'David León', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 3, 4, 'carla.munoz@example.com', 'Carla Muñoz', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 8, 4, 'roberto.sierra@example.com', 'Roberto Sierra', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 9, 4, 'patricia.cano@example.com', 'Patricia Cano', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `temp_password`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 0, 10, 4, 'hector.reyes@example.com', 'Héctor Reyes', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');

INSERT INTO `sports` (`id`, `name`) VALUES (1, 'Fútbol');
INSERT INTO `sports` (`id`, `name`) VALUES (2, 'Baloncesto');
INSERT INTO `sports` (`id`, `name`) VALUES (3, 'PingPong');
INSERT INTO `sports` (`id`, `name`) VALUES (4, 'Ajedrez');

-- TORNEO PRUEBA EDITAR
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2022/23', 'Torneo prueba PROCESO editar', 'PROCESO');

-- Fútbol Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2022/23', 'Torneo prueba PENDIENTE editar', 'PENDIENTE');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2021/22', 'Liga Juvenil Fútbol', 'PROCESO');
-- Baloncesto Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2021/22', 'Torneo Escolar Baloncesto', 'PENDIENTE');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2023/24', 'Campeonato Nacional Basket', 'PROCESO');
-- Ping Pong Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (3, '2024/25', 'Ping Pong club', 'PROCESO');
-- Ajedrez Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (4, '2021/22', 'Ajedrez Winter', 'FINALIZADO');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (4, '2022/23', 'Liga Ajedrez Indoor', 'PROCESO');


INSERT INTO teams (name) VALUES ('Raimon Eleven');
INSERT INTO teams (name) VALUES ('Zeus Junior High');
INSERT INTO teams (name) VALUES ('Advance Junior High');
INSERT INTO teams (name) VALUES ('Aliea Gakuen');
INSERT INTO teams (name) VALUES ('Hakkoku Junior High');
INSERT INTO teams (name) VALUES ('Teikoku Gakuen');
INSERT INTO teams (name) VALUES ('Kaminari Junior High');
INSERT INTO teams (name) VALUES ('Gendaijin Middle School');
INSERT INTO teams (name) VALUES ('Holy Road Christian School');
INSERT INTO teams (name) VALUES ('Otomari Dormitory');
INSERT INTO teams (name) VALUES ('Itochu Commercial High');
INSERT INTO teams (name) VALUES ('Shirouzu Junior High');
INSERT INTO teams (name) VALUES ('Ichidai Junior High');
INSERT INTO teams (name) VALUES ('Momoyama Middle School');
INSERT INTO teams (name) VALUES ('Marugame Middle School');
INSERT INTO teams (name) VALUES ('Kagemori Middle School');
INSERT INTO teams (name) VALUES ('Musashi Middle School');
INSERT INTO teams (name) VALUES ('Hakuren Phoenix');


-- PARTIDOS PARA TORNEO PRUEBA EDITAR PROCESO

-- Cuartos de final 
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-06-01', 'CUARTOS_FINAL', 1, 1, 4, 2, 3, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-06-28', 'CUARTOS_FINAL', 1, 5, 8, 0, 0, null);
--INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-06-28', 'CUARTOS_FINAL', 1, 5, 8, 2, 4, 8);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES (null, 'CUARTOS_FINAL', 1, 9, 12, 0, 0, null);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-07-01', 'CUARTOS_FINAL', 1, 13, 16, 0, 0, null);


-- PARTIDOS PARA TORNEO PRUEBA EDITAR PROCESO
-- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES (null, 'SEMIFINAL', 2, 9, 12, 0, 0, null);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-07-01', 'SEMIFINAL', 2, 13, 16, 0, 0, null);



-- Octavos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-01', 'OCTAVOS', 5, 1, 2, 64, 58, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-03', 'OCTAVOS', 5, 3, 4, 70, 72, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-04', 'OCTAVOS', 5, 5, 6, 61, 65, 6);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 7, 8, 55, 50, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 9, 10, 55, 50, 10);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 11, 12, 55, 50, 11);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 13, 14, 55, 50, 13);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 15, 16, 55, 50, 15);

-- -- Cuartos de final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 1, 4, 80, 75, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 5, 8, 80, 75, 5);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-08', 'CUARTOS_FINAL', 5, 9, 12, 66, 74, 12);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 13, 16, 80, 75, 13);

-- -- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 5, 1, 12, 89, 85, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 5, 5, 16, 89, 85, 5);

-- -- Final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-12', 'FINAL', 5, 1, 16, 92, 90, 1);


-- Octavos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-01', 'OCTAVOS', 3, 1, 2, 64, 58, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-03', 'OCTAVOS', 3, 3, 4, 70, 72, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-04', 'OCTAVOS', 3, 5, 6, 61, 65, 6);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 3, 7, 8, 55, 50, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 3, 9, 10, 55, 50, 9);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 3, 11, 12, 55, 50, 11);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 3, 13, 14, 55, 50, 13);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 3, 15, 16, 55, 50, 15);

-- Cuartos de final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 3, 1, 4, 80, 75, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 3, 5, 8, 80, 75, 5);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-08', 'CUARTOS_FINAL', 3, 9, 12, 66, 74, 12);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 3, 13, 16, 80, 75, 13);

-- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 3, 1, 12, 89, 85, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 3, 5, 16, 89, 85, 5);

-- Final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-12', 'FINAL', 3, 1, 16, 92, 90, 1);


-- Octavos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-01', 'OCTAVOS', 7, 1, 2, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-03', 'OCTAVOS', 7, 3, 4, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-04', 'OCTAVOS', 7, 5, 6, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 7, 7, 8, 54, 32, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 9, 10, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 11, 12, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 13, 14, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 15, 16, 0, 0);



-- Asignar personas a equipos
INSERT INTO players_team (player_id, team_id) VALUES (3, 1);
INSERT INTO players_team (player_id, team_id) VALUES (4, 1);
INSERT INTO players_team (player_id, team_id) VALUES (5, 1);
INSERT INTO players_team (player_id, team_id) VALUES (6, 1);
INSERT INTO players_team (player_id, team_id) VALUES (7, 1);
INSERT INTO players_team (player_id, team_id) VALUES (8, 1);
INSERT INTO players_team (player_id, team_id) VALUES (9, 1);
INSERT INTO players_team (player_id, team_id) VALUES (10, 1);

INSERT INTO players_team (player_id, team_id) VALUES (11, 2);
INSERT INTO players_team (player_id, team_id) VALUES (12, 2);
INSERT INTO players_team (player_id, team_id) VALUES (13, 2);
INSERT INTO players_team (player_id, team_id) VALUES (14, 2);
INSERT INTO players_team (player_id, team_id) VALUES (15, 2);
INSERT INTO players_team (player_id, team_id) VALUES (16, 2);
INSERT INTO players_team (player_id, team_id) VALUES (17, 2);
INSERT INTO players_team (player_id, team_id) VALUES (18, 2);
INSERT INTO players_team (player_id, team_id) VALUES (19, 2);
INSERT INTO players_team (player_id, team_id) VALUES (20, 2);

INSERT INTO players_team (player_id, team_id) VALUES (21, 3);
INSERT INTO players_team (player_id, team_id) VALUES (22, 3);
INSERT INTO players_team (player_id, team_id) VALUES (23, 3);
INSERT INTO players_team (player_id, team_id) VALUES (24, 3);

INSERT INTO players_team (player_id, team_id) VALUES (25, 4);
INSERT INTO players_team (player_id, team_id) VALUES (26, 4);
INSERT INTO players_team (player_id, team_id) VALUES (27, 4);
INSERT INTO players_team (player_id, team_id) VALUES (28, 4);
INSERT INTO players_team (player_id, team_id) VALUES (29, 4);
INSERT INTO players_team (player_id, team_id) VALUES (30, 4);
INSERT INTO players_team (player_id, team_id) VALUES (31, 4);

INSERT INTO players_team (player_id, team_id) VALUES (32, 5);
INSERT INTO players_team (player_id, team_id) VALUES (33, 5);

INSERT INTO players_team (player_id, team_id) VALUES (34, 6);
INSERT INTO players_team (player_id, team_id) VALUES (35, 6);
INSERT INTO players_team (player_id, team_id) VALUES (36, 6);

INSERT INTO players_team (player_id, team_id) VALUES (37, 7);

INSERT INTO players_team (player_id, team_id) VALUES (38, 8);
INSERT INTO players_team (player_id, team_id) VALUES (39, 8);
INSERT INTO players_team (player_id, team_id) VALUES (40, 8);
INSERT INTO players_team (player_id, team_id) VALUES (41, 8);
INSERT INTO players_team (player_id, team_id) VALUES (42, 8);
INSERT INTO players_team (player_id, team_id) VALUES (43, 8);
INSERT INTO players_team (player_id, team_id) VALUES (44, 8);
INSERT INTO players_team (player_id, team_id) VALUES (45, 8);
INSERT INTO players_team (player_id, team_id) VALUES (46, 8);
INSERT INTO players_team (player_id, team_id) VALUES (47, 8);
INSERT INTO players_team (player_id, team_id) VALUES (48, 8);

INSERT INTO players_team (player_id, team_id) VALUES (49, 9);
INSERT INTO players_team (player_id, team_id) VALUES (50, 9);
INSERT INTO players_team (player_id, team_id) VALUES (51, 9);
INSERT INTO players_team (player_id, team_id) VALUES (52, 9);
INSERT INTO players_team (player_id, team_id) VALUES (53, 9);
INSERT INTO players_team (player_id, team_id) VALUES (54, 9);
INSERT INTO players_team (player_id, team_id) VALUES (55, 9);
INSERT INTO players_team (player_id, team_id) VALUES (56, 9);

INSERT INTO players_team (player_id, team_id) VALUES (41, 10);
INSERT INTO players_team (player_id, team_id) VALUES (42, 10);
INSERT INTO players_team (player_id, team_id) VALUES (43, 10);
INSERT INTO players_team (player_id, team_id) VALUES (44, 10);

INSERT INTO players_team (player_id, team_id) VALUES (25, 11);
INSERT INTO players_team (player_id, team_id) VALUES (26, 11);
INSERT INTO players_team (player_id, team_id) VALUES (27, 11);
INSERT INTO players_team (player_id, team_id) VALUES (28, 11);
INSERT INTO players_team (player_id, team_id) VALUES (29, 11);
INSERT INTO players_team (player_id, team_id) VALUES (30, 11);
INSERT INTO players_team (player_id, team_id) VALUES (31, 11);




