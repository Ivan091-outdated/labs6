CREATE TABLE driver
(
    driver_id int GENERATED ALWAYS AS IDENTITY,
    name      varchar(20) NOT NULL,
    number    int         NOT NULL,
    team_id   int         NOT NULL,
    PRIMARY KEY (driver_id)
);

CREATE TABLE team
(
    team_id int GENERATED ALWAYS AS IDENTITY,
    name    varchar(50) NOT NULL,
    PRIMARY KEY (team_id)
);

CREATE TABLE weekend
(
    weekend_id int GENERATED ALWAYS AS IDENTITY,
    name       varchar(50) NOT NULL,
    race_date  date        NOT NULL,
    PRIMARY KEY (weekend_id)
);

CREATE TABLE driver_weekend_result
(
    driver_id  int NOT NULL,
    weekend_id int NOT NULL,
    position   int NOT NULL,
    PRIMARY KEY (driver_id, weekend_id)
);

CREATE TABLE position_points
(
    position int NOT NULL,
    points   int NOT NULL,
    PRIMARY KEY (position)
);

ALTER TABLE driver
    ADD FOREIGN KEY (team_id)
        REFERENCES team (team_id);

ALTER TABLE driver_weekend_result
    ADD FOREIGN KEY (driver_id)
        REFERENCES driver (driver_id);

ALTER TABLE driver_weekend_result
    ADD FOREIGN KEY (weekend_id)
        REFERENCES weekend (weekend_id);
