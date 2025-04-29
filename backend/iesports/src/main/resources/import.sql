INSERT INTO `cursos` (`year`, `nombre`) VALUES (1, 'Desarrollo de Aplicaciones Web');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (2, 'Administración de Sistemas Informáticos en Red');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (1, 'Técnico en Cuidados Auxiliares de Enfermería');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (2, 'Gestión Administrativa');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (1, 'Mecatrónica Industrial');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (2, 'Desarrollo de Aplicaciones Web');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (1, 'Administración de Sistemas Informáticos en Red');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (2, 'Técnico en Cuidados Auxiliares de Enfermería');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (1, 'Gestión Administrativa');
INSERT INTO `cursos` (`year`, `nombre`) VALUES (2, 'Mecatrónica Industrial');

INSERT INTO `roles` (`activo`, `nombre`) VALUES (1, 'Administrador');
INSERT INTO `roles` (`activo`, `nombre`) VALUES (1, 'Anfitrión');
INSERT INTO `roles` (`activo`, `nombre`) VALUES (1, 'Profesor');
INSERT INTO `roles` (`activo`, `nombre`) VALUES (1, 'Alumno');

INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 5, 3, 'laura.prof@example.com', 'Laura Ruiz', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 7, 2, 'ana.host@example.com', 'Ana Pérez', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 2, 2, 'mario.host@example.com', 'Mario López', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 1, 1, 'admin@example.com', 'Administrador General', 'admin123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 6, 4, 'julia.alumno@example.com', 'Julia Medina', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 9, 4, 'david.alumno@example.com', 'David Ramos', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 4, 4, 'sofia.alumno@example.com', 'Sofía Navarro', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 8, 4, 'lucas.alumno@example.com', 'Lucas Ortega', 'pass123');
INSERT INTO `personas` (`activo`, `curso_id`, `rol_id`, `email`, `nombre`, `password`) VALUES (1, 10, 4, 'martina.alumno@example.com', 'Martina Castillo', 'pass123');




