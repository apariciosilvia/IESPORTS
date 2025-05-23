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

INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 5, 3, 'laura.prof@example.com', 'Laura Ruiz', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 7, 2, 'ana.host@example.com', 'Ana Pérez', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 2, 2, 'mario.host@example.com', 'Mario López', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 1, 1, 'admin@example.com', 'Administrador General', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 6, 4, 'julia.alumno@example.com', 'Julia Medina', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 9, 4, 'david.alumno@example.com', 'David Ramos', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 4, 4, 'sofia.alumno@example.com', 'Sofía Navarro', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 8, 4, 'lucas.alumno@example.com', 'Lucas Ortega', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');
INSERT INTO `persons` (`active`, `course_id`, `role_id`, `email`, `name`, `password`) VALUES (1, 10, 4, 'martina.alumno@example.com', 'Martina Castillo', '$2a$10$GE7GA/T2MOA0DEbDskLih.daAuvRkC3t.7nZbpKTSRKYqvTBEX7Ey');

INSERT INTO `sports` (`id`, `name`) VALUES (1, 'Fútbol');
INSERT INTO `sports` (`id`, `name`) VALUES (2, 'Baloncesto');
INSERT INTO `sports` (`id`, `name`) VALUES (3, 'PingPong');
INSERT INTO `sports` (`id`, `name`) VALUES (4, 'Ajedrez');

-- Fútbol Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (1, '2022/23', 'Copa Nacional de Fútbol', 'PROCESO', 6, 0);
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (1, '2021/22', 'Liga Juvenil Fútbol', 'PROCESO', 8, 0);
-- Baloncesto Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (2, '2021/22', 'Torneo Escolar Baloncesto', 'PENDIENTE', 10, 0);
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (2, '2023/24', 'Campeonato Nacional Basket', 'PROCESO', 12, 0);
-- Ping Pong Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (3, '2024/25', 'Ping Pong club', 'PROCESO', 6, 0);
-- Ajedrez Tournaments
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (4, '2021/22', 'Ajedrez Winter', 'FINALIZADO', 4, 0);
INSERT INTO `tournaments` (`sport_id`, `date`, `name`, `state`, `max_teams`,`current_teams`) VALUES (4, '2022/23', 'Liga Ajedrez Indoor', 'PROCESO', 4, 0);



INSERT INTO teams (name) VALUES ('equipo 1');
INSERT INTO teams (name) VALUES ('equipo 2');
INSERT INTO teams (name) VALUES ('equipo 3');
INSERT INTO teams (name) VALUES ('equipo 4');
INSERT INTO teams (name) VALUES ('equipo 5');
INSERT INTO teams (name) VALUES ('equipo 6');
INSERT INTO teams (name) VALUES ('equipo 7');
INSERT INTO teams (name) VALUES ('equipo 8');
INSERT INTO teams (name) VALUES ('equipo 9');
INSERT INTO teams (name) VALUES ('equipo 10');
INSERT INTO teams (name) VALUES ('equipo 11');
INSERT INTO teams (name) VALUES ('equipo 12');
INSERT INTO teams (name) VALUES ('equipo 13');
INSERT INTO teams (name) VALUES ('equipo 14');
INSERT INTO teams (name) VALUES ('equipo 15');
INSERT INTO teams (name) VALUES ('equipo 16');


-- Fase de grupos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-01', 'OCTAVOS', 5, 1, 2, 64, 58, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-03', 'OCTAVOS', 5, 3, 4, 70, 72, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-04', 'OCTAVOS', 5, 5, 6, 61, 65, 6);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 7, 8, 55, 50, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 9, 10, 55, 50, 10);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 11, 12, 55, 50, 11);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 13, 14, 55, 50, 13);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 5, 15, 16, 55, 50, 15);

-- Cuartos de final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 1, 4, 80, 75, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 5, 8, 80, 75, 5);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-08', 'CUARTOS_FINAL', 5, 9, 12, 66, 74, 12);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 5, 13, 16, 80, 75, 13);

-- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 5, 1, 12, 89, 85, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 5, 5, 16, 89, 85, 5);

-- Final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-12', 'FINAL', 5, 1, 16, 92, 90, 1);

-- Fase de grupos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-01', 'OCTAVOS', 1, 1, 2, 64, 58, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-03', 'OCTAVOS', 1, 3, 4, 70, 72, 4);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-04', 'OCTAVOS', 1, 5, 6, 61, 65, 6);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 1, 7, 8, 55, 50, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 1, 9, 10, 55, 50, 10);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 1, 11, 12, 55, 50, 11);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 1, 13, 14, 55, 50, 13);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 1, 15, 16, 55, 50, 15);

-- Cuartos de final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 1, 1, 4, 80, 75, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 1, 5, 8, 80, 75, 5);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-08', 'CUARTOS_FINAL', 1, 9, 12, 66, 74, 12);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-07', 'CUARTOS_FINAL', 1, 13, 16, 80, 75, 13);

-- Semifinal
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 1, 1, 12, 89, 85, 1);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-10', 'SEMIFINAL', 1, 5, 16, 89, 85, 5);

-- Final
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-12', 'FINAL', 1, 1, 16, 92, 90, 1);


-- Fase de grupos
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


-- Fase de grupos
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-01', 'OCTAVOS', 7, 1, 2, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-03', 'OCTAVOS', 7, 3, 4, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-04', 'OCTAVOS', 7, 5, 6, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`, `winner_team_id`) VALUES ('2025-05-05', 'OCTAVOS', 7, 7, 8, 54, 32, 7);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 9, 10, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 11, 12, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 13, 14, 0, 0);
INSERT INTO `matches` (`date`, `round`, `tournament_id`, `team1_id`, `team2_id`, `points_team1`, `points_team2`) VALUES ('2025-05-05', 'OCTAVOS', 7, 15, 16, 0, 0);



INSERT INTO players_team (player_id, team_id) VALUES (5, 1);  -- Julia en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (6, 2);  -- David en equipo 2
INSERT INTO players_team (player_id, team_id) VALUES (8, 3);  -- Lucas en equipo 3
INSERT INTO players_team (player_id, team_id) VALUES (7, 1);  -- Sofía en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (9, 1);  -- Martina en equipo 1
INSERT INTO players_team (player_id, team_id) VALUES (5, 2);  -- Julia también en equipo 2
INSERT INTO players_team (player_id, team_id) VALUES (6, 3);  -- David también en equipo 3
INSERT INTO players_team (player_id, team_id) VALUES (9, 4);  -- Martina en equipo 4


