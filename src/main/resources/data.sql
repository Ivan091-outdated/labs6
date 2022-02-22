INSERT INTO team(team_id, name)
VALUES (1, 'Mercedes'),
       (2, 'Ferrari');


INSERT INTO driver(driver_id, name, number, team_id)
VALUES (1, 'Lewis Hamilton', 44, 1),
       (2, 'George Russell', 63, 1),
       (3, 'Charles Leclerc', 16, 2),
       (4, 'Carlos Sainz', 55, 2);

INSERT INTO weekend(weekend_id, name, race_date)
VALUES (1, 'Bahrain GP', '2022-03-20'),
       (2, 'Saudi Arabian GP', '2022-03-27');

INSERT INTO driver_weekend_result(driver_id, weekend_id, position)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (1, 2, 4),
       (2, 2, 1),
       (3, 2, 3),
       (4, 2, 2);

INSERT INTO position_points(position, points)
VALUES (1, 25),
       (2, 18),
       (3, 15),
       (4, 12);