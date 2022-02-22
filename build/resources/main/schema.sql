CREATE TABLE driver
(
    driver_id int generated always as identity,
    name      varchar(20) not null,
    number    int         not null,
    team_id   int         not null,
    primary key (driver_id)
);

CREATE TABLE team
(
    team_id int generated always as identity,
    name    varchar(50) not null,
    primary key (team_id)
);

CREATE TABLE weekend
(
    weekend_id int generated always as identity,
    name       varchar(50) not null,
    race_date  date        not null,
    primary key (weekend_id)
);

CREATE TABLE driver_weekend_result
(
    driver_id  int not null,
    weekend_id int not null,
    position   int not null,
    primary key (driver_id, weekend_id)
);

CREATE TABLE position_points
(
    position int not null,
    points   int not null,
    primary key (position)
)
