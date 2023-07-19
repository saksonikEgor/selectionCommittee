USE SelectionCommitteeDataBase;


DROP TABLE IF EXISTS `program_subject`;
DROP TABLE IF EXISTS `enrollee_achievement`;
DROP TABLE IF EXISTS `program_enrollee`;
DROP TABLE IF EXISTS `enrollee_subject`;
DROP TABLE IF EXISTS `exam_min_result`;
DROP TABLE IF EXISTS `subject`;
DROP TABLE IF EXISTS `enrollee`;
DROP TABLE IF EXISTS `program`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `achievement`;


-- achievement: table
CREATE TABLE `achievement`
(
    `achievement_id`   int NOT NULL AUTO_INCREMENT,
    `name_achievement` varchar(30) DEFAULT NULL,
    `bonus`            int         DEFAULT NULL,
    PRIMARY KEY (`achievement_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `achievement` (achievement_id, name_achievement, bonus)
VALUES (1, 'Золотая медаль', 5),
       (2, 'Серебряная медаль', 3),
       (3, 'Золотой значок ГТО', 3),
       (4, 'Серебряный значок ГТО', 1);


-- department: table
CREATE TABLE `department`
(
    `department_id`   int NOT NULL AUTO_INCREMENT,
    `name_department` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`department_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `department` (department_id, name_department)
VALUES (3, 'Аэрокосмических приборов и систем'),
       (5, 'Киберфизических систем'),
       (6, 'Информационных технологий и программирования'),
       (7, 'Гуманитарный'),
       (9, 'Фундаментальной подготовки и технологических инноваций'),
       (10, 'Искусств');


-- program: table
CREATE TABLE `program`
(
    `program_id`    int        NOT NULL AUTO_INCREMENT,
    `name_program`  varchar(100) DEFAULT NULL,
    `department_id` int          DEFAULT NULL,
    `plan`          int          DEFAULT NULL,
    `need_test`     tinyint(1) NOT NULL,
    PRIMARY KEY (`program_id`),
    KEY `department_id` (`department_id`),
    CONSTRAINT `program_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 63
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `program` (program_id, name_program, department_id, plan, need_test)
VALUES (6, 'Приборостроение', 3, 5, 0),
       (7, 'Технология транспортных процессов', 3, 5, 0),
       (8, 'Системный анализ и управление', 3, 5, 0),
       (22, 'Автоматизация технологических процессов и производ', 5, 5, 0),
       (27, 'Информационная безопасность', 5, 5, 0),
       (28, 'Прикладная информатика', 6, 5, 0),
       (30, 'Математическое обеспечение и администрирование инф', 6, 5, 0),
       (33, 'Гостинничное дело', 7, 7, 0),
       (34, 'Культурология', 7, 7, 0),
       (35, 'Реклама и связи с общественностью', 7, 7, 0),
       (46, 'Биотехнология', 9, 9, 0),
       (50, 'Инноватика', 9, 9, 0),
       (51, 'Стандартизация и метрология', 9, 9, 0),
       (55, 'Реставрация', 10, 10, 1),
       (56, 'Музыкально-инструментальное искусство', 10, 10, 1);
-- No native definition for element: department_id (index)


-- enrollee: table
CREATE TABLE `enrollee`
(
    `enrollee_id`        int NOT NULL AUTO_INCREMENT,
    `name_enrollee`      varchar(50)  DEFAULT NULL,
    `password`           varchar(60)  DEFAULT NULL,
    `email`              varchar(320) DEFAULT NULL,
    `passport_number`    int          DEFAULT NULL,
    `certificate_number` int          DEFAULT NULL,
    `statement_number`   int          DEFAULT NULL,
    `photo_number`       int          DEFAULT NULL,
    `benefit_number`     int          DEFAULT NULL,
    `army_number`        int          DEFAULT NULL,
    `medition_number`    int          DEFAULT NULL,
    `phone_number`       varchar(11)  DEFAULT NULL,
    PRIMARY KEY (`enrollee_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 54
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `enrollee` (enrollee_id, name_enrollee, password, email, passport_number, certificate_number,
                        statement_number, photo_number, benefit_number, army_number, medition_number, phone_number)
VALUES (1, 'Баранов Павел Дмитриевич', '12345', 'baranov@mail.ru', 1, 1, 1, 1, 1, 1, 1, '89140968755'),
       (2, 'Абрамова Екатерина Дмитриевна', '323', 'abr@mail.ru', 2, 2, 2, 2, 2, 2, 2, '89148675767'),
       (3, 'Семенов Иван Вадимович', '213r', 'semenov@mail.ru', 3, 3, 3, 3, 3, 3, 3, '89146534235'),
       (4, 'Яковлева Галина Дмитриевна', 'f3f34', 'yakovlev@mail.ru', 4, 4, 4, 4, 4, 4, 4, '89140687581'),
       (5, 'Попов Илья Михайлович', 'iu456ui', 'popov@mail.ru', 5, 5, 5, 5, 5, 5, 5, '89145241342'),
       (6, 'Степанова Дарья Сергеевна', 'f53g', 'stepanoveDaria@mail.ru', 6, 6, 6, 6, 6, 6, 6, '89142534132'),
       (8, 'Иванов Иван Иванович', '123', 'test@mail.ru', 8, 0, 8, 8, 0, 8, 8, '89140798444'),
       (10, 'Павлов Павел Павлович', 'qwe', 'q@mail.ri', 0, 0, 0, 0, 0, 0, 0, '89136453444'),
       (20, 'Михайлов Руслан Алексеевич', 'nugi4', 'mkhalov@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89159807987'),
       (21, 'Ларионов Севастьян Валентинович', 'g64', 'larionav@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89165408778'),
       (22, 'Сысоев Анатолий Геннадиевич', 'bht5', 'sysev@maiol.ru', 0, 0, 0, 0, 0, 0, 0, '89155465309'),
       (23, 'Лебедев Панкратий Аркадьевич', 'g734f5', 'lebedev@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89158675443'),
       (24, 'Беляков Ефим Геннадиевич', 'f56h868', 'belyakov@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89159781423'),
       (25, 'Петров Игорь Пантелеймонович', 'j657gf', 'petrov@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89157114234'),
       (26, 'Артемьев Владлен Ильяович', 'gere', 'artemyev@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89157243545'),
       (27, 'Архипов Феликс Дмитриевич', 'gwerh', 'arhupov@mail.ri', 0, 0, 0, 0, 0, 0, 0, '89150128363'),
       (28, 'Крюков Мстислав Матвеевич', 'hrtwgv', 'rh33@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89156142153'),
       (29, 'Сорокин Кондратий Ильяович', 'ewfgv', 'sorokin@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89150255555'),
       (30, 'Лаврентьев Эрик Миронович', 'gerhtyj', 'lavrentev@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89139786987'),
       (31, 'Алексеева Патрисия Витальевна', 'grehrtd', 'alekseeva@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89138675676'),
       (32, 'Силина Октябрина Артёмовна', 'grhtyj', 'silana@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89137566536'),
       (33, 'Носкова Виолетта Георгиевна', 'fgeqf', 'nosok@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89139780048'),
       (34, 'Щукина Ангелина Дмитриевна', 'fegrege', 'hsukina@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89137587436'),
       (35, 'Шарова Августа Николаевна', 'f23r2', 'sharova@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89138765638'),
       (36, 'Сорокина Альбина Кирилловна', '8765', 'sokokokok@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89139547213'),
       (37, 'Зайцева Маргарита Владиславовна', '34vk78', 'zqqui@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89137667532'),
       (38, 'Васильева Иоланта Гордеевна', 'yy53', 'vasilieva@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89137648233'),
       (39, 'Соколовченко Юрий Александрович', '9iiur4', 'sokolovchenko@mail.ru', 0, 0, 0, 0, 0, 0, 0, '89136571091'),
       (40, 'Юдина Ралина Федосеевна', '321rfer', 'yudina@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89126347687'),
       (41, 'Лапина Стефания Эльдаровна', 'fv24v2', 'lipina@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '81927834816'),
       (42, 'Моисеева Мэри Вениаминовна', 'v3h56vh3', 'moyleeva@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89120657098'),
       (43, 'Ковалёв Валентин Ильяович', 'm5784635', 'kov@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89127812354'),
       (44, 'Макаров Павел Владленович', 'vy45ct', 'makarov@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '81928974111'),
       (45, 'Силин Антон Рубенович', 'bh5v', 'sily@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89118916274'),
       (46, 'Попов Родион Богданович', 'bjh5', 'poppppoppo@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89111348713'),
       (47, 'Калинин Велор Михайлович', 'v3u56u356', 'kalininpoe22@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89128914211'),
       (48, 'Терентьев Глеб Федосеевич', 'v356', 'terrror@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89110080909'),
       (49, 'Мухина Лада Сергеевна', 'bu6n76788', 'muzhh@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89111641167'),
       (50, 'Федотова Александра Романовна', '87667574643', 'phf@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89120090000'),
       (51, 'Дементьева Аэлита Константиновна', '6v23t', 'dementyeva@yandex.ru', 0, 0, 0, 0, 0, 0, 0, '89111271378'),
       (52, 'Батталова Карина Олеговна', 'hui', 'bkao03@mail.ru', 0, 52, 0, 0, 0, 52, 0, '89279620868'),
       (53, 'Иванов Ян Иванович', '345lp2', 'iv671anovv@gmail.ru', 0, 0, 0, 0, 0, 0, 0, '89157639004');


-- subject: table
CREATE TABLE `subject`
(
    `subject_id`   int NOT NULL AUTO_INCREMENT,
    `name_subject` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`subject_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `subject` (subject_id, name_subject)
VALUES (1, 'Русский язык'),
       (2, 'Математика'),
       (3, 'Физика'),
       (4, 'Информатика'),
       (5, 'Химия'),
       (6, 'Биология'),
       (7, 'Литература'),
       (8, 'География'),
       (9, 'История'),
       (10, 'Обществознание'),
       (11, 'Английский язык');


-- exam_min_result: table
CREATE TABLE `exam_min_result`
(
    `exam_min_result_id` int NOT NULL AUTO_INCREMENT,
    `program_id`         int DEFAULT NULL,
    `min_result`         int DEFAULT NULL,
    PRIMARY KEY (`exam_min_result_id`),
    KEY `program_id` (`program_id`),
    CONSTRAINT `exam_min_result_ibfk_1` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `exam_min_result` (exam_min_result_id, program_id, min_result)
VALUES (2, 55, 25),
       (3, 56, 25);
-- No native definition for element: program_id (index)


-- enrollee_achievement: table
CREATE TABLE `enrollee_achievement`
(
    `enrollee_achiev_id` int NOT NULL AUTO_INCREMENT,
    `enrollee_id`        int DEFAULT NULL,
    `achievement_id`     int DEFAULT NULL,
    PRIMARY KEY (`enrollee_achiev_id`),
    KEY `enrollee_id` (`enrollee_id`),
    KEY `achievement_id` (`achievement_id`),
    CONSTRAINT `enrollee_achievement_ibfk_1` FOREIGN KEY (`enrollee_id`) REFERENCES `enrollee` (`enrollee_id`) ON DELETE CASCADE,
    CONSTRAINT `enrollee_achievement_ibfk_2` FOREIGN KEY (`achievement_id`) REFERENCES `achievement` (`achievement_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 49
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `enrollee_achievement` (enrollee_achiev_id, enrollee_id, achievement_id)
VALUES (1, 1, 2),
       (2, 1, 3),
       (3, 3, 1),
       (4, 4, 4),
       (5, 5, 1),
       (6, 5, 3),
       (21, 10, 1),
       (22, 20, 3),
       (23, 21, 2),
       (24, 24, 3),
       (25, 25, 1),
       (26, 26, 1),
       (27, 26, 3),
       (28, 27, 1),
       (29, 27, 3),
       (30, 28, 2),
       (31, 29, 2),
       (32, 33, 2),
       (33, 33, 3),
       (34, 37, 3),
       (35, 38, 2),
       (36, 38, 4),
       (37, 40, 1),
       (38, 40, 3),
       (39, 43, 3),
       (40, 45, 2),
       (41, 45, 3),
       (42, 46, 3),
       (43, 47, 4),
       (44, 50, 1),
       (45, 51, 1),
       (46, 52, 1),
       (47, 52, 3),
       (48, 2, 3);
-- No native definition for element: enrollee_id (index)

-- No native definition for element: achievement_id (index)


-- enrollee_subject: table
CREATE TABLE `enrollee_subject`
(
    `enrollee_subject_id` int NOT NULL AUTO_INCREMENT,
    `enrollee_id`         int DEFAULT NULL,
    `subject_id`          int DEFAULT NULL,
    `result`              int DEFAULT NULL,
    PRIMARY KEY (`enrollee_subject_id`),
    KEY `enrollee_id` (`enrollee_id`),
    KEY `subject_id` (`subject_id`),
    CONSTRAINT `enrollee_subject_ibfk_1` FOREIGN KEY (`enrollee_id`) REFERENCES `enrollee` (`enrollee_id`) ON DELETE CASCADE,
    CONSTRAINT `enrollee_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 237
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `enrollee_subject` (enrollee_subject_id, enrollee_id, subject_id, result)
VALUES (1, 1, 1, 68),
       (2, 1, 2, 70),
       (3, 1, 3, 41),
       (4, 1, 4, 75),
       (8, 3, 1, 85),
       (9, 3, 2, 67),
       (10, 3, 3, 90),
       (11, 3, 4, 78),
       (12, 4, 1, 82),
       (13, 4, 2, 86),
       (14, 4, 3, 70),
       (15, 5, 1, 65),
       (16, 5, 2, 67),
       (17, 5, 3, 60),
       (18, 6, 1, 90),
       (19, 6, 2, 92),
       (20, 6, 3, 88),
       (21, 6, 4, 94),
       (83, 8, 1, 54),
       (84, 8, 2, 38),
       (85, 10, 1, 65),
       (86, 10, 2, 66),
       (87, 10, 4, 86),
       (88, 20, 1, 68),
       (89, 20, 2, 38),
       (90, 20, 3, 58),
       (91, 20, 4, 86),
       (92, 21, 1, 68),
       (93, 21, 2, 92),
       (94, 21, 3, 38),
       (95, 21, 9, 86),
       (96, 22, 1, 58),
       (97, 22, 2, 92),
       (98, 22, 3, 68),
       (99, 22, 9, 68),
       (100, 23, 1, 68),
       (101, 23, 2, 68),
       (102, 23, 3, 68),
       (103, 23, 7, 86),
       (104, 24, 1, 58),
       (105, 24, 2, 87),
       (106, 24, 4, 68),
       (107, 24, 7, 92),
       (108, 25, 1, 86),
       (109, 25, 2, 38),
       (110, 25, 4, 58),
       (111, 25, 7, 87),
       (112, 26, 1, 68),
       (113, 26, 2, 68),
       (114, 26, 4, 68),
       (115, 26, 7, 68),
       (116, 27, 1, 90),
       (117, 27, 2, 58),
       (118, 27, 4, 90),
       (119, 27, 10, 87),
       (120, 28, 1, 68),
       (121, 28, 2, 66),
       (122, 28, 7, 92),
       (123, 28, 10, 68),
       (124, 29, 1, 90),
       (125, 29, 2, 87),
       (126, 29, 7, 58),
       (127, 29, 10, 92),
       (128, 30, 1, 38),
       (129, 30, 2, 90),
       (130, 30, 7, 87),
       (131, 30, 11, 38),
       (132, 31, 1, 48),
       (133, 31, 2, 58),
       (134, 31, 7, 66),
       (135, 31, 11, 48),
       (136, 32, 1, 68),
       (137, 32, 2, 87),
       (138, 32, 7, 68),
       (139, 32, 11, 66),
       (140, 33, 1, 48),
       (141, 33, 2, 68),
       (142, 33, 9, 68),
       (143, 33, 6, 54),
       (144, 34, 1, 58),
       (145, 34, 2, 58),
       (146, 34, 9, 72),
       (147, 34, 6, 87),
       (148, 35, 1, 38),
       (149, 35, 2, 54),
       (150, 35, 9, 68),
       (151, 35, 6, 58),
       (152, 36, 1, 66),
       (153, 36, 2, 68),
       (154, 36, 9, 87),
       (155, 36, 10, 72),
       (156, 37, 1, 68),
       (157, 37, 2, 38),
       (158, 37, 6, 54),
       (159, 37, 10, 58),
       (160, 38, 1, 72),
       (161, 38, 2, 68),
       (162, 38, 6, 68),
       (163, 38, 10, 58),
       (164, 8, 9, 78),
       (165, 8, 6, 65),
       (166, 39, 1, 25),
       (167, 39, 2, 78),
       (168, 39, 7, 98),
       (187, 40, 1, 76),
       (188, 40, 2, 46),
       (189, 40, 3, 76),
       (190, 41, 1, 34),
       (191, 41, 2, 56),
       (192, 41, 3, 87),
       (193, 42, 1, 35),
       (194, 42, 2, 87),
       (195, 42, 3, 98),
       (196, 43, 1, 67),
       (197, 43, 2, 57),
       (198, 43, 3, 94),
       (199, 44, 1, 73),
       (200, 44, 2, 57),
       (201, 44, 3, 53),
       (202, 45, 1, 75),
       (203, 45, 2, 35),
       (204, 45, 3, 65),
       (205, 46, 1, 68),
       (206, 46, 2, 64),
       (207, 46, 4, 68),
       (208, 47, 1, 46),
       (209, 47, 2, 57),
       (210, 47, 4, 67),
       (211, 48, 1, 78),
       (212, 48, 2, 57),
       (213, 48, 4, 57),
       (214, 49, 1, 68),
       (215, 49, 2, 64),
       (216, 49, 4, 36),
       (217, 50, 1, 67),
       (218, 50, 2, 78),
       (219, 50, 4, 79),
       (220, 51, 1, 76),
       (221, 51, 2, 89),
       (222, 51, 4, 90),
       (223, 52, 1, 99),
       (224, 52, 2, 100),
       (225, 52, 3, 100),
       (226, 52, 4, 100),
       (227, 52, 5, 100),
       (228, 52, 6, 19),
       (229, 52, 7, 19),
       (230, 52, 8, 100),
       (231, 52, 9, 100),
       (232, 52, 10, 100),
       (233, 52, 11, 100),
       (234, 2, 1, 55),
       (235, 2, 2, 64),
       (236, 2, 10, 70);
-- No native definition for element: enrollee_id (index)

-- No native definition for element: subject_id (index)


-- program_enrollee: table
CREATE TABLE `program_enrollee`
(
    `program_enrollee_id` int NOT NULL AUTO_INCREMENT,
    `program_id`          int DEFAULT NULL,
    `enrollee_id`         int DEFAULT NULL,
    `exam_result`         int DEFAULT NULL,
    PRIMARY KEY (`program_enrollee_id`),
    KEY `program_id` (`program_id`),
    KEY `enrollee_id` (`enrollee_id`),
    CONSTRAINT `program_enrollee_ibfk_1` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`) ON DELETE CASCADE,
    CONSTRAINT `program_enrollee_ibfk_2` FOREIGN KEY (`enrollee_id`) REFERENCES `enrollee` (`enrollee_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 132
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `program_enrollee` (program_enrollee_id, program_id, enrollee_id, exam_result)
VALUES (30, 6, 1, -1),
       (31, 7, 1, -1),
       (32, 8, 1, -1),
       (33, 33, 2, -1),
       (35, 6, 3, -1),
       (36, 7, 3, -1),
       (37, 8, 3, -1),
       (38, 6, 4, -1),
       (39, 7, 4, -1),
       (41, 8, 5, -1),
       (44, 7, 6, -1),
       (45, 8, 6, -1),
       (47, 22, 10, -1),
       (48, 27, 10, -1),
       (49, 28, 10, -1),
       (50, 6, 20, -1),
       (51, 8, 20, -1),
       (53, 7, 21, -1),
       (56, 6, 22, -1),
       (57, 7, 22, -1),
       (59, 6, 23, -1),
       (62, 22, 24, -1),
       (63, 27, 24, -1),
       (64, 28, 24, -1),
       (65, 22, 25, -1),
       (66, 27, 25, -1),
       (67, 28, 25, -1),
       (68, 22, 26, -1),
       (69, 27, 26, -1),
       (70, 28, 26, -1),
       (71, 22, 27, -1),
       (72, 27, 27, -1),
       (73, 28, 27, -1),
       (74, 33, 28, -1),
       (75, 35, 28, -1),
       (76, 56, 28, 88),
       (77, 33, 29, -1),
       (78, 35, 29, -1),
       (79, 56, 29, 90),
       (80, 56, 30, 35),
       (83, 56, 31, 56),
       (86, 56, 32, 64),
       (89, 34, 33, -1),
       (90, 46, 33, -1),
       (91, 55, 33, 76),
       (92, 34, 34, -1),
       (93, 46, 34, -1),
       (94, 55, 34, 78),
       (95, 34, 35, -1),
       (96, 46, 35, -1),
       (97, 55, 35, 55),
       (98, 33, 36, -1),
       (99, 34, 36, -1),
       (100, 35, 36, -1),
       (101, 33, 37, -1),
       (102, 35, 37, -1),
       (103, 46, 37, -1),
       (104, 33, 38, -1),
       (105, 35, 38, -1),
       (106, 46, 38, -1),
       (107, 34, 8, -1),
       (108, 46, 8, -1),
       (109, 55, 8, 82),
       (110, 56, 39, 44),
       (111, 30, 46, -1),
       (112, 30, 47, -1),
       (113, 30, 48, -1),
       (114, 30, 49, -1),
       (115, 30, 50, -1),
       (116, 30, 51, -1),
       (117, 50, 40, -1),
       (118, 50, 41, -1),
       (119, 50, 42, -1),
       (120, 50, 43, -1),
       (121, 50, 44, -1),
       (122, 50, 45, -1),
       (123, 51, 40, -1),
       (124, 51, 41, -1),
       (125, 51, 42, -1),
       (126, 51, 43, -1),
       (127, 51, 44, -1),
       (128, 51, 45, -1),
       (129, 6, 52, -1),
       (130, 56, 52, 4),
       (131, 30, 52, -1);
-- No native definition for element: program_id (index)

-- No native definition for element: enrollee_id (index)


-- program_subject: table
CREATE TABLE `program_subject`
(
    `program_subject_id` int NOT NULL AUTO_INCREMENT,
    `program_id`         int DEFAULT NULL,
    `subject_id`         int DEFAULT NULL,
    `min_result`         int DEFAULT NULL,
    PRIMARY KEY (`program_subject_id`),
    KEY `program_id` (`program_id`),
    KEY `subject_id` (`subject_id`),
    CONSTRAINT `program_subject_ibfk_1` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`) ON DELETE CASCADE,
    CONSTRAINT `program_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 133
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `program_subject` (program_subject_id, program_id, subject_id, min_result)
VALUES (16, 6, 1, 35),
       (17, 6, 2, 40),
       (18, 6, 3, 45),
       (19, 7, 1, 35),
       (20, 7, 2, 40),
       (21, 7, 3, 45),
       (22, 8, 1, 35),
       (23, 8, 2, 40),
       (24, 8, 3, 45),
       (40, 22, 1, 35),
       (41, 22, 2, 40),
       (42, 22, 4, 40),
       (55, 27, 1, 35),
       (56, 27, 2, 40),
       (57, 27, 4, 40),
       (58, 28, 1, 35),
       (59, 28, 2, 40),
       (60, 28, 4, 40),
       (64, 30, 1, 35),
       (65, 30, 2, 40),
       (66, 30, 4, 40),
       (73, 33, 1, 35),
       (74, 33, 2, 40),
       (75, 33, 10, 30),
       (76, 34, 1, 35),
       (77, 34, 2, 40),
       (78, 34, 9, 45),
       (79, 35, 1, 35),
       (80, 35, 2, 40),
       (81, 35, 10, 30),
       (91, 46, 1, 35),
       (92, 46, 2, 40),
       (93, 46, 6, 35),
       (103, 50, 1, 35),
       (104, 50, 2, 40),
       (105, 50, 3, 45),
       (106, 51, 1, 35),
       (107, 51, 2, 40),
       (108, 51, 3, 45),
       (109, 55, 1, 35),
       (110, 55, 2, 40),
       (111, 55, 9, 45),
       (112, 56, 1, 35),
       (113, 56, 2, 40),
       (114, 56, 7, 40);
-- No native definition for element: program_id (index)

-- No native definition for element: subject_id (index)
