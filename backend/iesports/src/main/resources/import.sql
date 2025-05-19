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
INSERT INTO `sports` (`id`, `name`) VALUES (4, 'Tenis');

-- Fútbol Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2022/23', 'Copa Nacional de Fútbol', 'PROCESO');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (1, '2021/22', 'Liga Juvenil Fútbol', 'PROCESO');
-- Baloncesto Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2022/23', 'Torneo Escolar Baloncesto', 'PENDIENTE');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (2, '2023/24', 'Campeonato Nacional Basket', 'PROCESO');
-- Hockey Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (3, '2021/22', 'Hockey Winter Cup', 'FINALIZADO');
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (3, '2022/23', 'Liga Hockey Indoor', 'PROCESO');
-- Tenis Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`) VALUES (4, '2024/25', 'Tennis club', 'PROCESO');


INSERT INTO teams (name) VALUES ('equipo 1');
INSERT INTO teams (name) VALUES ('equipo 2');
INSERT INTO teams (name) VALUES ('equipo 3');
INSERT INTO teams (name) VALUES ('equipo 4');

INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-01', 'FASE_GRUPOS', 1, 1, 2, 64, 58, 1);
-- Más partidos de fase de grupos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-03', 'FASE_GRUPOS', 1, 3, 4, 70, 72, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-04', 'FASE_GRUPOS', 6, 1, 3, 61, 65, 3);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'FASE_GRUPOS', 1, 2, 4, 55, 50, 2);

-- Cuartos de final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 1, 1, 4, 80, 75, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-08', 'CUARTOS_FINAL', 1, 2, 3, 66, 74, 3);

-- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 1, 1, 3, 89, 85, 1);

-- Final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-12', 'FINAL', 4, 1, 3, 92, 90, 1);

INSERT INTO players_team (player_id, team_id) VALUES (5, 1);  -- Julia en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (6, 2);  -- David en equipo 2
INSERT INTO players_team (player_id, team_id) VALUES (8, 3);  -- Lucas en equipo 3
INSERT INTO players_team (player_id, team_id) VALUES (7, 1);  -- Sofía en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (9, 1);  -- Martina en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (5, 2);  -- Julia también en equipo 2
INSERT INTO players_team (player_id, team_id) VALUES (6, 3);  -- David también en equipo 3
INSERT INTO players_team (player_id, team_id) VALUES (9, 4);  -- Martina en equipo 4


