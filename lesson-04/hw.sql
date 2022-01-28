DROP DATABASE IF EXISTS cinema;

CREATE DATABASE IF NOT EXISTS cinema;

use cinema;

CREATE TABLE IF NOT EXISTS films (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(45) NOT NULL,
    duration TIME NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS dates (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     start_date DATE NOT NULL,
                                     PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS sessions (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        film_id INT NOT NULL,
                                        date_id INT NOT NULL,
                                        start_time TIME NOT NULL,
                                        price DECIMAL NOT NULL,
                                        PRIMARY KEY (id),
    INDEX fk_film_id_films_id_idx (film_id ASC),
    INDEX fk_date_id_dates_id_idx (date_id ASC),
    CONSTRAINT fk_film_id_films_id
    FOREIGN KEY (film_id)
    REFERENCES films (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT fk_date_id_dates_id
    FOREIGN KEY (date_id)
    REFERENCES dates (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS tickets (
                                       id INT NOT NULL AUTO_INCREMENT,
                                       session_id INT NOT NULL,
                                       PRIMARY KEY (id),
    INDEX fk_session_id_sessions_id_idx (session_id ASC),
    CONSTRAINT fk_session_id_sessions_id
    FOREIGN KEY (session_id)
    REFERENCES sessions (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES ('Люди Х', '01:00:00');
INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES ('Рембо', '01:30:00');
INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES ('Терминатор', '01:00:00');
INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES ('Титаник', '02:00:00');
INSERT INTO `cinema`.`films` (`name`, `duration`) VALUES ('Аватар', '02:00:00');

INSERT INTO `cinema`.`dates` (`start_date`) VALUES('2022-12-01');

INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('1', 1, '10:00:00', '250');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('2', 1, '12:00:00', '250');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('3', 1, '15:00:00', '450');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('4', 1, '08:00:00', '350');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('5', 1, '18:00:00', '250');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('2', 1, '14:00:00', '250');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('4', 1, '09:00:00', '150');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('1', 1, '11:00:00', '250');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('3', 1, '10:30:00', '200');
INSERT INTO `cinema`.`sessions` (`film_id`, date_id, `start_time`, `price`) VALUES ('5', 1, '11:30:00', '450');


INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('1');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('1');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('1');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('3');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('5');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('3');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('3');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('1');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('1');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('5');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('5');

INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('10');

# 1. ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
#    Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
SELECT
    films1.name AS `Фильм 1`,
    SEC_TO_TIME(TIME_TO_SEC(d1.start_date) + TIME_TO_SEC(sessions1.start_time)) AS `Начало 1`,
    films1.duration AS `Длительность 1`,
    films2.name AS `Фильм 2`,
    SEC_TO_TIME(TIME_TO_SEC(d2.start_date) + TIME_TO_SEC(sessions2.start_time)) AS `Начало 2`,
    films2.duration AS `Длительность 2`
FROM
    sessions AS sessions1
        INNER JOIN
    dates AS d1 ON sessions1.date_id = d1.id
        INNER JOIN
    films AS films1 ON films1.id = sessions1.film_id
        INNER JOIN
    sessions AS sessions2 ON sessions1.id != sessions2.id
        INNER JOIN
    dates AS d2 ON sessions2.date_id = d2.id
    INNER JOIN
    films AS films2 ON films2.id = sessions2.film_id
HAVING
    `Начало 2` > `Начало 1`
   AND TIME_TO_SEC(`Начало 1`) + TIME_TO_SEC(films1.duration) > TIME_TO_SEC(`Начало 2`)
ORDER BY `Начало 1`, `Начало 2`;

# 2. перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
#    Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
SELECT
    films.name AS `Фильм 1`,
    s1.start_time AS `Время начала`,
    films.duration AS `Длительность`,
    s2.start_time AS `Время начала второго фильма`,
    SEC_TO_TIME(TIME_TO_SEC(s2.start_time) - (TIME_TO_SEC(s1.start_time) + TIME_TO_SEC(films.duration))) AS `Длительность перерыва`
FROM
    films
        INNER JOIN
    sessions AS s1 ON films.id = s1.film_id
        INNER JOIN
    sessions AS s2
WHERE
        s2.id = (SELECT
                     sessions.id
                 FROM
                     sessions
                 WHERE
                         sessions.start_time > s1.start_time
                 ORDER BY sessions.start_time
    LIMIT 1)
HAVING `Длительность перерыва` >= TIME('00:30:00')
ORDER BY s1.start_time;

# 3. список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
#    и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
#    Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
(SELECT
     films.name as `Фильм`,
     COUNT(tickets.id) as `Число посетителей`,
     COUNT(tickets.id) / (SELECT COUNT(*) from sessions as s where s.film_id = films.id) as `Среднее за сеанс`,
     (select SUM(s1.price) from sessions as s1 inner join tickets as t1 on s1.id = t1.session_id where s1.film_id = films.id group by s1.film_id) as `Сборы`
 FROM
     sessions
         INNER JOIN
     films ON sessions.film_id = films.id
         INNER JOIN
     tickets ON tickets.session_id = sessions.id
 GROUP by films.id order by `Сборы` desc)
UNION
SELECT 'Итого', SUM(t1.cnt), SUM(t1.avg_per_session), SUM(t1.profit)
FROM
    (SELECT
         films.id as film_id,
         COUNT(tickets.id) as cnt,
         COUNT(tickets.id) / (SELECT COUNT(*) from sessions as s where s.film_id = films.id) as avg_per_session,
         (select SUM(s1.price) from sessions as s1 inner join tickets as t1 on s1.id = t1.session_id where s1.film_id = films.id group by s1.film_id) as profit
     FROM
         sessions
             INNER JOIN
         films ON sessions.film_id = films.id
             INNER JOIN
         tickets ON tickets.session_id = sessions.id
     GROUP by films.id) as t1;

# 4. число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
#    с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT
    '9:00 - 15:00' as `Интервал`,
    COUNT(t1.id) as `Число посетителей`,
    IFNULL(SUM(s1.price), 0) as `Кассовые сборы`
FROM
    sessions as s1
        INNER JOIN
    tickets as t1 ON t1.session_id = s1.id
WHERE
    s1.start_time between TIME('09:00:00') AND TIME('15:00:00')
UNION
SELECT
    '15:00 - 18:00',
    COUNT(t1.id) as `Число посетителей`,
    IFNULL(SUM(s1.price), 0) as `Кассовые сборы`
FROM
    sessions as s1
        INNER JOIN
    tickets as t1 ON t1.session_id = s1.id
WHERE
    s1.start_time between TIME('15:00:00') AND TIME('18:00:00')
UNION
SELECT
    '18:00 - 21:00',
    COUNT(t1.id) as `Число посетителей`,
    IFNULL(SUM(s1.price), 0) as `Кассовые сборы`
FROM
    sessions as s1
        INNER JOIN
    tickets as t1 ON t1.session_id = s1.id
WHERE
    s1.start_time between TIME('18:00:00') AND TIME('21:00:00')
UNION
SELECT
    '21:00 - 00:00',
    COUNT(t1.id) as `Число посетителей`,
    IFNULL(SUM(s1.price), 0) as `Кассовые сборы`
FROM
    sessions as s1
        INNER JOIN
    tickets as t1 ON t1.session_id = s1.id
WHERE
    s1.start_time between TIME('21:00:00') AND TIME('23:59:59');